(ns cs307.class13)

(defn square-old
  [x]
  (* x x))

(defn cube-old
  [x]
  (* x x x))

(defn powerer
  "Returns a function that raises argument to exponent power."
  [exponent]
  (fn [argument] ; NOTE: returns an anonymous function
    (apply * (repeat exponent argument))))

(def cube (powerer 3))

(cube 5)

(def to-the-tenth (powerer 10))

(to-the-tenth 2)
(to-the-tenth 3.7)

((powerer 10) 2)

;; This is a _closure_ - a function with an environment,
;; which gives each free variable a value at the time
;; the function was created.
;; In this example, _exponent_ is the free variable

(defn concat-string-with
  "Returns a function that takes a string and concats it onto start-string"
  [start-string]
  (fn [s]
    (str start-string s)))

(def say-hello (concat-string-with "hello "))

(say-hello "Susan")
(say-hello "Bill")

(def t-minus (concat-string-with "T-minus "))

(t-minus 10)

(map t-minus (reverse (range 11)))

;; Want to log the result of a function and then return that result

(defn log-fn-then-execute
  "Logs (prints) the function and then executes it.
   Note: This function takes a function as an argument and also
   returns a function."
  [f]
  (fn [& args]
    (println (conj args f))
    (apply f args)))

(def logged+ (log-fn-then-execute +))

(logged+ 4 3)
(logged+ 1 2 3 4 5)

(def logged-cube (log-fn-then-execute cube))

(logged-cube 5)

(def logged-square (log-fn-then-execute square-old))

(logged-square 5)


;;;;;;;;;;;;;;;
;; Homoiconicity

;; read-string
;; returns the data structure represented by the string

(read-string "(+ 2 3)")

(type (read-string "(+ 2 3)"))

(read-string "[1 2 3]")

(read-string "'(1 2 3)")
(read-string "(quote (1 2 3))")

; another reader macro
(read-string "#(+ % %)")

; ignores comments!
(read-string "(+ 2 3) ; this is a comment")

(read-string "cat")
; this is just the symbol cat
; symbols are just names, and, in some context, you can bind a value to them