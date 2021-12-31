(ns clojure-course.namespaces
  (:gen-class)
  (:require [clojure.string :refer [capitalize]]))

(defn -main
  "Namespaces in Clojure"
  []
  (println (capitalize "henlo")))
