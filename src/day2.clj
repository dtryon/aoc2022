(ns day2
  (:require [utils :refer [read-file]]))


(defn first-star []
  (->> (read-file "src/day2.test.input")))

(comment
  (first-star))

(defn second-star []
  (->> (read-file "src/day2.input")))

(comment
  (second-star))
