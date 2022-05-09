(defproject todo-api "0.1.0-SNAPSHOT"
  :description "TODO Api Implementation"
  :url ""
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [compojure "1.6.2"]
                 [http-kit "2.3.0"]
                 [ring "1.9.5"]
                 [ring-middleware-format "0.7.4"]
                 [ring/ring-defaults "0.3.3"]]
  :main ^:skip-aot todo-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
