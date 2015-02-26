(defproject flycouchdb-webproject-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [cheshire "5.3.1"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [com.cemerick/drawbridge "0.0.6" :exclusions [org.clojure/tools.nrepl]]
                 [ring-basic-authentication "1.0.1"]
                 [clj-http "0.7.8"]
                 [org.clojure/data.json "0.2.4"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [clj-http "0.7.8"]
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

