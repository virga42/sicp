(defun expmod (base exp m)
  (cond
    ((= exp 0) 1)
    ((evenp exp)
     (rem
      (square (expmod base (/ exp 2) m))
      m))
    (t (rem (* base (expmod base (1- exp) m)) m))))

(defun square (n)
  (* n n))

(defun fermat-test (n)
  (labels
      ((try-it (a)
	 (= (expmod a n n) a)))
    (try-it (+ 1 (random (- n 1))))))
