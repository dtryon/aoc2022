(ns day6
  (:require
    [utils :refer [read-file]]))

(defn first-star []
  (->> (read-file "src/day6.input")
       (first)
       (partition 4 1)
       (keep-indexed #(if (= (count (set %2)) 4) %1 nil))
       (first)
       (+ 4)))

(comment
  (first-star))

(defn second-star []
  (->> (read-file "src/day6.input")
       (first)
       (partition 14 1)
       (keep-indexed #(if (= (count (set %2)) 14) %1 nil))
       (first)
       (+ 14)))

(comment
  (second-star))
