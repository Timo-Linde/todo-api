(ns todo-api.task.rest
  (:require
    [todo-api.task.api :as task-api]
    [ring.util.response :as rr]))

(defn list-tasks
  [todo-id]
  (-> todo-id
    (parse-uuid)
    (task-api/list-tasks)
    (rr/response)))

(defn read-task
  [todo-id task-id]
  (->
    (task-api/read-task (parse-uuid todo-id) (parse-uuid task-id))
    (rr/response)))

(defn create-task
  [todo-id name]
  (let [new-task-id (task-api/create-task (parse-uuid todo-id) name)
        url (str "http://localhost:1337/todos/" todo-id "/task/" new-task-id)]
    (rr/created url)))

(defn update-task
  [todo-id task-id name]
  (->
    (task-api/update-task (parse-uuid todo-id) (parse-uuid task-id) name)
    (rr/response)))

(defn delete-task
  [todo-id task-id]
  (->
    (task-api/delete-task (parse-uuid todo-id) (parse-uuid task-id))
    (rr/response)))