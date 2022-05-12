(ns todo-api.storage.konserve
  (:require
    [clojure.core.async :refer [<!]]
    [konserve.core :as k]
    [konserve.filestore :refer [connect-fs-store]]))

(def opts {:sync? true})
(def store (connect-fs-store "resources/store" opts))

(defn check-key-exist-or-throw
  [store key]
  (when-not (k/exists? store key)
    (ex-info "Key invalid!" {:type :invalid-key
                             :msg  (str "Key not found: '" key "'")})))