(ns epl.stdlib)

(defn factorial [n]
  (apply * (range 1 (inc n))))

(defn reciprocal [n]
  (/ 1 (float n)))

(defn signum [n]
  (cond (pos? n) 1
        (zero? n) 0
        (neg? n) -1))

(defn exponential [n]
  (. Math exp n))

(defn absolute-value [n]
  (. Math abs n))

(defn natural-logarithm [n]
  (. Math log n))

(defn y-times-pi [n]
  (* Math/PI n))

(defn ceiling [n]
  (. Math ceil n))

(defn floor [n]
  (. Math floor n))

(defn power [a b]
  (. Math pow a b))

(defn index [n]
  (vec (range 1 (inc n))))

(defn monadic-function [function-name]
  (case function-name
    :absolute-value absolute-value
    :ceiling ceiling
    :floor floor
    :natural-logarithm natural-logarithm
    :exponential exponential
    :identity identity
    :negative -
    :reciprocal reciprocal
    :factorial factorial
    :signum signum
    :y-times-pi y-times-pi
    :index index))

(defn dyadic-function [function-name]
  (case function-name
    :add +
    :subtract -
    :multiply *
    :divide /
    :maximum max
    :minimum min
    :power power))
