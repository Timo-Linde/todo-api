(ns todo-api.storage.edn-file
  (:require
    [clojure.edn :as edn]
    [todo-api.storage.api :as api]))

(def ^:private filename "resources/storage.edn")

(defmethod api/fetch :edn-file
  []
  (-> filename
    (slurp)
    (edn/read-string)))

(defmethod api/persist :edn-file
  [new-todos]
  (->> new-todos
    (pr-str)
    (spit filename)))
