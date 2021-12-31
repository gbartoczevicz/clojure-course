(ns clojure-course.watchers
  (:gen-class))

;; delegate -> key atom old-state new-state
(defn -main
  "Watchers in Clojure"
  []
  (def an-atom (atom 0))

  (add-watch an-atom :an-atom-watcher
             (fn [& delegate]
               (println "The watcher has been called with" delegate)))
  (swap! an-atom inc)

  (remove-watch an-atom :an-atom-watcher)
  (swap! an-atom inc))
