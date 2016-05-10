(load "~/bottega/sicp/ex-2.2.lisp")
(load "~/bottega/nature/nature-sdl.lisp")

(in-package :nature)

(defparameter permutation #(151 160 137 91 90 15 
			    131 13 201 95 96 53 194 233 7 225 140 36 103 30 69 142 8 99 37 240 21 10 23 
			    190  6 148 247 120 234 75 0 26 197 62 94 252 219 203 117 35 11 32 57 177 33 
			    88 237 149 56 87 174 20 125 136 171 168  68 175 74 165 71 134 139 48 27 166 
			    77 146 158 231 83 111 229 122 60 211 133 230 220 105 92 41 55 46 245 40 244 
			    102 143 54  65 25 63 161  1 216 80 73 209 76 132 187 208  89 18 169 200 196 
			    135 130 116 188 159 86 164 100 109 198 173 186  3 64 52 217 226 250 124 123 
			    5 202 38 147 118 126 255 82 85 212 207 206 59 227 47 16 58 17 182 189 28 42 
			    223 183 170 213 119 248 152  2 44 154 163  70 221 153 101 155 167  43 172 9 
			    129 22 39 253  19 98 108 110 79 113 224 232 178 185  112 104 218 246 97 228 
			    251 34 242 193 238 210 144 12 191 179 162 241  81 51 145 235 249 14 239 107 
			    49 192 214  31 181 199 106 157 184  84 204 176 115 121 50 45 127  4 150 254 
			    138 236 205 93 222 114 67 29 24 72 243 141 128 195 78 66 215 61 156 180))

(defparameter g (make-array 512))

(dotimes (i 255 g)
  (setf (aref g i ) (aref permutation i))
  (setf (aref g (+ i 255)) (aref permutation i)))

(defun lerp (p0 p1 x)
  (+ p0 (* x (- p1 p0))))

(defun dot-geo (vect1 vect2)
  (* (cos (angle vect1 vect2))
     (vector-length vect1)
     (vector-length vect2)))

(defun dot-alg (vect1 vect2)
  (+ (* (car vect1) (car vect2))
     (* (cdr vect1) (cdr vect2))))

(defun vector-length (vector)
  ())

(defun fade (tr)
  (* tr tr tr (+ (* tr (- (* tr 6) 15)) 10)))

(defun unit (n)
  (logand n 255))

(defun grad (hash x y z)
  (let ((d (logand hash 15)))
    (cond
      ((= d 0) (+ x y))
      ((= d 1) (+ (- x) y))
      ((= d 2) (+ x (- y)))
      ((= d 3) (+ (- x) (- y)))
      ((= d 4) (+ x z))
      ((= d 5) (+ (- x) z))
      ((= d 6) (+ x (- z)))
      ((= d 7) (+ (- x) (- z)))
      ((= d 8) (+ y z))
      ((= d 9) (+ (- y) z))
      ((= d 10) (+ y (- z)))
      ((= d 11) (+ (- y) (- z)))
      ((= d 12) (+ y x))
      ((= d 13) (+ (- y) z))
      ((= d 14) (+ y (- x)))
      ((= d 15) (+ (- y) (- z)))
      (t 0))))

(defun map-value-to-bluescale (v)
  (let ((ramp (abs v)))
    (cond
      ((> ramp 1.0) 255)
      ((< ramp 0) 0)
      (t (floor (* 255 ramp))))))

(defun blue-scale (v)
  (let ((b (map-value-to-bluescale v)))
    (sdl:color :r 0 :g 0 :b b)))

(defun setup ()
  (sdl:clear-display *white*)
  (loop for h from 1 to 50 by .1
       do
       (loop for w from 1 to 50 by .1
	  do
	    (progn
	      ;; (format t "~a scale ~a val ~a x ~a y~%" (map-value-to-bluescale (perlin h w 0))
	      ;; 	      (perlin h w 0)
	      ;; 	      h
	      ;; 	      w)
	      (draw-pixel (sdl:point :x (round (* w 10))
	      			     :y (round (* h 10)))
	      			     :color (blue-scale (perlin w h 0)))
	      )))
       (update-display))

(defun perlin (x y z)
  (let* ((xi (unit (floor x)))
	 (yi (unit (floor y)))
	 (zi (unit (floor z)))
	 (xf (- x (floor x)))
	 (yf (- y (floor y)))
	 (zf (- z (floor z)))
	 (u (fade xf))
	 (v (fade yf))
	 (w (fade zf))
	 (aaa (aref g (+ (aref g (+ (aref g xi) yi)) zi)))
	 (aba (aref g (+ (aref g (+ (aref g xi) (1+ yi))) zi)))
	 (aab (aref g (+ (aref g (+ (aref g xi) yi)) (1+ zi))))
	 (abb (aref g (+ (aref g (+ (aref g xi) (1+ yi))) (1+ zi))))
	 (baa (aref g (+ (aref g (+ (aref g (1+ xi)) yi)) zi)))
	 (bba (aref g (+ (aref g (+ (aref g (1+ xi)) (1+ yi))) zi)))
	 (bab (aref g (+ (aref g (+ (aref g (1+ xi)) yi)) (1+ zi))))
	 (bbb (aref g (+ (aref g (+ (aref g (1+ xi)) (1+ yi))) (1+ zi))))
	 (x1-y1 (lerp (grad aaa xf yf zf)
		      (grad baa (1- xf) yf zf)
		      u))
	 (x2-y1 (lerp (grad aba xf (1- yf) zf)
		      (grad bba (1- xf) (1- yf) zf)
		      u))
	 (y1 (lerp x1-y1 x2-y1 v))
	 (x1-y2 (lerp (grad aab xf yf (1- zf))
		      (grad bab (1- xf) yf (1- zf))
		      u))
	 (x2-y2 (lerp (grad abb xf (1- yf) (1- zf))
		      (grad bbb (1- xf) (1- yf) (1- zf))
		      u))
	 (y2 (lerp x1-y2 x2-y2 v)))
    ;;(format t "~a y1: ~a y2: ~a~% " (list aaa aba aab abb baa bba bab bbb) y1 y2)
    (/ (+ (lerp y1 y2 w) 1) 2)
    ))


;; (let ((stream (open "~/bottega/sicp/output2.txt" :direction
;; 		    :output :if-exists :supersede)))
;;   (loop for h from 1 to 5 by .1
;;        do
;;        (loop for w from 1 to 5 by .1
;; 	  do (progn
;; 	       (write-to-string (perlin w h 0) stream)
;; 	       (terpri stream))))
;;   (close stream))
