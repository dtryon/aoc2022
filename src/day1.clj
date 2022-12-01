(ns day1
  (:require [utils :refer [read-file]]))


(defn first-star []
  (->> (read-file "src/day1.input")
      (partition-by #(> (count %) 0))
      (filter #(> (count (first %)) 0))
      (map #(map (fn [n] (Integer/parseInt n)) %))
      (map #(apply + %))
      (apply max)))

(comment
  (first-star))

(defn second-star []
  (->> (read-file "src/day1.input")
      (partition-by #(> (count %) 0))
      (filter #(> (count (first %)) 0))
      (map #(map (fn [n] (Integer/parseInt n)) %))
      (map #(apply + %))
      (sort)
      (reverse)
      (take 3)
      (apply +)))

(comment
  (second-star))



