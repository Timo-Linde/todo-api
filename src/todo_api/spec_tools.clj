(ns todo-api.spec-tools
  (:require
    [clojure.spec.alpha :as s]))

(defn check-spec-or-throw
  [spec data]
  (when-not (s/valid? spec data)
    (throw (IllegalArgumentException. (s/explain spec data)))))