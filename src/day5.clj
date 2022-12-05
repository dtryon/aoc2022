(ns day5
  (:require
    [clojure.string :as str]
    [utils :refer [read-file]]))

(defn parse-stacks [s]
  (->> s
    (map #(mapv (vec %) (take (/ (count %) 4) (iterate (fn [n] (+ 4 n)) 1))))
    (reverse)
    (apply mapv vector)
    (map rest)
    (map #(filter (fn [s] (not (= \space s))) %))
    (map reverse)
    (map vec)
    vec))

(defn parse-moves [m]
  (->> m
       (map #(str/split % #" from "))
       (map (fn [[n moves]]
              [(Integer/parseInt (second (str/split n #"move ")))
               (map #(Integer/parseInt %) (str/split moves #" to "))]))))

(defn make-move [stacks move rev]
  (let [[n [s d]] move
        src (nth stacks (dec s))
        dest (nth stacks (dec d))
        [src-items src-done] (split-at n src)
        dest-done (concat (rev src-items) dest)]
  (assoc (assoc stacks (dec s) src-done) (dec d) dest-done)))

(defn make-moves [stacks move rev]
  (cond (empty? move) stacks
        :else (recur (make-move stacks (first move) rev) (rest move) rev)))


(defn first-star []
  (let [[s m] (->> (read-file "src/day5.input")
       (partition-by #(empty? %))
       (filter #(seq (first %))))]
    (->> (make-moves (parse-stacks s) (parse-moves m) reverse)
         (map first)
         (apply str))))

(comment
  (first-star))

(defn second-star []
  (let [[s m] (->> (read-file "src/day5.input")
       (partition-by #(empty? %))
       (filter #(seq (first %))))]
    (->> (make-moves (parse-stacks s) (parse-moves m) identity)
         (map first)
         (apply str))))

(comment
  (second-star))

