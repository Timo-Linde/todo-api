(ns todo-api.todo.api
  (:require
    [konserve.core :as k]
    [todo-api.spec-tools :as spec-tools]
    [todo-api.storage.konserve :refer [check-key-exist-or-throw opts store]]
    [todo-api.todo.spec :as todo-spec]))

(defn- update-todo-entry
  [todo name description]
  (-> todo
    (assoc :name name)
    (assoc :description description)))

(defn create-todo
  [name description]
  (let [id (random-uuid)
        new-todo {:name        name
                  :description description}]
    (spec-tools/check-spec-or-throw ::todo-spec/todo new-todo)
    (k/assoc store id new-todo opts)
    id))

(defn read-todo
  [id]
  (spec-tools/check-spec-or-throw ::todo-spec/id id)
  (check-key-exist-or-throw store id)
  (k/get store id opts))

(defn update-todo
  [id name description]
  (spec-tools/check-spec-or-throw ::todo-spec/todo
    {:name name
     :description description})
  (check-key-exist-or-throw store id)
  (k/update store id #(update-todo-entry % name description) opts))

(defn delete-todo
  [id]
  (spec-tools/check-spec-or-throw ::todo-spec/id id)
  (check-key-exist-or-throw store id)
  (k/dissoc store id opts))

(defn list-todos
  []
  (let [keys (k/keys store opts)]
    (map read-todo keys)))