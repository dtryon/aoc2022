(ns day3
  (:require [utils :refer [read-file]]))

(defn first-star []
  (->> (read-file "src/day3.test.input")))

(comment
  (first-star))

(defn second-star []
  (->> (read-file "src/day3.input")))

(comment
  (second-star))
