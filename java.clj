(.toUpperCase "fred")

(.getName String)

(System/getProperty "java.vm.version")

Math/PI

(-> (System/getProperties) (.get "os.name"))

(doto (new java.util.HashMap) (.put "a" 1) (.put "b" 2))

(import java.util.Date)
(def *now* (Date.))
(bean *now*)

(defn make-some-example
  []
  (proxy [Object] []
    (toString [] "Hello, World!")))

(.toString (make-some-example))

(type (make-some-example))

(defn len [x]
  (.length x))

(defn len2 [^String x]
  (.length x))


(time (reduce + (map len (repeat 1000000 "asdasd"))))
(time (reduce + (map len2 (repeat 1000000 "asdasd"))))

(defn my-fn [^String x]
  (println x))

(my-fn "11")
