(ns clojure-course.sequences
  (:gen-class))

(defn sequences-seq
  []
  (def colors (seq ["red" "purple" "white"]))
  (println "Colors" colors)

  (println "New color at the beginning" (cons "orange" colors))
  (println "New color at somewhere", (conj colors "yellow"))
  (println "Two color sequences concatenation" (concat colors (seq ["black" "gray"])))

  (def not-unique-colors (concat colors (seq ["red" "yellow" "purple"])))
  (println "The unique colors from" not-unique-colors "are" (distinct not-unique-colors))

  (println colors "as backwards is" (reverse colors))
  (println "The first element from" colors "is" (first colors))
  (println "All the elements from" colors "except the first one" (rest colors))
  (println "The last element from" colors "is" (last colors))

  (def to-sort-seq (seq [2 5 28 83 17 91 -9]))

  (println "The sorted sequence from" to-sort-seq "is" (sort to-sort-seq)))

(defn -main
  "Collections in Clojure"
  []
  (sequences-seq))
