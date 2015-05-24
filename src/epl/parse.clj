(ns epl.parse
  (:require [instaparse.core :as insta]))

(def parser
  (insta/parser
    "<S> = statement-list
     <whitespace> = <#'[\t ]*'>

     <statement-list> = statement (<'\n'> statement)*

     <statement> =
       expression whitespace

     expression =
       (target whitespace <'👈 '> whitespace expression) |
       (monadic-identifier whitespace  expression) |
       (subexpression (whitespace dyadic-identifier expression)?)

     <subexpression>  = simple-expression (<'['> llist <']'>)?

     <simple-expression> = array-identifier | <'('> expression <')'>

     target = variable-identifier (<'['> llist <']'>)?

     llist = expression? (';' expression?)*

     dyadic-identifier =
       #'[🎈🎷✨👍👎🍩🎉🙋💁🙅🙆💥🚀🍍💃🙌💖🍕🍔🍣🍦😿😾👯😸😻🙀💧]' whitespace

     monadic-identifier =
       '📦 👈 ' | #'[🎈🎷✨👍👎🍩🎉🙋💁🙅🙆💥🚀🍍💃🙌💖]' whitespace

     <array-identifier> = constant | variable-identifier

     <constant> =
       character-constant | numeric-constant

     numeric-constant =
       decimal-representation (<#'[\t ]+'> decimal-representation)*

     decimal-representation =
       ((#'-?\\d+\\.?\\d*') | (#'-?\\.\\d+'))

     character-constant =
       (<'\"'> #'[^\"]*' <'\"'>) | (<'\\''> #'[^\\']*' <'\\''>)

     variable-identifier = #'[A-Za-z][A-Za-z0-9_]*'"))

(def dyadic-identifiers
  {"🎈" :residue
   "🎷" :base-x-logarithm
   "✨" :power
   "👍" :maximum
   "👎" :minimum
   "🍩" :circular-and-hyperbolic-trigonometry
   "🎉" :binomial
   "🙋" :add
   "💁" :subtract
   "🙅" :multiply
   "🙆" :divide
   "💥" :without
   "🚀" :index-of
   "🍍" :rotate-along-last-axis
   "💃" :take
   "🙌" :reshape
   "💖" :format-specified
   "🍕" :and
   "🍔" :or
   "🍣" :not-and
   "🍦" :not-or
   "😿" :less
   "😾" :less-or-equal
   "👯" :equal
   "😸" :greater-or-equal
   "😻" :greater
   "🙀" :not-equal
   "💧" :drop })

(defn select-dyadic-identifier [emoji]
  [:dyadic-identifier (dyadic-identifiers emoji)])

(def monadic-identifiers
  {"📦 👈" :quad-assignment
   "🎈" :absolute-value
   "🎷" :natural-logarithm
   "✨" :exponential
   "👍" :ceiling
   "👎" :floor
   "🍩" :y-times-pi
   "🎉" :factorial
   "🙋" :identity
   "💁" :negative
   "🙅" :signum
   "🙆" :reciprocal
   "💥" :not
   "🚀" :index
   "🍍" :reverse-along-last-axis
   "💃" :mix
   "🙌" :shape-of
   "💖" :format-default })

(defn select-monadic-identifier [emoji]
  [:monadic-identifier (monadic-identifiers emoji)])

(def transform-options
   {:dyadic-identifier select-dyadic-identifier
    :monadic-identifier select-monadic-identifier
    :decimal-representation read-string})

(defn parse [input]
  (->> (parser input)
       (insta/transform transform-options)))
