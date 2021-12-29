(ns clojure-course.conditionals)

(defn cond-if
  []
  (println "Cond if")

  (def x 5)
  (def y 6)

  (if (= x y)
    (println x "and" y "are equal")
    (println x "and" y "are not equal")))

(defn cond-if-do
  []
  (println "Cond if do")

  (if (= 1 1)
    (do (println "It's true")
        (println "probably"))
    (do (println "It's obviously false"))))

(defn cond-nesting
  []
  (println "Cond neting")

  (if (and (= 5 5) (or (= 2 2) (not true)))
    (println true)
    (println false)))

(defn cond-case
  []
  (println "Cond case")

  (def animal "dog")

  (case animal
    "dog" (println "i have a dog")
    "cat" (println "i have a cat")
    "egg" (println "i have a egg")))

(defn cond-cond
  [amount]
  (println "Cond conditional")

  (cond
    (<= amount 2) (println "Few")
    (<= amount 10) (println "Several")
    (<= amount 100) (println "Many")
    :else (println "A lot!")))

(defn -main
  "Using conditionals in Clojure"
  []
  (cond-if)
  (cond-if-do)
  (cond-nesting)
  (cond-case)
  (cond-cond 1000))
