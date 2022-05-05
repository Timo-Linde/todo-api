(defproject todo-api "0.1.0-SNAPSHOT"
  :description "TODO Api Implementation"
  :url ""
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :main ^:skip-aot todo-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
