(ns clojure-course.references
  (:gen-class))

(defn -main
  "Value reference in Clojure"
  []
  (def amount (ref 0))
  (println "Raw reference" amount)
  (println "Reference value" @amount)

  (dosync
    (ref-set amount 83))
  (println "Reference after dosync" @amount)

  (dosync
    (alter amount inc))
  (println "Using alter inside dosync :)" @amount))
