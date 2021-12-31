(ns clojure-course.macros
  (:gen-class))

;; Will result into '(if true (do (println "henlo")))'
(macroexpand-1 '(when true (println "henlo")))
