(defun make-interval (a b)
  (cons a b))

(defun lower-bound (interval)
  (car interval))

(defun upper-bound (interval)
  (cdr interval))

(defun add-interval (x y)
  (make-interval (+ (lower-bound x) (lower-bound y))
		 (+ (upper-bound x) (upper-bound y))))

(defun mul-interval (x y)
  (let ((p1 (* (lower-bound x) (lower-bound y)))
	(p2 (* (lower-bound x) (upper-bound y)))
	(p3 (* (upper-bound x) (lower-bound y)))
	(p4 (* (upper-bound x) (upper-bound y))))
    (make-interval (min p1 p2 p3 p4)
		   (max p1 p2 p3 p4))))

(defun div-interval (x y)
  (if (> 0 (lower-bound y))
      (print "error")
      (mul-interval
       x
       (make-interval (/ 1.0 (upper-bound y))
		      (/ 1.0 (lower-bound y))))))

(defun sub-interval (x y)
  (make-interval ((- (lower-bound x) (upper-bound y))
		  (- (upper-bound x) (lower-bound y)))))

;; ex 2.11
(defun make-center-width (c w)
  (make-interval (- c w) (+ c w)))

(defun center (interval)
  (/ (+ (lower-bound interval) (upper-bound interval)) 2))

(defun width (interval)
  (/ (- (upper-bound interval) (lower-bound interval)) 2))

;; ex 2.12
(defun make-center-percent (c pct)
  (make-interval (- c (* c pct)) (+ c (* c pct))))

(defun percent (interval)
  (/ (width interval) (center interval)))

;; ex 2.14
(defun par1 (r1 r2)
  (div-interval (mul-interval r1 r2)
		(add-interval r1 r2)))

(defun par2 (r1 r2)
  (let ((one (make-interval 1 1)))
    (div-interval
     one
     (add-interval (div-interval one r1)
		   (div-interval one r2)))))
