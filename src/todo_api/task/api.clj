(ns todo-api.task.api
  (:require
    [todo-api.spec-tools :as spec-tools]
    [todo-api.storage.api :as storage]
    [todo-api.storage.atom]
    [todo-api.storage.utils :refer [check-key-exist-or-throw]]
    [todo-api.task.spec :as task-spec]
    [todo-api.todo.api :as todo-api]
    [todo-api.todo.spec :as todo-spec]))

(defn create-task
  [todo-id name]
  (spec-tools/check-spec-or-throw ::todo-spec/id todo-id)
  (let [task-id (random-uuid)
        new-task {:name name}]
    (spec-tools/check-spec-or-throw ::task-spec/task new-task)
    (storage/assoc-in [todo-id :tasks task-id] new-task)
    task-id))

(defn read-task
  [todo-id task-id]
  (spec-tools/check-spec-or-throw ::todo-spec/id todo-id)
  (spec-tools/check-spec-or-throw ::task-spec/id task-id)
  (check-key-exist-or-throw [todo-id :tasks task-id])
  (->> (storage/get-in [todo-id :tasks task-id])
    (assoc :id task-id)))

(defn update-task
  [todo-id task-id name]
  (spec-tools/check-spec-or-throw ::todo-spec/id todo-id)
  (spec-tools/check-spec-or-throw ::task-spec/id task-id)
  (spec-tools/check-spec-or-throw ::task-spec/name name)
  (check-key-exist-or-throw [todo-id :tasks task-id])
  (storage/update-in [todo-id :tasks task-id] assoc :name name))

(defn delete-task
  [todo-id task-id]
  (spec-tools/check-spec-or-throw ::todo-spec/id todo-id)
  (spec-tools/check-spec-or-throw ::task-spec/id task-id)
  (check-key-exist-or-throw [todo-id :tasks task-id])
  (storage/update-in [todo-id :tasks]
    dissoc task-id))

(defn list-tasks
  [todo-id]
  (-> todo-id
    (todo-api/read-todo)
    (:tasks)))
