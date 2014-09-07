(def fib-seq
     (lazy-cat [0 1] (map + (rest fib-seq) fib-seq)))

(defn n-repeat [n] (lazy-cat (repeat n n) (n-repeat (inc n))))

(take 10 (n-repeat 1))

(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (cons n (lazy-seq (positive-numbers (inc n))))))

(take 5 (positive-numbers))


(take 5 (iterate inc 5))

(defn iterate [f x] (cons x (lazy-seq (iterate f (f x)))))



