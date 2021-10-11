(ns cs307.class19)

(defn integer_+
  "Instruction should take a push state and return a push state"
  [push-state]
  (let [top-item (first (:integer push-state))
        second-item (second (:integer push-state))
        new-int-stack (conj (rest (rest (:integer push-state)))
                            (+ top-item second-item))]
    (assoc push-state :integer new-int-stack)))

; make sure this works:

(def some-push-state {:integer '(5 3 100)})

(def program '(integer_+ 5 integer_*))

(integer_+ some-push-state)
;; => {:integer (8 100)}


; part of the interpreter will look something like this:
(let [the-first-instruction (first program)]
  (println (type the-first-instruction))
  (the-first-instruction some-push-state))
;; => nil
;; Why do we get nil here???


(let [the-first-instruction (eval (first program))]
  (println the-first-instruction)
  (the-first-instruction some-push-state))

(integer? 5N)