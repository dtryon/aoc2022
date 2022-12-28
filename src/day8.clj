(ns day8
  (:require
    [utils :refer [read-file]]))

(def counted (atom []))

(defn count-trees [coll]
  (reduce (fn [acc row]
            (+ acc (:total (reduce (fn [tree-acc tree]
                                     (if (> (:value tree) (:max tree-acc))
                                       (let [next-max (:value tree)
                                             total (:total tree-acc)]
                                         (if (.contains @counted (:key tree))
                                             { :max next-max :total total }
                                             (do
                                               (swap! counted (fn [xs] (conj xs (:key tree))))
                                               { :max next-max :total (inc total) })))
                                       tree-acc)
                                     ) { :max -1 :total 0 } row)))) 0 coll))

(defn count-all-directions [coll]
  (let [left coll
        right (map reverse coll)
        top (apply mapv vector coll)
        bottom (map reverse top)]
    (+ (count-trees left)
       (count-trees right)
       (count-trees top)
       (count-trees bottom))))


(defn gen-key []
  (apply str (repeatedly 7 #(rand-nth "abcdefghijklmnopqrstuvwxyz0123456789"))))


(defn first-star []
  (->> (read-file "src/day8.input")
       (map vec)
       (map #(map (fn [c] { :key (gen-key)  :value (Integer/parseInt (str c)) }) %))
       (count-all-directions)))

(comment
  (first-star))

(defn search-right [grid tree x y]
  (let [new-y (inc y) v (get-in grid [x new-y])]
    (cond (nil? v) 0
          (>= v tree) 1
          :else (+ 1 (search-right grid tree x new-y)))))

(defn search-left [grid tree x y]
  (let [new-y (dec y) v (get-in grid [x new-y])]
    (cond (nil? v) 0
          (>= v tree) 1
          :else (+ 1 (search-left grid tree x new-y)))))

(defn search-up [grid tree x y]
  (let [new-x (inc x) v (get-in grid [new-x y])]
    (cond (nil? v) 0
          (>= v tree) 1
          :else (+ 1 (search-up grid tree new-x y)))))

(defn search-down [grid tree x y]
  (let [new-x (dec x) v (get-in grid [new-x y])]
    (cond (nil? v) 0
          (>= v tree) 1
          :else (+ 1 (search-down grid tree new-x y)))))

(defn scenic-score [grid]
  (map-indexed
    #(map-indexed (fn [idx tree]
                    (let [x %1 y idx]
                      (* (search-right grid tree x y)
                         (search-left grid tree x y)
                         (search-up grid tree x y)
                         (search-down grid tree x y)))) %2) grid))

(defn second-star []
  (->> (read-file "src/day8.input")
       (map #(map (fn [c] (Integer/parseInt (str c))) %))
       (map vec)
       vec
       scenic-score
       flatten
       (apply max)))

(comment
  (second-star))
