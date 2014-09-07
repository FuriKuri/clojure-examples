
(* 1 2)
(+ 1 2)
(* 3 2 (+ 5 5))

;;Datentypen
"Hello World"
1
3.0
3/4
true

(def a 1)
(def b 2)
(+ a b)

(class "Hello")
(class 3.0)
(class 11111111223122343242342342343223423)
(class 1)
(class 3/4)

;; Listen
'(1 2 3 4)
(list 1 2 3 4)
(type '(1 2 3))
'("Hello" "World" 42)
'(true false)

(nth '(1 2 3) 2)

(conj '(1 2 3) 4)
(conj '(1 2 3) '(4 5 6))

(def list1 '(1 2 3))
(conj list1 0)
list1

'(:geschachtelt (+ 1 2))
(list :geschachtelt (+ 1 2))
`(:geschachtelt ~(+ 1 2))


;;Vektoren
[1 2 3 4]
(vec (list 1 2 3 4))
(type [1 2 3])
["Hello" "World" 42]
[false true]
(nth [1 2 3] 2)
([1 2 3] 2)


;;Maps
{:name "Theo" :status "Der beste"}
(type {:name "Theo"})
{:name "Kia" :dead false}
(def map1 {:name "Kia" :dead false})
(get map1 :name)
(map1 :name)
(:name map1)

(zipmap [:a :b :c :d] [1 2 3 4])

;;Set
#{1 2 3}
(type #{1 2 3})
