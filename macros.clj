(def DEBUG true)

(defn on-debug-fn [& args]
  (when DEBUG
    (eval `(do ~@args))))

(defmacro on-debug [& body]
  `(when DEBUG
     (do ~@body)))

(on-debug (println "Hello World"))

(with-out-str (somebody else's code that prints to screen))

(ns hello-world
  (:use compojure.core)
  (:require [compojure.route :as route]))

(defroutes app
  (GET "/" [] "<h1>Hello World</h1>")
  (route/not-found "<h1>Page not found</h1>"))
