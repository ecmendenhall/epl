(ns epl.eval-spec
  (:require [speclj.core :refer :all]
            [epl.eval :refer :all]))

(describe "evaluation"
  (describe "numeric constants"
    (it "evaluates numeric constants"
       (should= [[1 2 3]] (evaluate-identifier [:numeric-constant [1 2 3]]))))

  (describe "character constants"
    (it "evaluates character constants"
       (should= "roflcopter" (evaluate-identifier [:character-constant "roflcopter"]))))

  (describe "variable identifiers"
    (it "evaluates bound variable identifiers"
       (with-redefs [variable-bindings (atom {"wat" [5]})]
         (should= [5] (evaluate-identifier [:variable-identifier "wat"])))))

  (describe "monadic identifiers"
    (it "evaluates monadic identifiers"
      (should= identity (evaluate-identifier [:monadic-identifier :identity]))))

  (describe "monadic identifiers"
    (it "applies monadic functions to their arguments"
      (should= [5]
               (evaluate-expression [:expression [:monadic-identifier :identity] [:expression [:numeric-constant 5]]]))))

  (it "applies dyadic funcitons to their arguments"
    (should= [2 3 4]
             (evaluate-expression [:expression [:numeric-constant 1 2 3] [:dyadic-identifier :add] [:expression [:numeric-constant 1 1 1]]])))

  )
