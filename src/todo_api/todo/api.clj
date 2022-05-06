(ns todo-api.todo.api
  (:require
    [todo-api.storage.api :refer [fetch persist]]
    [todo-api.storage.atom]
    [todo-api.storage.edn-file]))

(defn- update-todo-entry
  [todo name description]
  (-> todo
    (assoc :name name)
    (assoc :description description)))

(defn create-todo
  [name description]
  (-> (fetch)
    (assoc (random-uuid)
      {:name        name
       :description description})
    (persist)))

(defn read-todo
  [id]
  (-> (fetch)
    (get id)))

(defn update-todo
  [id name description]
  (-> (fetch)
    (update id update-todo-entry name description)
    (persist)))

(defn delete-todo
  [id]
  (-> (fetch)
    (dissoc id)
    (persist)))

(defn list-todos
  []
  (fetch))