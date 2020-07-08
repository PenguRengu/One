(ns one.core
  (:gen-class)
  (:require [one.preparer :as prepare]
            [one.java]
            [one.clj]
            [clojure.string :as cstr]
            [camel-snake-kebab.core :as csk]))

;; Commands
(defn get-cmd-templates [lang]
  (case lang
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
(defn find-cmd [tokens lang]
  (let [cmds (vec (keys (get-cmd-templates lang)))
        cmd (atom "error")]
    (loop [i 0]
      (let [cmd-tokens (cstr/split (get cmds i) #" ")]
        (if (match? tokens cmd-tokens) (reset! cmd (get cmds i))))

      (if (< i (- (count cmds) 1)) (recur (+ i 1))))
    @cmd))

;; Conversion
(defn convert [text lang]
  (let [tokens (get-tokens text)
        cmd (find-cmd tokens lang)
        code (atom (get (get-cmd-templates lang) cmd))
        cmd-tokens (cstr/split cmd #" ")]
    (if (= (get tokens 0) "do")
      (loop [k 1]
        (reset! code (str @code (convert (get tokens k) lang)))

        (if (< k (- (count tokens) 1)) (recur (+ k 1))))
      (loop [i 0]
        (let [token (get tokens i)
              cmd-token (get cmd-tokens i)]
          (if (= (get (cstr/split cmd-token #"=") 0) "_ph") (reset! code (cstr/replace @code cmd-token (if (= (str (get token 0)) "(") (convert token lang) (prepare/prepare-token token cmd-token lang))))))

        (if (< i (- (count cmd-tokens) 1)) (recur (+ i 1)))))

    @code))
(defn -main [& args]
  (println ">")
  (let [input (read-line)
        file (get (cstr/split input #" ") 0)
        lang (get (cstr/split input #" ") 1)
        output-file (str "output." lang)]
    (println "converting...")
    (spit output-file (convert (prepare/prepare-text (slurp file)) lang))
    (println (str "output file: " output-file))))