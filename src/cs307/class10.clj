(ns cs307.class10)


; memoize
; idea: each time a function is called, remember what the return value is
; for that input, and record it in a table. Then, when you make future
; function calls, see if you've recorded the result for the given input
; if so, just return the memoized value.
; In the land of pure functions, the same function call with same arguments
; will always return the same value. 

(def fib-memo
  (memoize
   (fn [n]
     (cond
       (= n 0) 0
       (= n 1) 1
       :else (+' (fib-memo (dec n))
                 (fib-memo (dec (dec n))))))))

(map fib-memo (range 10))

(fib-memo 200)

(fib-memo 1400)

;;;
; Reduce - interposes function calls between elements of a list

(def nums (range 4 10))

nums

(reduce + nums)
(apply + nums)

; can provide an initial "left" argument:
(reduce + 100 nums)

(reduce str 100 nums)

; want the string "4, 5, 6, ..."

(reduce (fn [x y] (str x ", " y))
        nums)
(reduce #(str %1 ", " %2) nums)
(apply str (interpose ", " nums))

(reduce conj '() nums)
(reverse nums)

(reduce list nums)
(reduce list '() nums)

; Say you have a list of things, and want to know how often each appears in that list
; by creating a map of key/count pairs.

(def letters '(:a :b :c :a :a :a :b :a :c :d :a :b :a :a :a :c))

(defn inc-count
  [count-map k]
  (assoc count-map
         k
         (inc (get count-map k 0))))

(reduce inc-count {} letters)

; another way
(frequencies letters)

;;;;;
; Want a function that finds the first prime number greater than its parameter x

(defn prime?
  "Tells if n is prime."
  [n]
  (if (< n 2)
    false
    (empty? (filter (fn [divisor]
                      (zero? (mod n divisor)))
                    (range 2 (inc (int (Math/sqrt n))))))))

(prime? 17)

(defn first-prime-greater
  "finds the first prime number greater than or equal to its parameter x"
  [x]
  (first
   (filter prime?
           (drop x (range)))))

(first-prime-greater 3002)

