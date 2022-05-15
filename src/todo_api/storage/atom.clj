(ns todo-api.storage.atom
  (:require
    [todo-api.storage.api :as api]))

(def ^:private store (atom {}))

(defn- fetch
  []
  @store)

(defn- persist
  [new-store]
  (reset! store new-store))

(defn- with-fetch-persist
  [f]
  (-> (fetch)
    (f)
    (persist)))

(defn- with-fetch
  [f]
  (f (fetch)))

(defmethod api/assoc :atom
  [k v]
  (with-fetch-persist
    (fn [store]
      (assoc store k v))))

(defmethod api/dissoc :atom
  [k]
  (with-fetch-persist
    (fn [store]
      (dissoc store k))))

(defmethod api/get :atom
  [k]
  (with-fetch
    (fn [store]
      (get store k))))

(defmethod api/update :atom
  [k f & args]
  (with-fetch-persist
    (fn [store]
      (apply update store k f args))))

(defmethod api/assoc-in :atom
  [ks v]
  (with-fetch-persist
    (fn [store]
      (assoc-in store ks v))))

(defmethod api/get-in :atom
  [ks]
  (with-fetch
    (fn [store]
      (get-in store ks))))

(defmethod api/update-in :atom
  [ks f & args]
  (with-fetch-persist
    (fn [store]
      (apply update-in store ks f args))))

(defmethod api/exists? :atom
  [k]
  (with-fetch
    (fn [store]
      (->> (if (vector? k) k (vector k))
        (get-in store)
        (some?)))))

(defmethod api/keys :atom
  []
  (with-fetch
    (fn [store]
      (keys store))))

(defmethod api/fetch :atom
  []
  (fetch))

(defmethod api/persist :atom
  [new-store]
  (persist new-store))