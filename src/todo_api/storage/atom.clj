(ns todo-api.storage.atom
  (:require
    [todo-api.storage.api :as api]))

(def ^:private todos (atom {}))

(defmethod api/fetch :atom
  []
  @todos)

(defmethod api/persist :atom
  [new-todos]
  (reset! todos new-todos))