(ns todo-api.core
  (:require
    [org.httpkit.server :as http-kit]
    [todo-api.handler :as handler])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& _args]
  (http-kit/run-server (handler/new-handler) {:port 1337}))