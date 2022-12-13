(ns day8
  (:require
    [utils :refer [read-file]]))

(def counted (atom []))

(defn count-trees [coll]
  (reduce (fn [acc row]
            (+ acc (:total (reduce (fn [tree-acc tree]
                                     ; (println (str "tree " tree))
                                     ;(println (str "counted " @counted))
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

(defn second-star []
  (->> (read-file "src/day8.input")))

(comment
  (second-star))
