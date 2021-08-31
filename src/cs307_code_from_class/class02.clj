(ns cs307.class02)

;; I'll upload all code we write together in class to GitHub

;; REPL - Read evaluate print loop

(comment

  (+ 2 4)

  ; Many Clojure functions can take different numbers of arguments:
  (+ 3)

  (+ 1 2 3 4 5)

  (+ 1,2,3,4,5) ; don't do this

  (* 1 2 3 4 5)
  (* 4)

  (/ 6 27)
  (float (/ 6 27))

  (+ 3 7.31)

  (type 6/27)
  (type 7.31)

  (+ 1 2 (* 9 3)
     8 (/ 34 11))

  (< 1 4)
  (= 3 9)
  (< 2 4 6 8 10)

  ; booleans
  true
  false
  nil ; not a boolean: represents "nothing"
      ; nil is the only thing to evaluate to false in a conditional besides false

  ; keywords
  ; start with a colon, and always evaluate to themselves
  ; used to represent an atomic thing
  :kiwis
  :avocados

  ; strings - used to represent text
  ; always use double quotes, and can go across multiple lines
  "Hi there"
  "This string
uses
multiple
lines"

  ; str function converts all of its arguments to strings and then concatenates them
  (str "Hello" "World")
  (str "hi " (+ 3 2))


  )

