(defun cons-* (x y)
  (* (expt 2 x)
     (expt 3 y)))

(defun num-divs (n d)
  (labels ((iter (x result)
	   (if (= 0 (rem x d))
	       (iter (/ x d) (+ 1 result))
	       result)))
    (iter n 0)))

(defun car-* (z)
  (num-divs z 2))

(defun cdr-* (z)
  (num-divs z 3))
