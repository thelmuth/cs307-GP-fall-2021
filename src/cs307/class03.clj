(ns cs307.class03)

(comment

  (println "cat")
  (println "Why" "do" "you" "have" 5 "cats?")
  (println (str "Why" "do" "you" "have" 5 "cats?"))

  ; better to have a small number of common data structures with
  ; many functions to manipulate them than to define
  ; many classes that have different interfaces

  ; sequences: lists and vectors
  ; lists: look a lot like code:

  '(1 2 3)
  '(+ 2 3)
  '(1 (+ 2 3) 4)
  '(1 "(+" 2 "3)" 4)

  ; Equivalent to first thing above
  (quote (1 2 3))

  (list 1 (+ 2 3) 4)
  (list 1 '(+ 2 3) 4 (* 10 20))

  (nth (nth '(1 '(+ 2 3) 4) 1) 0)

  (nth (nth '(1 (+ 2 3) 4) 1)
       0)

  ; vectors: faster for indexing, slower for iteration
  [1 2 3]
  [1 (+ 2 3) 5]
  (vector 1 2 3)

  '(5 :a "hi" ("a" "list")) ; can mix data types in lists

  ; sequence functions: work on lists and vectors:
  (first '(:a :b :c :d :e))
  (rest '(:a :b :c :d :e))
  (count '(:a :b :c :d :e))
  (nth '(:a :b :c :d :e) 3)

  ; conj - conjoin
  (conj '(10 11 12) 4)
  (conj [10 11 12] 4)

  (concat '(1 2 3) '(:a :b :c))
  (concat [1 2 3] [:a :b :c])
  (concat [1 2] '(4 5))

  (concat []))

; let: allows you to assign values to symbols within a lexical context
; let is your friend and you should use it often!
(comment

  (let [nums '(7 2 3 3 1)
        the-first (first nums)]
    (* the-first 100))

  ; Note: no pure functions change values associated with symbols
  ; also: let returns the last thing evaluated; nothing else matters
  ; unless it has side effects like printing
  (let [nums '(7 2 8 1 0)]
    (println nums)
    (println (conj nums 100))
    (println nums)
    (rest nums))

  (second '(1 2 3))
  (last '(1 2 3))
  (butlast '(1 2 3)))

; defn
; define function
(defn square
  "Squares the input x; this is a docstring"
  [x] ; parameter
  (* x x)) ; functions return the last (or only) expression in their body

(defn print-strings-and-concatenate
  "Like with let, defn can do multiple expressions and then return the last one."
  [string1 string2]
  (println string1)
  (println string2)
  (str string1 string2))

(defn clojure.core/+
  "This function can take any number of parameters"
  [& args]
  (apply + args))



(comment
  
  (square 5)
  (square 3.6)
  (square (/ 2 3))
  (square "hi")

  (print-strings-and-concatenate "hi" "there")

  (variable-parameters 1 2 3)
  (variable-parameters 1)
  (variable-parameters)
  (rest 1 2 3)
  (+ 1 2 3)

  (+)

  (clojure.core/+ 1 2)

  (simple-symbol?)


  (+++ 1 2 3)
  
  )
