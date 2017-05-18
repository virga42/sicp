;; Excercise 1.2
(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5))))) (* 3 (- 6 2) (- 2 7)))

;; Exercise 1.3
(defn square
  [x]
  (* x x))

(defn sum-of-squares
  [x y]
  (+ (square x) (square y)))

(defn largest-two-of-three
  [a b c]
  (if (and (< a b) (< a c))
    (list b c)
    (if (and (< b a) (< b c))
      (list a c)
      (list a b))))

(defn sum-of-squares-largest-two
  [a b c]
  (letfn [(largest-two
           [a b c]
           (if (and (< a b) (< a c))
             (list b c)
             (if (and (< b a) (< b c))
               (list a c)
               (list a b))))]
      (let [[x y] (largest-two a b c)]
        (+ (* x x) (* y y)))))

;; Exercise 1.4
(defn a-plus-abs-b
  [a b]
  ((if (> b 0) + -) a b))


;; Exercise 1.5
;; sample change
