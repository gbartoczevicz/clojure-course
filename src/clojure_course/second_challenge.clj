(ns clojure-course.second-challenge
  (:gen-class)
  (:import (java.util UUID)))

(defstruct Car :name :value)
(defstruct Coupon :name :discount)

(defn create-car
  [^String name ^Number value]
  (struct Car name value))

(defn assoc-discount-into-car
  [car, percentage]
  (def car-value (:value car))
  (def with-discount (- car-value (* car-value percentage)))
  (assoc car :value with-discount))

(defn create-cars
  []
  (seq [(create-car "bmw" 60000),
        (create-car "ferrari" 100000),
        (create-car "fiat" 20000)]))

(defn create-coupon
  [^String name ^Integer discount]
  (try
    (struct Coupon (.toString (UUID/fromString name)) discount)

    (catch IllegalArgumentException _ignore
      (struct Coupon (.toString (UUID/randomUUID)) 0))))

(defn create-cars-with-discount
  [cars coupon]
  (def percentage (/ (:discount coupon) 100))
  (for [car cars] (assoc-discount-into-car car percentage)))

(defn cars-available-by-budget
  [cars budget]
  (filter #(<= (:value %) budget) cars))

(defn -main
  "Car dealership"
  []

  (def budget 50000)
  (def cars (create-cars))
  (def coupon (create-coupon (.toString (UUID/randomUUID)) 20))
  (def cars-with-discount (create-cars-with-discount cars coupon))
  (def cars-available (cars-available-by-budget cars-with-discount budget))

  (println "Cars available" cars)
  (println "Coupon being applied" coupon)
  (println "Cars available with the discount applied with the coupon" cars-with-discount)
  (println "Cars available with the current budget" cars-available))