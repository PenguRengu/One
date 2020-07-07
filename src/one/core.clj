(ns one.core
  (:gen-class)
  (:require [one.java]
            [one.clj]
            [clojure.string :as cstr]
            [camel-snake-kebab.core :as csk]))

(def lang (atom "java"))
(def indent (atom 0))
(defn convert-case [text]
  (case @lang 
    "java" (csk/->camelCase text)
    "clj" (csk/->kebab-case text)))
(defn vec-remove
  "remove elem in coll"
  [pos coll]
  (vec (concat (subvec coll 0 pos) (subvec coll (inc pos)))))
(defn replace-several [content & replacements]
  (let [replacement-list (partition 2 replacements)]
    (reduce #(apply cstr/replace %1 %2) content replacement-list)))
(defn get-cmd-templates [] 
  (case @lang
    "java" one.java/java
    "clj" one.clj/clj))
(defn get-parentheses-length [text]
  (let [paren-count (atom 0)]
    (loop [i 0]
      (let [char (str (get text i))]
        (if (= char "(") (swap! paren-count inc))
        (if (= char ")") (swap! paren-count dec)))
      (if (<= @paren-count 0) (+ 1 i)
          (if (< i (- (count text) 1)) (recur (+ i 1)))))))
(defn get-tokens [text]
  (let [chars (apply str (butlast (nthrest (vec text) 1)))
        tokens (atom [])
        token-index (atom 0)
        paren-end (atom 0)]
    (reset! tokens (conj @tokens ""))
    (loop [i 0]
      (let [char (str (get chars i))]
        (cond (and (= char " ") (>= i @paren-end)) (do
                                                     (swap! token-index inc)
                                                     (reset! tokens (conj @tokens "")))
              (and (= char "(") (>= i @paren-end)) (let [paren-token (subs chars i (+ i (get-parentheses-length (subs chars i))))]
                                                     (reset! tokens (assoc @tokens @token-index paren-token))
                                                     (reset! paren-end (+ i (count paren-token))))
              (< i @paren-end) ()
              :else (reset! tokens (assoc @tokens @token-index (str (get @tokens @token-index) char)))))

      (if (< i (- (count chars) 1)) (recur (+ i 1))))
    @tokens))
(defn match? [tokens cmd-tokens]
  (let [match (atom true)]
    (if (not= (count tokens) (count cmd-tokens)) (reset! match false)
        (loop [i 0]
          (if (and (not= (get tokens i) (get cmd-tokens i)) (not= (get (cstr/split (get cmd-tokens i) #"=") 0) "_ph")) (reset! match false))

          (if (< i (- (count tokens) 1)) (recur (+ i 1)))))
    @match))
(defn find-cmd [tokens]
  (let [cmds (vec (keys (get-cmd-templates)))
        cmd (atom "error")]
    (loop [i 0]
      (let [cmd-tokens (cstr/split (get cmds i) #" ")]
        (if (match? tokens cmd-tokens) (reset! cmd (get cmds i))))

      (if (< i (- (count cmds) 1)) (recur (+ i 1))))
    @cmd))
(defn add-indent [text]
  (str text (apply str (repeat @indent "    "))))

(defn prepare-token [token cmd-token]
  (let [token (replace-several token #"%_op" "(" #"%_cp" ")" #"%_" " ")]
    (if (cstr/includes? cmd-token "name") (convert-case token) token)))
(defn convert [text]
  (let [tokens (get-tokens text)
        cmd (find-cmd tokens)
        code (atom (get (get-cmd-templates) cmd))
        cmd-tokens (cstr/split cmd #" ")]
    (if (= (get tokens 0) "do")
      (loop [k 1]
        (reset! code (str @code (convert (get tokens k))))

        (if (< k (- (count tokens) 1)) (recur (+ k 1))))
      (loop [i 0]
        (let [token (get tokens i)
              cmd-token (get cmd-tokens i)]
          (if (= (get (cstr/split cmd-token #"=") 0) "_ph") (reset! code (cstr/replace @code cmd-token (if (= (str (get token 0)) "(") (convert token) (prepare-token token cmd-token))))))

        (if (< i (- (count cmd-tokens) 1)) (recur (+ i 1)))))
    
    @code))
(defn prepare-text [text]
  (let [text-lines (atom (cstr/split-lines text))]
    (loop [i 0]
      (reset! text-lines (assoc @text-lines i (cstr/trim (get @text-lines i))))
      (if (or (= (get (cstr/split (get @text-lines i) #" ") 0) "//") (empty? (get @text-lines i))) (reset! text-lines (vec-remove i @text-lines)))
      
      (if (< i (- (count @text-lines) 1)) (recur (+ i 1))))
    (cstr/join " " @text-lines)))
(defn -main [& args]
  (println "language?")
  (reset! lang (read-line))
  (println "file?")
  (let [file (read-line)
        output-file (str "output." @lang)]
    (println "converting...")
    (spit output-file (convert (prepare-text (slurp file))))
    (println (str "output file: " output-file))))