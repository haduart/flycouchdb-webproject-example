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
            [clojure.data.json :as json])
  (:import [java.io File]
           [java.net URL URLDecoder]
           [org.jboss.vfs VFS VirtualFile VirtualFileFilter]))

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
  ;(clojure.pprint/pprint (.getProtocol folder-path))
  ;(->>
  ;  (.getPath folder-path)
  ;  (. URLDecoder decode)
  ;  (File.)
  ;  (.getAbsolutePath)
  ;  (. VFS getChild)
  ;  (.getChildren)
  ;  first
  ;  (.openStream)
  ;  slurp
  ;  clojure.pprint/pprint)
  (println "Migrations done!"))

(def app
  (handler/site (execute-routing)))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT")))]
    (jetty/run-jetty (execute-routing)
      {:port port :join? false})))