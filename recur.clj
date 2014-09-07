(defn sum2 [n]
  (loop [counter n
         value 0]
    (if (zero? counter)
      value
      (recur (dec counter) (+ value counter)))))


(def factorial
  (fn [n]
    (loop [cnt n acc 1]
       (if (zero? cnt)
            acc
          (recur (dec cnt) (* acc cnt))))))
