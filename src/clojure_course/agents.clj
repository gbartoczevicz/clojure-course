(ns clojure-course.agents
  (:gen-class))

(defn -main
  "Agents in Clojure"
  []
  (def an-agent (agent 0))

  (println "Raw agent" an-agent)
  (println "Agent value" @an-agent)

  (send an-agent inc)
  (println "New agent value" @an-agent "... or not")

  (send an-agent inc)
  (await-for 1000 an-agent)
  (println "Ok, the new agent value is" @an-agent)

  (println "Some agent error" (agent-error an-agent))

  ;; Not showed in the course :')
  (send an-agent inc)
  (await an-agent)
  (println "The new value (using await only)" @an-agent))
