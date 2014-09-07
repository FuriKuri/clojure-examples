;;refs - Coordinated - Synchronous

(def account-a (ref 0))
;; ⇒ #'user/account-a
(def account-b (ref 0))
;; ⇒ #'user/account-b

(deref account-a)
;; ⇒ 0
@account-b
;; ⇒ 0

(def account-a (ref 1000))
;; ⇒ #'user/account-a
(def account-b (ref 1000))
;; ⇒ #'user/account-b

(dosync
  ;; will be executed as (+ @account-a 100)
  (alter account-a + 100)
  ;; will be executed as (- @account-b 100)
  (alter account-b - 100))
;; ⇒ 900
@account-a
;; ⇒ 1100
@account-b
;; ⇒ 900

(do (.start (Thread. leseThread))
  (.start (Thread. schreibeThread)))



;; Atom - Uncoordinated  - Synchronous
(swap! atom update-fn args*)

(def currently-connected (atom []))

(def currently-connected (atom []))

@currently-connected
;; ⇒ []
(deref currently-connected)
;; ⇒ []
currently-connected
;; ⇒ #<Atom@614b6b5d: []>

(let [xs (atom [])]
  @xs)

(swap! currently-connected conj "chatty-joe")

@currently-connected
;; ⇒ ["chatty-joe"]
(reset! currently-connected [])
;; ⇒ []
@currently-connected
;; ⇒ []



;; Agents - Uncoordinated  - Asynchronous
(def errors-counter (agent 0))
;; ⇒ #'user/errors-counter
errors-counter
;; ⇒ #<Agent@6a6287b2: 0>
@errors-counter
;; ⇒ 0
(deref errors-counter)
;; ⇒ 0
@errors-counter
;; ⇒ 0
(send errors-counter inc)
;; ⇒ #<Agent@6a6287b2: 0>
@errors-counter
;; ⇒ 1

;; 10 is an additional parameter. The + function will be invoked as `(+ @errors-counter 10)`.
(send errors-counter + 10)
;; ⇒ #<Agent@6a6287b2: 1>
@errors-counter
;; ⇒ 11


@errors-counter
;; ⇒ 11
(send errors-counter / 0)
;; Evaluation aborted.
;; ⇒ nil
(send errors-counter / 0)
;; ⇒ #<Agent@6a6287b2: 10>
(send errors-counter inc)
;; Evaluation aborted.
(send errors-counter / 0)
;; Evaluation aborted.
;; ⇒ nil
(agent-error errors-counter)
;; ⇒ #<ArithmeticException java.lang.ArithmeticException: Divide by zero>
(restart-agent errors-counter 0)
;; ⇒ 0
(send errors-counter + 10)
;; ⇒ #<Agent@6a6287b2: 0>
@errors-counter
;; ⇒ 10



;; Delay
(def d (delay (System/currentTimeMillis)))
;; ⇒ #'user/d
d
;; ⇒ #<Delay@21ed22af: :pending>
;; dereferencing causes the value to be realized, it happens only once
@d
;; ⇒ 1350997814621
@d
;; ⇒ 1350997814621
@d
;; ⇒ 1350997814621

(def d (delay (System/currentTimeMillis)))
;; ⇒ #'user/d
(realized? d)
;; ⇒ false
@d
;; ⇒ 1350997967984
(realized? d)
;; ⇒ true


;;Future
(def ft (future (+ 1 2 3 4 5 6)))

;; will block the current thread for 10 seconds, returns :completed
(def ft (future (Thread/sleep 10000) :completed))
;; ⇒ #'user/ft
(deref ft 2000 :timed-out)
;; ⇒ :timed-out




;; Locking
(let [l (java.util.ArrayList.)]
  (locking l
    (.add l 10))
  l)




;; Reducers
(def natuerliche (iterate inc 1))
(type natuerliche)
;=> clojure.lang.Cons
(type (rest natuerliche))
;=> clojure.lang.LazySeq
(take 10 natuerliche)
;=> (1 2 3 4 5 6 7 8 9 10)


(def zahlen (doall (range 1e7)))
(type zahlen)
(def zahlen (vec (range 1e7)))
(type zahlen)
(defn standard [daten]
  (reduce +
          (map inc
               (filter odd? daten))))

(time (standard zahlen))

(defn pstandard [daten]
  (reduce +
          (pmap inc
               (filter odd? daten))))
(time (pstandard zahlen))


(require '[clojure.core.reducers :as r])
(defn not-lazy [data]
  (reduce +
          (r/map inc
                 (r/filter odd? data))))
(time (not-lazy zahlen))

(defn parallel [data]
  (r/fold +
          (r/map inc
                 (r/filter odd? data))))
(time (parallel zahlen))


