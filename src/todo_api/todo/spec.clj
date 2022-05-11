(ns todo-api.todo.spec
  (:require
    [clojure.spec.alpha :as s]
    [todo-api.task.spec :as task]))

(s/def ::id uuid?)
(s/def ::name string?)
(s/def ::description string?)

(s/def ::todo (s/keys :req-un [::id ::name ::description ::task/tasks]))