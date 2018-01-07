(ns exercises.core-test
  (:require [clojure.test :refer :all]
            [exercises.core :refer :all]))

(deftest test-square
  (testing "returns value of a given number multiplied by itself")
  (is (= 16 (square 4)))
  (is (= 49 (square 7))))

(deftest test-largest2
  (testing "returns list of two largest numbers given 3 numbers")
  (is (= (list 3 4) (largest2 2 3 4)))   ;yz
  (is (= (list 4 3) (largest2 2 4 3)))   ;yz
  (is (= (list 3 4) (largest2 3 2 4)))   ;xz
  (is (= (list 3 4) (largest2 3 4 2)))   ;xy
  (is (= (list 4 3) (largest2 4 2 3)))   ;xz
  (is (= (list 4 3) (largest2 4 3 2)))   ;xy
  (is (= (list 3 3) (largest2 2 3 3)))   ;xy dupe vals
  (is (= (list 3 3) (largest2 3 2 3)))   ;xy dupe vals
  (is (= (list 3 3) (largest2 3 3 2)))   ;xy dupe vals
  (is (= (list -1 4) (largest2 -1 -3 4))));xy neg vals

(deftest test-sum-squares-largest-numbers
  (testing "returns the sum of squares of two larger numbers of given 3 numbers"
    (is (= 25 (sum-squares-largest 2 3 4)))
    (is (= 18 (sum-squares-largest 2 3 3)))     ;dupe vals
    (is (= 17 (sum-squares-largest -1 -3 4))))) ;neg vals

(deftest test-average
  (testing "returns the average of two numbers"
    (is (= 6 (average 4 8)))
    (is (= 15 (average 10 20)))))

(deftest test-improve
  (testing "averaging quotient and radicand produces improved guess"
    (is (== 1.5 (/ (+ 2 1) 2)))))

(deftest test-sqrt-iter
  (testing "producing the square root of a number"
    (is (let [eps 0.00000001
              result (sqrt-iter 1.0 16)]
          (or (< (- 4 eps) result)
              (> (+ 4 eps) result))))))

(deftest test-sqrt-iter2
  (testing "producing the square root of a number; modified version"
    (is (let [eps 0.001
              result (sqrt-iter2 1.0 16)]
          (or (< (- 4 eps) result)
              (> (+ 4 eps)) result)))
    (is (let [eps 0.001
              result (sqrt-iter2 1.0 10000000000000)]
          (or (< (- 3162277.66017 eps) result)
              (> (+ 3162277.66017 eps)) result)))
    (is (let [eps 0.001
              result (sqrt-iter2 0.002 0.000005)]
          (or (< (- 0.00223606797 eps) result)
              (> (+ 0.00223606797 eps)) result)))))

(deftest test-cubert-iter2
  (testing "producing the cube root of a number"
    (is (let [eps 0.001
              result (cubert-iter2 1.0 9)]
          (or (< (- 3 eps) result)
              (> (+ 3 eps)) result)))
    (is (let [eps 0.001
              result (cubert-iter2 1.0 729)]
          (or (< (- 9 eps) result)
              (> (+ 9 eps)) result)))
    (is (let [eps 0.001
              result (cubert-iter2 1.0 1000000)]
          (or (< (- 100 eps) result)
              (> (+ 100 eps)) result)))
    (is (let [eps 0.001
              result (cubert-iter2 0.0002 0.0000000005)]
          (or (< (- 0.000793701 eps) result)
              (> (+ 0.000793701 eps)) result)))))
