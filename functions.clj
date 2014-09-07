;;funktionen
(format "Hello, %s" "World")
'(format "Hello, %s" "World")

;; function programming
(defn hello-format [x]
  (format "Hello, %s" x))
(hello-format "World")
(fn [x] (format "Hello, %s" x))
((fn [x] (format "Hello, %s" x)) "World")
(def fn-hello (fn [x] (format "Hello, %s" x)))
(fn-hello "World")
;;http://clojuredocs.org/clojure_core/clojure.core/defn

(range 3)

(defn sum [n]
  (reduce + (range (inc n))))



(defn factors [number]
  (filter (comp integer? (partial / number)) (range 1 (+ 1 number))))

(defn proper-factors [number]
  (filter (partial > number) (factors number)))

(defn aliquot-sum [number]
  (reduce + (proper-factors number)))

(defn classify [number]
  (cond (= (aliquot-sum number) number) :perfect
        (< (aliquot-sum number) number) :deficient
        :default :abundant))


(defprotocol P
  (foo [x])
  (bar-me [x] [x y]))

(deftype Foo [a b c]
  P
  (foo [x] a)
  (bar-me [x] b)
  (bar-me [x y] (+ c y)))

(bar-me (Foo. 1 2 3) 42)

(foo
 (let [x 42]
   (reify P
     (foo [t] 17)
     (bar-me [t] x)
     (bar-me [t y] x))))

(extend java.lang.String
         P
         {:foo (fn [a]  "Hello World")})

(foo "Hi")
