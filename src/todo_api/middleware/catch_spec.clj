(ns todo-api.middleware.catch-spec
  (:require
    [ring.util.response :as rr]))

(defn catch-spec-mw
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch IllegalArgumentException ex
        (println "Exception catched:" (pr-str ex))
        (rr/not-found (pr-str ex))))))