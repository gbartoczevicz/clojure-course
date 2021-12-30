(ns clojure-course.struct-maps
  (:gen-class))

(defn -main
  "Structs in Clojure"
  []

  (defstruct Pet :name :type)

  (def dog (struct Pet "Eggdog" "dog"))
  (println "Pet Struct" dog)

  (def catto (struct-map Pet :name "Catto :)" :type "cat"))
  (println "Pet struct-map name" (:name catto))

  (def edited-catto (assoc catto :name "Just catto"))
  (println "Edited catto name is" (:name edited-catto))

  (def catto-with-age (assoc edited-catto :age 1))

  (println "Edited catto is" (:age catto-with-age) "years old"))
