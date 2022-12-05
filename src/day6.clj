(ns day6
  (:require
    ;[clojure.string :as str]
    [utils :refer [read-file]]))

(defn first-star []
  (->> (read-file "src/day6.test.input")))

(comment
  (first-star))

(defn second-star []
  (->> (read-file "src/day6.input")))

(comment
  (second-star))
