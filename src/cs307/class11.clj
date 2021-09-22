(ns cs307.class11)

;;;;; Lazy Sequences
; sequences that only compute the elements when you need them

(take 5 (filter even? (range 1000000000000N)))

(nth (filter even? (range 1000000000000N))
     1000)

(apply str (filter even? (range 10)))


;;; infinite sequences

(range)

(take 5 (range))

(last (range))

(nth (range) 10)

;; filter and map work on infinite sequences

(first (filter (fn [x] (> x 1000))
               (range)))

(take 10 (map #(* % %) (range)))


;; How would you write a function that finds the first square number above x

(defn first-square-above-x
  [x]
  (first (filter (fn [n] (> n x))
                 (map #(* % %)
                      (range)))))

(first-square-above-x 1000000)

;; repeat
(take 5 (repeat :hi))

(repeat 5 :hi)

;; repeatedly
(take 5 (repeatedly rand))

(repeatedly 5 rand)

(repeat 5 rand)

(repeatedly 5 list)

(take 5 (map #(* 100 %) (repeatedly rand)))


;; iterate
; creates an infinite sequence of:
; (x, (fn x), (fn (fn x)), (fn (fn (fn x))), ...)

(take 10 (iterate inc 10))

(take 8 (iterate list 9))

; infinite sequence of the powers of 2:
(take 10 (iterate #(* 2 %) 1))

(take 10 (drop 10 (iterate #(* 2 %) 1)))

; 2^100
(nth (iterate #(*' 2 %) 1) 100)

(def infinite-foobar
  (iterate (fn [s] (str s "bar"))
           "foo"))

(take 6 infinite-foobar)

; find first element of sequence that has more than 40 characters
(first (filter #(> (count %) 40) infinite-foobar))


;;; define an infinite sequence of fibonacci numbers
(defn next-fib
  "Given a pair of fib numbers, produce the next pair of fib numbers"
  [[a b]]
  [b (+' a b)])

(defn what
  [[a b c & r]]
  (println a)
  (println b)
  (println c)
  (println r))

(what [5 9 4 7 56 54 4 3])


(def fib-sequence
  (map first
       (iterate next-fib [0 1])))

(take 10 fib-sequence)

(defn fast-fib
  [n]
  (nth fib-sequence
       n))

(fast-fib 100000)

;; lazy-seq: more general method for construction of lazy/infinite sequences

(cons 5 '(1 2 3))
(cons 5 [1 2 3])


(defn even-numbers
  ([] (even-numbers 0))
  ([n] (lazy-seq (cons n
                       (even-numbers (+ n 2))))))

(take 10 (even-numbers))

(take 5 (filter even? (range)))

(take 10 (map #(* 2 %) (range)))

(take 10 (iterate #(+ 2 %) 0))

;;;; triangle numbers
; infinite sequence of triangle numbers
; t(n) = t(n-1) + n

(defn triangle-numbers
  "Returns an infinite sequence of the triangle numbers"
  ([] (triangle-numbers 1 0))
  ([n tn-1]
   (let [next-number (+ tn-1 n)]
     (lazy-seq (cons next-number
                     (triangle-numbers (inc n)
                                       next-number))))))

(take 15 (triangle-numbers))


(take 15 (reductions + (range)))


