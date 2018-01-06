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
