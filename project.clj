(defproject flycouchdb-webproject-example "0.1.0-SNAPSHOT"
  :description "Example of a webproject for FlyCouchDB"
  :url "https://github.com/haduart/flycouchdb-webproject-example"
  :license {:name "BSD"
            :url "http://www.opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.2"]
                 [cheshire "5.4.0"]
                 [clj-http "0.7.8"]
                 [org.clojure/data.json "0.2.4"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [clj-http "1.0.1"]
                 [flycouchdb "0.2.1"]]

  :plugins [[lein-ring "0.8.10"]
            [lein-midje "3.0.0"]]
  :min-lein-version "2.0.0"
  :ring {:handler flycouchdb-webproject-example.handler/app
         :init    flycouchdb-webproject-example.handler/init}
  :main flycouchdbwebprojectexample.handler

  :profiles {:dev        {:dependencies [[javax.servlet/servlet-api "2.5"]
                                         [ring-mock "0.1.5"]
                                         [midje "1.6.0"]]}
             :production {:dependencies [[javax.servlet/servlet-api "2.5"]]}})

