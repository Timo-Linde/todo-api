(ns todo-api.swagger.generate
  (:require
    [ring.util.response :as rr]
    [spec-tools.swagger.core :as swagger]
    [todo-api.todo.spec :as todo-spec]))

(def json
  (swagger/swagger-spec
    {:swagger "2.0"
     :info    {:version     "0.0.1"
               :title       "TODO-API"
               :description "A Api to handle Todos and it's tasks"
               :contact     {:name  "Timo Linde"
                             :email "info@timo-linde.de"
                             :url   "https://timo-linde.de"}
               }
     :paths   {"/todos" {:get  {:responses {:default {:description ""}}}
                         :post {:summary             "Todo Api"
                                :description         ""
                                :tags                []
                                ::swagger/parameters {:body ::todo-spec/todo}
                                ::swagger/responses  {404 {:description "404"}}}}}}))

(def json-response (rr/response json))