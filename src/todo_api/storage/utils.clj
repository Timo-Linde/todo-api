(ns todo-api.storage.utils
  (:require [todo-api.storage.api :as store]))

(defn check-key-exist-or-throw
  [key]
  (when-not (store/exists? key)
    (throw
      (ex-info "Key invalid!" {:type :invalid-key
                               :msg  (str "Key not found: '" key "'")}))))