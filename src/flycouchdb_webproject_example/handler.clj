(ns flycouchdb-webproject-example.handler
  (:use [compojure.core]
        [clojure.java.io :only (file resource)]
        [flycouchdb.migration :only (migrate flycouchdb)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [cheshire.core :refer :all]
            [cheshire.generate :refer [add-encoder encode-str remove-encoder]]
            [ring.util.response :as resp]
            [ring.adapter.jetty :as jetty]
            [clojure.data.json :as json]))

(defn json-response [data & [status]]
  {:status  (or status 200)
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str data)})

(defroutes app-routes
  (GET "/" req (resp/redirect (str (:context req) "/index.html")))
  (GET "/api/test/" [] (json-response {:response "response"}))
  (route/resources "/")
  (route/not-found (resp/resource-response "404.html" {:root "public"})))

(defn wrap-dir-index
  "Middleware to force request for / to return index.html"
  [handler] (fn [req] (handler (update-in req [:uri] #(if (= "/" %) "/index.html" %)))))

(defn execute-routing [] (-> app-routes
                           wrap-dir-index))

(def folder-path (resource "migrations/"))

(def flydb (flycouchdb folder-path))

(defn init []
  (println "Running migrations")
  (migrate flydb)
  (println "Migrations done!"))

(def app
  (handler/site (execute-routing)))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT")))]
    (jetty/run-jetty (execute-routing)
      {:port port :join? false})))