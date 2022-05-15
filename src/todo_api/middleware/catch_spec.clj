(ns todo-api.middleware.catch-spec
  (:require
    [ring.util.response :as rr]))

(defn- handle-unknown-error
  [ex]
  (println "Unknown Api Error:" (pr-str ex))
  (rr/status 500))

(defn catch-spec-mw
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception ex
        (let [{:keys [type msg]} (ex-data ex)]
          (case type
            :invalid-spec (rr/not-found msg)
            :invalid-key (rr/not-found msg)
            (handle-unknown-error ex)))))))