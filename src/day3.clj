(ns day3
  (:require
    [clojure.string :as str]
    [clojure.set :refer [intersection]]
    [utils :refer [read-file]]))

(def alpha "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn first-star []
  (->> (read-file "src/day3.input")
       (map #(partition (/ (count %) 2) %))
       (map #(map set %))
       (map #(apply intersection %))
       (map #(+ 1 (str/index-of alpha (first %))))
       (apply +)))

(comment
  (first-star))

(defn second-star []
  (->> (read-file "src/day3.input")
       (partition 3)
       (map #(map set %))
       (map #(apply intersection %))
       (map #(+ 1 (str/index-of alpha (first %))))
       (apply +)))

(comment
  (second-star))
