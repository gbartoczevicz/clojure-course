(ns clojure-course.first-challenge
  (:gen-class))

(defn implementation
  [age-multiplicand, pet]

  (def PetMultiplierMap {'dog  7
                         'cat  5
                         'fish 10})

  (def pet-multiplier (get PetMultiplierMap pet))

  (cond (nil? pet-multiplier)
        (do
          (throw
            (Exception.
              (format
                "The value '%s' is not a valid pet type" pet)))))

  (def age-product (* pet-multiplier age-multiplicand))

  (println pet "with" age-multiplicand "years old has" age-product "years old in human years"))

(defn -main
  "A program that calculate animal age into human age"
  []
  (implementation 4 'dog)
  (implementation 5 'cat)
  (implementation 2 'fish))
