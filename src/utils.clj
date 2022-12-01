(ns utils
  (:require [clojure.java.io :refer [reader]]))

(defn read-file
  "read file line by line"
  [file-name]
  (with-open [rdr (reader file-name)]
    (reduce conj [] (line-seq rdr))))
