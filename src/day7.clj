(ns day7
  (:require [clojure.string :as str]
            [utils :refer [read-file]]))


(defn build-dirs [result line]
  (cond (str/starts-with? line "$ cd ..") (assoc result :current-dir (vec (drop-last (:current-dir result))))
        (str/starts-with? line "$ cd") (let [dir (last (str/split line #"\$ cd ")) current-dir (conj (:current-dir result) dir)]
                                         (assoc result :current-dir current-dir))
        (str/starts-with? line "$ ls") result
        (str/starts-with? line "dir") (let [child-dir (last (str/split line #"dir "))
                                            current-dir (:current-dir result)
                                            dirs (:dirs result)
                                            dir (conj (get dirs current-dir []) child-dir)
                                            new-dirs (assoc dirs current-dir dir)]
                                        (assoc result :dirs new-dirs))
        (some? (re-find #"\d+" line)) (let [child-file (first (str/split line #" "))
                                            current-dir (:current-dir result)
                                            dirs (:dirs result)
                                            dir (conj (get dirs current-dir []) (Integer/parseInt child-file))
                                            new-dirs (assoc dirs current-dir dir)]
                                        (assoc result :dirs new-dirs))))

(defn resolve-dir-names [dirs]
  (let [all-dirs (seq dirs)]
    (into {} (map (fn [[k v]]
                    [(str/join "/" k) (map #(cond (string? %) (str/join "/" (conj k %)) :else %) v)]) all-dirs))))

(defn resolve-values [values lookup]
  (let [head (first values)
        tail (rest values)]
    (cond (nil? head) 0
          (int? head) (+ head (resolve-values tail lookup))
          :else (+ (resolve-values tail lookup)
                   (resolve-values (get lookup head) lookup)))))

(defn resolve-dir-values [dirs]
  (let [all-dirs (seq dirs)]
    (into {} (map (fn [[k v]]
                    [k (resolve-values v dirs)]) all-dirs))))

(defn first-star []
  (->> (read-file "src/day7.input")
       (reduce build-dirs { :current-dir [] :dirs {} })
       (:dirs)
       (resolve-dir-names)
       (resolve-dir-values)
       (vals)
       (filter #(>= 100000 %))
       (apply +)))

(comment
  (first-star))


(defn find-delete-target [values]
  (let [root (first values)
        unused (- 70000000 root)]
    (println unused)
    (filter #(<= 30000000 (+ unused %)) values)))

(defn second-star []
  (->> (read-file "src/day7.input")
       (reduce build-dirs { :current-dir [] :dirs {} })
       (:dirs)
       (resolve-dir-names)
       (resolve-dir-values)
       (vals)
       ((comp reverse sort))
       (find-delete-target)
       ((comp first sort))))

(comment
  (second-star))
