(ns todo-api.storage.api)

(defmulti fetch
  (constantly :edn-file))

(defmulti persist
  (constantly :edn-file))