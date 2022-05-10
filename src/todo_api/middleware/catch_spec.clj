(ns todo-api.middleware.catch-spec
  (:require
    [ring.util.response :as rr]))

(defn catch-spec-mw
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception ex
        (let [{:keys [type msg]} (ex-data ex)]
          (case type
            :invalid-spec (rr/not-found msg)
            (rr/status 500)))))))