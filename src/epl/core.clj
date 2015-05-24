(ns epl.core
  (:require [epl.parse :as parse]
            [epl.eval :as eval]))

(defn print-val [value]
  (-> (str value)
      (clojure.string/replace "]" "")
      (clojure.string/replace "[" "")
      (println)))

(defn repl []
  (do
    (print "💫  ")
    (flush))
  (let [input (read-line)]
    (print-val (eval/evaluate-expression (first (parse/parse input))))
    (recur)))

(defn -main [& args]
  (println "👍 ❌ 💯")
  (println "〰〰〰〰〰〰〰〰〰〰〰〰")
  (flush)
  (repl))
