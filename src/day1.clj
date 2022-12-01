(ns day1
  (:require [utils :refer [read-file]]))


(defn first-star []
  (->> (read-file "src/day1.input")
      (partition-by #(empty? %))
      (filter #(seq (first %)))
      (map #(apply + (map (fn [n] (Integer/parseInt n)) %)))
      (apply max)))

(comment
  (first-star))

(defn second-star []
  (->> (read-file "src/day1.input")
      (partition-by #(empty? %))
      (filter #(seq (first %)))
      (map #(apply + (map (fn [n] (Integer/parseInt n)) %)))
      ((comp reverse sort))
      (take 3)
      (apply +)))

(comment
  (second-star))



