(ns todo-api.task.spec
  (:require
    [clojure.spec.alpha :as s]))

(s/def ::id uuid?)
(s/def ::name string?)
(s/def ::task (s/keys :req-un [::name]))

(s/def ::tasks (s/map-of ::id ::task))