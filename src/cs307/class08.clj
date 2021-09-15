(ns cs307.class08)

(def people '({:name "Kelly" :age 22}
              {:name "Francis" :age 34}
              {:name "Olivia" :age 19}))

people

; you want to get get a list of the names of people from this list of maps

(:name {:name "Bob" :age 44})

(map :name people)
(map #(get % :name) people)

(map (fn [function]
       (function 5 8))
     [+ - * / mod < str vector])


;; filter
;; applies a function to each element, and returns the ones
;; for which the function returns true (or a truthful value)

(def nums (range 4 12))
nums

(filter even? nums)

; want all numbers in our list > 8
(filter #(> % 8) nums)

; want to filter a list of words and only retain those of length > 5
(def words '("only" "the" "longest" "will" "survive"))

(filter #(> (count %) 5) words)

;; Recursion

(defn my-count
  "Replicates the count function"
  [lst]
  (if (empty? lst)
    0
    (inc (my-count (rest lst)))))

(my-count nums)

(my-count (range 100000))

;; Recursive calls in Clojure consume stack space, and Java
;; isn't very friendly to this recursion. More later.

(defn fib
  "The nth Fibonacci number"
  [n]
  (cond
    (<= n 0) 0
    (= n 1) 1
    :else (+ (fib (dec n))
             (fib (dec (dec n))))))

(fib 5)

(map fib (range 10))

; (fib 100) ; bad
; exponential recursion here - bad


;; Tail recursion
;; By using recur instead of a recursive call, and not doing anything
;; besides returning the recursive result, Clojure does not have to
;; track the recursive calls, and can avoid using tons of stack space

;; => cannot do anytihng with the value returned by the recursive call

(defn first-positive-number-in-list
  [numbers]
  (cond
    (empty? numbers) nil
    (> (first numbers) 0) (first numbers)
    :else (recur (rest numbers))))

; recur - do a recursive call, but use tail recursion

(first-positive-number-in-list '(-3 -7 -1 0 -7 4 2 -4 8))

(first-positive-number-in-list (range -100000 5))
; with normal recursion, would stack overflow

;; loop simply sets the recursion point for recur
;; and declares parameters with their initial values
;; Note: it is not a loop, it is recursion

(defn my-tail-count
  [in-lst]
  (loop [lst in-lst
         c 0]
    (if (empty? lst)
      c
      (recur (rest lst)
             (inc c)))))

(my-tail-count nums)

(my-tail-count (range 100000))


(defn my-tail-fib
  [n]
  (loop [n n
         x 0
         y 1]
    (if (<= n 0)
      x
      (recur (dec n)
             y
             (+' x y)))))

(map my-tail-fib (range 10))

(my-tail-fib 100)

(count (str (my-tail-fib 100000)))
