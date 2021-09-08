(ns cs307.class04)


; Conditionals

(comment

  ; Must have 3 arguments
  (if (< 1 4)
    (str 1 " is smaller")
    (str 4 " is smaller"))

  (when (< 1 4)
    (println "It was true!"))

  (when (> 1 4)
    (println "It was false!!"))

  ; cond: takes multiple condition / what to do pairs
  (defn how-big
    "Tells how big a number is"
    [num]
    (cond
      (< num 0) "That num is negative"
      (< num 10) "Single digit"
      (< num 100) (str "The num" num "is double digit")
      :else "THAT NUM IS SO BIG"))

  (how-big 5)
  (how-big 98)

  (how-big 5343))

; def: used to GLOBALLY assign a *constant* value to a symbol
;      these will never change

(def more-nums '(1 2 3 4 5))
more-nums
(rest more-nums)
more-nums

;; NEVER use def inside other code; just use it at outermost level
;; if you find yourself using def to chnage values of "variables", STOP!!!

; it turns out, defn is just syntactic sugar for def of a function:

(def cube
  (fn [x]
    (* x x x)))

(cube 5)

;;; Maps: like dictionaries in Python

; empty map
{}

{:apples 4
 :bananas 6
 :cantaloupes 10}

(def weird
  {(fn [x] (+ x 10)) 5})

(get weird (fn [x] (+ x 10)))

(def room-colors {:dining-room :taupe
                  :kitchen :mauve
                  :living-room :mint
                  :bedroom :lavender})

room-colors

; Add a key-value pair to a map:
(assoc room-colors :bathroom :cerulean)
; Returns a new map with this key/value added

room-colors

(assoc room-colors :kitchen :ecru)

; get values:
(get room-colors :kitchen)
(get room-colors :den)
(get room-colors :den :red)

; get keys or the values
(keys room-colors)
(vals room-colors)

; You can use maps or keywords as functions
; either case, it acts the same as get
(room-colors :kitchen)
(:kitchen room-colors)


;;;;;;;;;;
;; Higher-order functions
;; either take other functions as arguments OR return functions

(def nums (range 4 12))
nums
;; => (4 5 6 7 8 9 10 11)

;; apply
(+ nums) ; bad
(apply + nums)

;; apply takes a function and passes all elements of a list as arguments to the function

(str nums) ; probably not what you want
(apply str nums)

(max nums) ;not what you want
(apply max nums)

(apply str {:a 5 :b 2})


;;; map function
;; applies a function to every element of a sequence and returns a new sequence
;; of those results

(inc 5)
(dec 5)

(map inc nums)

cube

(map cube nums)

;; use anonymous functions as our argument
(map (fn [x] (* x 5))
     nums)

; special syntax for _simple_ anonymous functions
(map #(* % %)
     nums)

(#(+ % %2) 6 8)

; find the lengths of each word in a list
(map count
     '("what" "are" "our" "lengths?"))

(defn sorted-by-length?
  "Is true if the list argument is sorted by length"
  [words]
  (apply < (map count words)))

(sorted-by-length? '("what" "are" "our" "lengths?"))
(sorted-by-length? '("hi" "dog" "elephant"))