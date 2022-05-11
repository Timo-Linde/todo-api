(ns todo-api.task.api
  (:require
    [todo-api.spec-tools :as spec-tools]
    [todo-api.storage.api :refer [fetch persist]]
    [todo-api.task.spec :as task-spec]
    [todo-api.todo.api :as todo-api]
    [todo-api.todo.spec :as todo-spec]))

(defn create-task
  [todo-id name]
  (spec-tools/check-spec-or-throw ::todo-spec/id todo-id)
  (let [new-task
        {:id   (random-uuid)
         :name name}]
    (spec-tools/check-spec-or-throw ::task-spec/task new-task)
    (-> (fetch)
      (assoc-in [todo-id :tasks (:id new-task)]
        (dissoc new-task :id))
      (persist))))

(defn read-task
  [todo-id task-id]
  (spec-tools/check-spec-or-throw ::todo-spec/id todo-id)
  (spec-tools/check-spec-or-throw ::task-spec/id task-id)
  (-> (fetch)
    (get-in [todo-id :tasks task-id])))

(defn update-task
  [todo-id task-id name]
  (spec-tools/check-spec-or-throw ::todo-spec/id todo-id)
  (spec-tools/check-spec-or-throw ::task-spec/id task-id)
  (spec-tools/check-spec-or-throw ::task-spec/name name)
  (-> (fetch)
    (update-in [todo-id :tasks task-id]
      assoc :name name)
    (persist)))

(defn delete-task
  [todo-id task-id]
  (spec-tools/check-spec-or-throw ::todo-spec/id todo-id)
  (spec-tools/check-spec-or-throw ::task-spec/id task-id)
  (-> (fetch)
    (update-in [todo-id :tasks] dissoc task-id)
    (persist)))

(defn list-tasks
  [todo-id]
  (-> todo-id
    (todo-api/read-todo)
    (:tasks)))
