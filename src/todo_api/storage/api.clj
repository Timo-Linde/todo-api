(ns todo-api.storage.api
  (:refer-clojure :exclude [get get-in update update-in assoc assoc-in exists? dissoc keys]))

(def implementation
  (constantly
    #_:edn-file
    :atom))

(defmulti assoc
  implementation)

(defmulti dissoc
  implementation)

(defmulti get
  implementation)

(defmulti update
  implementation)

(defmulti assoc-in
  implementation)

(defmulti get-in
  implementation)

(defmulti update-in
  implementation)

(defmulti exists?
  implementation)

(defmulti keys
  implementation)

;;; Remove Old implementation:
(defmulti fetch
  implementation)

(defmulti persist
  implementation)