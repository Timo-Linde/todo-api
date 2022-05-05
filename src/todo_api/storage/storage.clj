(ns todo-api.storage.storage)

(def todos (atom {}))

(defn fetch
  []
  @todos)

(defn persist
  [new-todos]
  (reset! todos new-todos))