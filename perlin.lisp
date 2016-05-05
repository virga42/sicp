(load "~/bottega/sicp/ex-2.2.lisp")

(defun lerp (p0 p1 x)
  (+ (y-point p0) (* (- (y-point p1)
		       (y-point p0))
		    (/ (- x (x-point p0))
		       (- (x-point p1) (x-point p0))))))

(defparameter p0 (make-point 2 2))
(defparameter p1 (make-point 5 5))

(lerp p0 p1 3)

(defun dot-product (vect1 vect2)
  (* (cos (angle vect1 vect2))
     (vector-length vect1)
     (vector-length vect2)))

(defun vector-length (vector)
  ())

