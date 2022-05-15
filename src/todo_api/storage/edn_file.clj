(ns todo-api.storage.edn-file
  (:require
    [clojure.edn :as edn]
    [todo-api.storage.api :as api]))

(def ^:private filename "resources/storage.edn")

(defn- fetch
  []
  (-> filename
    (slurp)
    (edn/read-string)))

(defn- persist
  [new-store]
  (->> new-store
    (pr-str)
    (spit filename)))

(defn- with-fetch-persist
  [f]
  (-> (fetch)
    (f)
    (persist)))

(defn- with-fetch
  [f]
  (f (fetch)))

(defmethod api/assoc :edn-file
  [k v]
  (with-fetch-persist
    (fn [store]
      (assoc store k v))))

(defmethod api/dissoc :edn-file
  [k]
  (with-fetch-persist
    (fn [store]
      (dissoc store k))))

(defmethod api/get :edn-file
  [k]
  (with-fetch
    (fn [store]
      (get store k))))

(defmethod api/update :edn-file
  [k f & args]
  (with-fetch-persist
    (fn [store]
      (apply update store k f args))))

(defmethod api/assoc-in :edn-file
  [ks v]
  (with-fetch-persist
    (fn [store]
      (assoc-in store ks v))))

(defmethod api/get-in :edn-file
  [ks]
  (with-fetch
    (fn [store]
      (get-in store ks))))

(defmethod api/update-in :edn-file
  [ks f & args]
  (with-fetch-persist
    (fn [store]
      (apply update-in store ks f args))))

(defmethod api/exists? :edn-file
  [k]
  (with-fetch
    (fn [store]
      (->> (if (vector? k) k (vector k))
        (get-in store)
        (some?)))))

(defmethod api/keys :edn-file
  []
  (with-fetch
    (fn [store]
      (keys store))))
