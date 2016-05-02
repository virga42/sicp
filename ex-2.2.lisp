(defun make-point (a b)
  "constructor"
  (cons a b))

(defun x-point (p)
  "selector"
  (car p))

(defun y-point (p)
  "selector"
  (cdr p))

(defun midpoint-segment (line-segment)
  "procedure returns a midpoint"
  (let ((avg-x (/ (+ (x-point (start-segment line-segment))
		  (x-point (end-segment line-segment)))
		  2))
	(avg-y (/ (+ (y-point (start-segment line-segment))
		     (y-point (end-segment line-segment)))
		  2)))
    (make-point avg-x avg-y)))

(defun make-segment (start-point end-point)
  "constructor"
  (cons start-point end-point))

(defun start-segment (segment)
  "selector"
  (car segment))

(defun end-segment (segment)
  "selector"
  (cdr segment))

(defun print-point (p)
  "procedure prints points"
  (format t "(~a,~a)" (x-point p) (y-point p)))

;; (setf a-line-segment (make-segment (make-point 1 1) (make-point 3 3)))
;; (print-point (midpoint-segment a-line-segment))

(defun make-rectangle-points (a b)
  "constructor"
  (cons a b))

(defun make-rectangle-wh (a w h)
  "constructor"
  (cons a (cons (+ (x-point a) w) (+ (y-point a) h))))

(defun rect-width (r)
  "procedure"
  (- (car (cdr r)) (car (car r))))

(defun rect-height (r)
  "procedure"
  (- (cdr (cdr r)) (cdr (car r))))

(defun perimeter-of-rect (r)
  "procedure"
  (* (+ (rect-width r) (rect-height r))
     2))

(defun area-of-rect (r)
  "procedure"
  (* (rect-width r) (rect-height r)))

