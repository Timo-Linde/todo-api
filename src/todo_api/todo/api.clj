(ns todo-api.todo.api
  (:require
    [todo-api.spec-tools :as spec-tools]
    [todo-api.storage.utils :refer [check-key-exist-or-throw]]
    [todo-api.storage.api :as store]
    [todo-api.storage.atom]
    [todo-api.storage.edn-file]
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
    (store/assoc id new-todo)
    id))

(defn read-todo
  [id]
  (spec-tools/check-spec-or-throw ::todo-spec/id id)
  (check-key-exist-or-throw id)
  (-> (store/get id)
    (assoc :id id)))

(defn update-todo
  [id name description]
  (spec-tools/check-spec-or-throw ::todo-spec/todo
    {:name name
     :description description})
  (check-key-exist-or-throw id)
  (store/update id #(update-todo-entry % name description)))

(defn delete-todo
  [id]
  (spec-tools/check-spec-or-throw ::todo-spec/id id)
  (check-key-exist-or-throw id)
  (store/dissoc id))

(defn list-todos
  []
  (let [keys (store/keys)]
    (map read-todo keys)))