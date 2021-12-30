(ns clojure-course.destructuring
  (:gen-class))

(defn -main
  "Destructuring in Clojure"
  []

  (def vect [1 2 3 4 5])

  (let [[a b c] vect]
    (println "Destructured" vect "is" a b c))

  (let [[a b & delegate] vect]
    (println "Destructured" vect "is" a b)
    (println "The rest also is" delegate))

  (def map-to-destructure {'name "egg" 'lastname "dog"})
  (let [{name-destructured 'name lastname-destructured 'lastname} map-to-destructure]
    (println "Destructured map props are" name-destructured lastname-destructured)))
