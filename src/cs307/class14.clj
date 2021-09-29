(ns cs307.class14)

(eval 5)
;; => 5

(eval x)
;; => Syntax error compiling at (src/cs307/class14.clj:6:1).
;;    Unable to resolve symbol: x in this context

(let [x 45]
  (eval x))
;; => 45

(eval '(+ 2 3))
;; => 5


;;;;;;;;
;; Macros
;; Take _unevaluated_ arguments as input, unlike functions
;; And: the thing that is returned by the macro should be a form that Clojure
;; can evaluate using eval.
;;  - Usually you'll return a list that represents a function call
;;
;; Why?
;; Sometimes, you don't don't want to evaluate arguments.
;; Sometimes, you want to be able to build a form before evaluating it.

;; Here's an example of a built-in macro: -> ("thread first" operator).


(-> '("hello" "to" "the" "world")
    first
    count
    (str "head"))

(str (count (first '("hello" "to" "the" "world"))) "head")

;; macroexpand - shows you what a macro call will return
(macroexpand '(-> '("hello" "to" "the" "world")
                  first
                  count
                  (str "head")))
;; => (str (count (first '("hello" "to" "the" "world"))) "head")


;; Write our own macros
;; The thing ALWAYS to remember when using macros: they are exactly like functions.
;; except that they DO NOT evaluate their arguments, and THEY DO evaluate
;; their returned value.

(defmacro just-inc-5
  []
  '(inc 5))

(just-inc-5)
;; => 6

(macroexpand '(just-inc-5))
;; => (inc 5)

(defmacro inc-by-2
  [arg]
  (list '+ 2 arg))

(inc-by-2 5)
(inc-by-2 (+ 10 10))

(macroexpand '(inc-by-2 5))
;; => (+ 2 5)

(macroexpand '(inc-by-2 (+ 10 10)))
;; => (+ 2 (+ 10 10))

(defmacro bad-inc-by-2
  [arg]
  '(+ 2 arg))

(bad-inc-by-2 5)
;; => Syntax error compiling at (src/cs307/class14.clj:77:4).
;;    Unable to resolve symbol: arg in this context

(macroexpand '(bad-inc-by-2 5))
;; => (+ 2 arg)


;; Let's say we want to be able to use infix for binary operators
(defmacro infix
  [call]
  (list (second call)
        (first call)
        (nth call 2)))

(infix (4 + 6))
(macroexpand '(infix (4 + 6)))
;; => (+ 4 6)

(infix (3 > 9))

(defmacro infix-pm
  [[f s t]]
  (list s f t))

(macroexpand '(infix-pm (4 + 6)))
;; => (+ 4 6)


(defmacro print-and-return-bad
  "Prints the expression of the call, and returns the result of evaluating."
  [expression]
  (list let [result expression]
        (list println (str expression))
        result))
;; => Syntax error compiling at (src/cs307/class14.clj:111:3).
;;    Can't take value of a macro: #'clojure.core/let

(defmacro print-and-return
  "Prints the expression of the call, and returns the result of evaluating."
  [expression]
  (list 'let ['result expression]
        (list 'println (list 'quote expression))
        'result))

(print-and-return (+ 3 5))
;; => 8

(macroexpand '(print-and-return (+ 3 5)))
;; => (let* [result (+ 3 5)] (println '(+ 3 5)) result)


(defmacro print-and-return-josef
  "Prints the expression of the call, and returns the result of evaluating."
  [expression]
  (println expression)
  expression)

(print-and-return-josef (+ 3 5))

(macroexpand '(print-and-return-josef (+ 3 5)))
;; => (+ 3 5)


;;;;;;;;;;;;
;; Syntax quoting
;; It's awful to put everything in a list and quote everything we want
;; verbatim. Usually in Clojure, we use '(...) to get a literal list.
;; which quotes everything inside the list (i.e. doesn't evaluate)

;; If we want to do the same in macros, but evaluate something in
;; the list, we can use syntax quoting, which is like quoting except:
;;  1. Fully qualified symbols by namespace (to avoid conflicts)
;;  2. Can unquote a form using ~

'+
`+

'(+ 1 2)
`(+ 1 2)

`(+ 1 (inc 5))
;; => (clojure.core/+ 1 (clojure.core/inc 5))

'(+ 1 (inc 5))
;; => (+ 1 (inc 5))

`(+ 1 ~(inc (* 2 3)))
;; => (clojure.core/+ 1 7)

'(+ 1 ~(inc (* 2 3)))
;; => (+ 1 ~(inc (* 2 3)))


