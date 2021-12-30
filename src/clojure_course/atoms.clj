(ns clojure-course.atoms
  (:gen-class))

(defn atoms-atoms
  []
  (def amount (atom 100))

  (println "Raw atom" amount)
  (println "Atom value" @amount)

  (swap! amount inc)
  (println "New atom value" @amount)

  (reset! amount 83)
  (println "Value after 'reset!'" @amount)

  (compare-and-set! amount 83 25)
  (println "Value of atom is" @amount)

  (compare-and-set! amount 42 23)
  (println "Value of atom is the same" @amount))

(defn -main
  "Atom meaning in Clojure"
  []
  (atoms-atoms))
