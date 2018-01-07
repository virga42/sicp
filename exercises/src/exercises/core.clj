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
; a primitive. The operator is then evaluated with the provided operands, a and b.

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

;; Number -> Number
;; interp. returns the absolute value of a given number
(defn abs
  [x]
  (if (< x 0)
    (- x)
    x))

;; Number Number -> Number
;; interp. produce the average of two numbers
(defn average
  [x y]
  (/ (+ x y) 2))

;; Number Number -> Number
;; interp. produce a better guess by averaging original guess with given number/original guess
(defn improve
  [guess x]
  (average guess (/ x guess)))

;; Number Number -> Boolean
;; returns true if a guess is within a given tolerance
(defn good-enough?
  [guess x]
  (< (abs (- (square guess) x)) 0.001))

;; Number Number -> Number
;; interp. produce the square root of a number using Newton's method of successive
;; approximations. guess is an initial guess, x is radicand
(defn sqrt-iter
  [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))

; What happens when Alyssa attempts to use this to compute square roots?

; She will receive an error because new-if uses applicative-order; the program
; will not complete. The else-clause will always be evaluated and since it is
; recursive the computer will run out of memory.

;; ==============
;; Exercise 1.7
;; ==============

; Design a square-root procedure that uses an end test that stops when there
; is only a small fraction of change from guess to the next

(defn good-enough2?
  [old-guess new-guess x]
  (< 0.001 (- (/ new-guess x) (/ old-guess x))))

(defn sqrt-iter2
 [guess x]
 (letfn [(sqrt-iter [old-guess new-guess x]
                    (cond
                      (good-enough2? old-guess new-guess x) new-guess
                      :else (sqrt-iter new-guess (improve new-guess x))))]
        (sqrt-iter guess (improve guess x) x)))

;; ==============
;; Exercise 1.8
;; ==============

; Newton's method for cube roots is based on the fact that if y is an
; approximation to the cube root of x, then a better approximation is given by
; the evaluated x/y^2 + 2y / 3
; Use this formulat to implement a cube-root procedure analagous to the
; square-root procedures

;; Number Number -> Number
;; produce a better approximation of a cube root by averaging old guess with
;; new guess using Newton's approximation for cube roots
(defn improve-cube
  [guess x]
  (average guess (/ (+ (/ x (square guess)) (* 2 guess)) 3)))

;; Number Number -> Number
;; interp. produce the cube root of a number using Newton's method of successive
;; approximations. guess is an initial guess, x is radicand
(defn cubert-iter2
 [guess x]
 (letfn [(cubert-iter [old-guess new-guess x]
                    (cond
                      (good-enough2? old-guess new-guess x) new-guess
                      :else (cubert-iter new-guess (improve-cube new-guess x))))]
        (cubert-iter guess (improve-cube guess x) x)))


;; ==============
;; Exercise 1.9
;; ==============

(defn +
  [a b]
  (cond (= a 0) b
    :else (inc (+ (dec a) b))))
; is a linear recursive process; for example (+ 2 2) produces the following shape
; (inc (+ 1 2))
; (inc (inc (+ 0 2)))
; (inc (inc 2))
; => 4

; it is characterized as having a chain of deferred calls to inc. the number of
; deferred calls to inc is proportional to the size of a; a very large a would
; have negative performance.

(defn +
  [a b]
  (cond (= a 0) b
    :else (+ (dec a) (inc b))))
; is a linear iterative process; for example (+ 2 2) produces the following shape
; (+ 1 3) - result of (+ (dec 2) (inc 2))
; (+ 0 4) - result of (+ (dec 1) (inc 3))
; => 4

; it is characterized has having fixed size; the number of steps to resolve is linear
; to the size of a

;; ==============
;; Exercise 1.10
;; ==============

(defn A
  [x y]
  (cond
    (= y 0) 0
    (= x 0) (* 2 y)
    (= y 1) 2
    :else (A (- x 1)
             (A x (- y 1)))))

; (defn f [n] (A 0 n)) represents 2*n
; (defn g [n] (A 1 n)) represents 2^n
; (defn h [n] (A 2 n)) represents 2^2...{n-1 times}

;; ==============
;; Exercise 1.11
;; ==============
