(ns clojure-course.loops
  (:gen-class))

(defn loops-loop
  []
  (println "Simple loop")

  (loop [x 0]
    (when (< x 10)
      (println x)
      (recur (inc x)))))

(defn loops-dotimes
  []
  (println "Do-times")

  (dotimes [x 10]
    (println x)))

(defn loops-whiledo
  []
  (println "While-do")

  (def x (atom 0))

  (while (< @x 10)
    (do (println @x)
        (swap! x inc))))

(defn loops-doseq
  []
  (def seq [1 2 3 4 5])

  (println "Do-seq")

  (doseq [x seq]
    (println "The value at the collection is" x)))

(defn -main
  "Loops in Clojure"
  []
  (loops-loop)
  (loops-dotimes)
  (loops-whiledo)
  (loops-doseq))
