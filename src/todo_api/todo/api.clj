(ns todo-api.todo.api
  (:require
    [todo-api.spec-tools :as spec-tools]
    [todo-api.storage.api :refer [fetch persist]]
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
  (let [new-todo
        {:id          (random-uuid)
         :name        name
         :description description
         :tasks {}}]
    (spec-tools/check-spec-or-throw ::todo-spec/todo new-todo)
    (-> (fetch)
      (assoc (:id new-todo) (dissoc new-todo :id))
      (persist))
    (:id new-todo)))

(defn read-todo
  [id]
  (spec-tools/check-spec-or-throw ::todo-spec/id id)
  (-> (fetch)
    (get id)))

(defn update-todo
  [id name description]
  (spec-tools/check-spec-or-throw ::todo-spec/todo
    {:id id
     :name name
     :description description
     :tasks {}})
  (-> (fetch)
    (update id update-todo-entry name description)
    (persist)))

(defn delete-todo
  [id]
  (spec-tools/check-spec-or-throw ::todo-spec/id id)
  (-> (fetch)
    (dissoc id)
    (persist)))

(defn list-todos
  []
  (fetch))