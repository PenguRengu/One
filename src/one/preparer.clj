(ns one.preparer
  (:require [clojure.string :as cstr]
            [camel-snake-kebab.core :as csk]))

;; Utility
(defn vec-remove
  "remove elem in coll"
  [pos coll]
  (vec (concat (subvec coll 0 pos) (subvec coll (inc pos)))))
(defn convert-case [text lang]
  (case lang
    "java" (csk/->camelCase text)
    "clj" (csk/->kebab-case text)))
(defn replace-several [content & replacements]
  (let [replacement-list (partition 2 replacements)]
    (reduce #(apply cstr/replace %1 %2) content replacement-list)))

;; Preparing
(defn prepare-token [token cmd-token lang]
  (let [token (replace-several token #"%_op" "(" #"%_cp" ")" #"%_" " ")]
    (if (cstr/includes? cmd-token "name") (convert-case token lang) token)))
(defn prepare-text [text]
  (let [text-lines (atom (cstr/split-lines text))]
    (loop [i 0]
      (reset! text-lines (assoc @text-lines i (cstr/trim (get @text-lines i))))
      (if (or (= (get (cstr/split (get @text-lines i) #" ") 0) "//") (empty? (get @text-lines i))) (reset! text-lines (vec-remove i @text-lines)))

      (if (< i (- (count @text-lines) 1)) (recur (+ i 1))))
    (cstr/join " " @text-lines)))