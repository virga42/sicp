(defun make-rat (n d &optional )
  (let ((g (gcd n d)))
  (cons (/ (* n (signum d)) g) (/ (abs d) g))))

(defun numer (x)
  (car x))

(defun denom (x)
  (cdr x))

(defun print-rat (x)
  (format t "~a/~a~%" (numer x) (denom x)))

