(defproject epl "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [instaparse "1.4.0"]]
  :profiles {:dev {:dependencies [[speclj "3.2.0"]]}}
  :plugins [[speclj "3.2.0"]]
  :test-paths ["spec"]
  :main epl.core)
