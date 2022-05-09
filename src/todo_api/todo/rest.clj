(ns todo-api.todo.rest
  (:require
    [todo-api.todo.api :as todo-api]
    [ring.util.response :as rr]))

(defn list-todos
  []
  (rr/response
    (todo-api/list-todos)))

(defn read-todo
  [id]
  (-> id
    (parse-uuid)
    (todo-api/read-todo)
    (rr/response)))

(defn create-todo
  [name description]
  (let [new-id (todo-api/create-todo name description)
        url (str "http://localhost:1337/todos/" new-id)]
    (rr/created url)))

(defn update-todo
  [id name description]
  (-> id
    (parse-uuid)
    (todo-api/update-todo name description)
    (rr/response)))

(defn delete-todo
  [id]
  (-> id
    (parse-uuid)
    (todo-api/delete-todo)
    (rr/response)))