(ns day2
  (:require
    [clojure.string :as str]
    [clojure.core.match :refer [match]]
    [utils :refer [read-file]]))

(defn convert [s]
  (match [s]
         ["A"] 1
         ["B"] 2
         ["C"] 3
         ["X"] 1
         ["Y"] 2
         ["Z"] 3))

(defn win [p1 p2]
  (match [p1 p2]
         [1 2] true
         [2 1] false
         [1 3] false
         [3 1] true
         [2 3] true
         [3 2] false))

(defn game [[p1 p2]]
  (cond (= p1 p2) (+ 3 p2)
        (win p1 p2) (+ 6 p2) 
        (not (win p1 p2)) p2))

(defn first-star []
  (->> (read-file "src/day2.input")
       (map #(map convert (str/split % #" ")))
       (map game)
       (apply +)))

((first-star)
  comment)

(defn get-winner [p1]
  (match [p1]
         [1] 2
         [2] 3
         [3] 1))

(defn get-loser [p1]
  (match [p1]
         [1] 3
         [2] 1
         [3] 2))

(defn game2 [moves]
  (let [[p1 p2] moves]
    (cond (= p2 2) (+ 3 p1)
          (= p2 3) (+ 6 (get-winner p1)) 
          (= p2 1) (get-loser p1))))

(defn second-star []
  (->> (read-file "src/day2.input")
       (map #(map convert (str/split % #" ")))
       (map game2)
       (apply +)))

(comment
  (second-star))



