(ns exercises.core)

;; ==============
;; Excercise 1.1
;; ==============

;; 10
;; 10

;; (+ 5 3 4)
;; 12

;; (- 9 1)
;; 8

;; (/ 6 2)
;; 3

;; (+ (* 2 4) (- 4 6))
;; 6

;; (def a 3)
;; #'user/a

;; (def b (+ a 1))
;; #'user/b

;; (+ a b (* a b))
;; 19

;; (= a b)
;; false

;; (if (and (> b a) (< b (* a b)))
;;   b
;;   a)

;; (cond
;;   (= a 4) 6
;;   (= b 4) (+ 6 7 a)
;;   :else 25)
;; 16

;; (+ 2 (if (> b a) b a))
;; 6

;; (* (cond
;;      (> a b) a
;;      (< a b) b
;;      :else -1)
;;    (+ a 1))
;; 16

;; ==============
;; Exercise 1.2
;; ==============

; (/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
;    * 3 (- 6 2) (- 2 7))

;; ==============
;; Exercise 1.3
;; ==============

;; Number -> Number
;; produce the value of a number multiplied by itself
(defn square
  [x]
  (* x x))

;; Number Number Number -> (list of Number)
;; interp. return a list with the two largest numbers of three given numbers
(defn largest2
  [x y z]
  (cond
    (and (>= x y) (<= y z)) (list x z)
    (and (<= x y) (<= x z)) (list y z)
    :else (list x y)))

;; Number Number Number -> Number
;; interp. return the sum of squares of two largest numbers of three given numbers
(defn sum-squares-largest
  [x y z]
  (+ (square (first  (largest2 x y z)))
     (square (second (largest2 x y z)))))
