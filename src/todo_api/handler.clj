(ns todo-api.handler
  (:require
    [compojure.core :as cp]
    [compojure.route :as route]

    [ring.middleware.defaults :as ring-defaults]
    [ring.middleware.format :as ring-mw-format]
    [ring.middleware.resource :as ring-mw-resource]
    [ring.util.response :as rr]
    [todo-api.middleware.catch-spec :as catch-spec-mw]
    [todo-api.todo.rest :as todo-rest]))

(defn- wrap-response
  [handler]
  (fn [req]
    (let [resp (handler req)]
      (println (pr-str resp))
      resp)))

(defn routes
  []
  (cp/routes
    (cp/GET "/todos" _req
      (todo-rest/list-todos))
    (cp/GET "/todos/:id" [id]
      (todo-rest/read-todo id))
    (cp/POST "/todos" [name description]
      (todo-rest/create-todo name description))
    (cp/PATCH "/todos/:id" [id name description]
      (todo-rest/update-todo id name description))
    (cp/DELETE "/todos/:id" [id]
      (todo-rest/delete-todo id))

    (route/not-found "Not Found!")
    ))


(defn new-handler
  []
  (->
    (routes)
    (catch-spec-mw/catch-spec-mw)
    ;;(wrap-response)
    ;;(ring-mw-resource/wrap-resource "public")
    (ring-mw-format/wrap-restful-format)
    (ring-defaults/wrap-defaults ring-defaults/api-defaults)
    ))