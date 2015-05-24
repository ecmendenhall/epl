(ns epl.eval
  (:require [epl.stdlib :refer :all]))

(def variable-bindings (atom {}))

(defn lookup-binding [variable]
  (get @variable-bindings variable))


(defn evaluate-identifier [[expression-type & args]]
  (case expression-type
    :monadic-identifier (apply monadic-function args)
    :dyadic-identifier (apply dyadic-function args)
    :variable-identifier (apply lookup-binding args)
    :character-constant (apply str args)
    :numeric-constant (apply vector args)))

(declare evaluate-expression)

(defn eval-dyadic-expression [[left operator right]]
  (vec (map (evaluate-identifier operator) (evaluate-identifier left) (evaluate-expression right))))

(defn eval-monadic-expression [[operator args]]
  (vec (map (evaluate-identifier operator) (evaluate-expression args))))

(defn eval-assignment [[target args]]
  (swap! variable-bindings #(assoc % target (evaluate-identifier args))))

(defn evaluate-expression [expression]
  (cond (= 2 (count expression))
        (evaluate-identifier (second expression))
        (= :target (first (second expression)))
        (eval-assignment (rest expression))
        (= :monadic-identifier (first (second expression)))
        (eval-monadic-expression (rest expression))
        :else
        (eval-dyadic-expression (rest expression))))


