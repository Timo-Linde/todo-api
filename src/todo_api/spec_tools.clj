(ns todo-api.spec-tools
  (:require
    [clojure.spec.alpha :as s]))

(defn check-spec-or-throw
  [spec data]
  (when-not (s/valid? spec data)
    (throw (ex-info "Data invalid!" {:type :invalid-spec
                                     :msg  (s/explain-str spec data)}))))