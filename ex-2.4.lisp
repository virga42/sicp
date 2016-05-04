(defun cons-* (x y)
  (lambda (m) (funcall m x y)))

(defun car-* (z)
  (funcall z (lambda (p q) p)))

(defun cdr-* (z)
  (funcall z (lambda (p q) q)))
