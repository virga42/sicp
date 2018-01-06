(ns exercises.core)

;; ==============
;; Excercise 1.1
;; ==============

; Below is a sequence of expressions. What is the result printed by the interpreter
; in response to each expression? Assume that the sequence is to be evaluated in
; order in which it is presented


;; 10
;; => 10

;; (+ 5 3 4)
;; => 12

;; (- 9 1)
;; => 8

;; (/ 6 2)
;; => 3

;; (+ (* 2 4) (- 4 6))
;; => 6

;; (def a 3)
;; => #'user/a

;; (def b (+ a 1))
;; => #'user/b

;; (+ a b (* a b))
;; => 19

;; (= a b)
;; => false

;; (if (and (> b a) (< b (* a b)))
;;   b
;;   a)
;; => 4

;; (cond
;;   (= a 4) 6
;;   (= b 4) (+ 6 7 a)
;;   :else 25)
;; => 16

;; (+ 2 (if (> b a) b a))
;; => 6

;; (* (cond
;;      (> a b) a
;;      (< a b) b
;;      :else -1)
;;    (+ a 1))
;; => 16

;; ==============
;; Exercise 1.2
;; ==============

; Translate the following expression into prefix form
; 5 + 4 + (2 - (3 - (6 + 4/5))) / 3(6- 2)(2 - 7)

; (/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
;    * 3 (- 6 2) (- 2 7))

;; ==============
;; Exercise 1.3
;; ==============

; Define a procedure that takes three numbers as arguments and returns the sum
; of the squares of the two larger numbers

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

;; ==============
;; Exercise 1.4
;; ==============

; Observe that our model of evaluation allows for combinations whose operators are
; compound expressions. Use this observation to describe the behavior of the
; following procedure:

; (defn a-plus-abs-b
;   [a b]
;   ((if (> b 0) + -) a b))

; the body is substituted with the given arguments then the leftmost subexpression
; (in this case the operator is a special form, if) is evaluated which produces
; a primitive. The operator is then evaluated with the provide operands, a and b.

;; (a-plus-abs-b 3 4) returns 7. Since b is greater than 0 the expression becomes
;; (+ 3 4)
;; (a-plus-abs-b 3 -4) returns 7 as well. Since b is less than 0 the expression is
;; (- 3 -4)

;; ==============
;; Exercise 1.5
;; ==============

; Ben Bitdiddle has invented a test to determine whether the interpreter he is
; faced with using applicative-order evaluation or normal-order evaluation. He
; defines the following two procedures:

; (defn p
;   (p))

; (defn test
;   [x y]
;   (if (= x 0)
;     0
;     y))

; What behavior will Ben observe with an interpreter that uses applicative-order?
;
; (test 0 (p)) becomes
; (if (= 0 0)
;   0
;   (p))
; but (p) still needs to be evaluated. The expression returns itself; it is never
; able to terminate which I suspect causes the interpreter to throw an error.

; What behavior will Ben observe with an interpreter that uses normal-order?
;
; (test 0 (p)) becomes
; (if (= 0 0)
;   0
;   (p))
; the if expression is evaluated and since it returns true (0 = 0) the alternative
; expression never needs to be evaluated. Hence an error doesn't occur.

;; ==============
;; Exercise 1.6
;; ==============
;
