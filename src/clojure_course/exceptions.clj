(ns clojure-course.exceptions
  (:gen-class))

(defn to-throw-error
  [x]
  (inc x))

(defn -main
  "Errors in Clojure"
  []

  (try
    (to-throw-error "an unexpected statement!")
    (catch Exception exception
      (println "An error occurred:" (.getMessage exception)))
    (finally
      (println "The program is about to terminate :)"))))