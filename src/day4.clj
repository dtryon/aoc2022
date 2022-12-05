(ns day4
  (:require
    [clojure.string :as str]
    [utils :refer [read-file]]))

(defn has-contains? [[a b] [x y]]
  (cond (and (>= a x) (<= b y)) 1
        (and (>= x a) (<= y b)) 1
        :else 0))

(defn first-star []
  (->> (read-file "src/day4.input")
       (map #(str/split % #","))
       (map #(map (fn [xs] (map (fn [n] (Integer/parseInt n)) (str/split xs #"-"))) %))
       (map #(has-contains? (first %) (second %)))
       (apply +)))

(comment
  (first-star))

(defn has-overlap? [[a b] [x y]]
  (cond (and (>= a x) (<= b y)) 1
        (and (>= x a) (<= y b)) 1
        (and (<= a x) (>= b x)) 1
        (and (<= a y) (>= b y)) 1
        :else 0))

(defn second-star []
  (->> (read-file "src/day4.input")
       (map #(str/split % #","))
       (map #(map (fn [xs] (map (fn [n] (Integer/parseInt n)) (str/split xs #"-"))) %))
       (map #(has-overlap? (first %) (second %)))
       (apply +)))

(comment
  (second-star))
