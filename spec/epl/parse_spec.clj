(ns epl.parse-spec
  (:require [speclj.core :refer :all]
            [epl.parse :refer :all]))

(describe "parsing"
  (describe "decimal representations"
    (it "parses a single-digit number"
      (should= [:expression [:numeric-constant 1]] (first (parse "1"))))

    (it "parses a multi-digit number"
      (should= [:expression [:numeric-constant 1000000]] (first (parse "1000000"))))

    (it "parses a decimal number"
      (should= [:expression [:numeric-constant 10.5]] (first (parse "10.5"))))

    (it "parses a decimal number without a leading 0"
      (should= [:expression [:numeric-constant 0.5]] (first (parse "0.5"))))

    (it "parses a negative number"
      (should= [:expression [:numeric-constant -5]] (first (parse "-5"))))

    (it "parses a negative decimal number"
      (should= [:expression [:numeric-constant -0.5]] (first (parse "-0.5")))))

  (describe "character constants"
    (it "parses a double-quoted character constant"
      (should= [:expression [:character-constant "roflcopter"]] (first (parse "\"roflcopter\""))))

    (it "parses a single-quoted character constant"
      (should= [:expression [:character-constant "roflcopter"]] (first (parse "'roflcopter'")))))

  (describe "variable identifiers"
    (it "parses a variable identifier with letters only"
      (should= [:expression [:variable-identifier "roflcopter"]] (first (parse "roflcopter"))))

    (it "parses a variable identifier with letters and numbers"
      (should= [:expression [:variable-identifier "roflcopter11"]] (first (parse "roflcopter11"))))

    (it "parses a variable identifier with letters, numbers, and underscores"
      (should= [:expression [:variable-identifier "roflcopter_11"]] (first (parse "roflcopter_11"))))

    (it "handles uppercase letters in variable identifiers"
      (should= [:expression [:variable-identifier "RoflCopter_11"]] (first (parse "RoflCopter_11"))))))
