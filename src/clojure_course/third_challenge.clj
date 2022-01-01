(ns clojure-course.third-challenge
  (:gen-class)
  (:require [clojure.core.reducers :as r]))

(declare Operation)
(declare Item)

(defstruct Operation :type :amount)
(defstruct Item :name :amount)

(defn is-valid-operation-type
  [^String type]
  (if-not (or (= type "buy") (= type "sell") (= type "initial"))
    (throw (IllegalArgumentException. (format "The type '%s' is invalid" type)))))

(defn is-valid-amount
  [^Integer amount]
  (if (< amount 0)
    (throw (IllegalArgumentException. (format "The amount '%d' needs to be positive" amount)))))

(defn create-operation
  [^String type ^Integer amount]
  (is-valid-operation-type type)
  (is-valid-amount amount)
  (struct Operation type amount))

(defn create-item
  [^String name ^Integer amount]
  (struct Item name amount))

(defn create-available-items
  []
  (seq [(create-item "pen" 1)
        (create-item "notebook" 5)
        (create-item "backpack" 10)
        (create-item "expensive" (Integer/MAX_VALUE))]))

(defn create-accounts-with-operations
  [buyer-operations merchant-operations]
  {:buyer    buyer-operations
   :merchant merchant-operations})

(defn extract-raw-value-from-operation
  [operation]
  (def
    op-type (:type operation))
  (def
    op-amount (:amount operation))

  (if (= op-type "buy")
    (* -1 op-amount)
    (* +1 op-amount)))

(defn extract-raw-value-from-item
  [item]
  (:amount item))

(defn sum-operation-seq-amount
  [operation-seq]
  (def
    to-reduce-amount-seq (map #(extract-raw-value-from-operation %) operation-seq))
  (r/fold + to-reduce-amount-seq))

(defn can-purchase-item
  [accounts item]
  (def
    buyer-operations (:buyer accounts))
  (def
    total-amount (sum-operation-seq-amount buyer-operations))
  (def
    if-buying-after-value (- total-amount (:amount item)))
  (>= if-buying-after-value 0))

(defn create-accounts-from-incoming-item
  [accounts, item]
  (def
    item-amount (:amount item))

  (def
    buy-operation (create-operation "buy" item-amount))
  (def
    sell-operation (create-operation "sell" item-amount))

  (def
    buyer-operations (conj (:buyer @accounts) buy-operation))
  (def
    merchant-operations (conj (:merchant @accounts) sell-operation))

  (create-accounts-with-operations buyer-operations merchant-operations))

(defn get-random-item
  []
  (def
    available-items (create-available-items))
  (def
    size (count available-items))
  (def
    position (rand-int size))

  (nth available-items position))

(defn create-purchased-items-from-item
  [items item]
  (conj @items item))

(defn purchase-item
  [accounts items-purchased item]
  (def
    updated-accounts (create-accounts-from-incoming-item accounts item))

  (ref-set accounts updated-accounts)

  (def
    updated-items-purchased (create-purchased-items-from-item items-purchased item))

  (ref-set items-purchased updated-items-purchased))

(defn print-item-deal
  [position item status]
  (println (inc position) ". The" item "was" status))

(defn simulate
  [position accounts items-purchased item]
  (def
    item-name (:name item))

  (if (can-purchase-item @accounts item)
    (do
      (purchase-item accounts items-purchased item)
      (print-item-deal position item-name "purchased :)"))
    (print-item-deal position item-name "not purchased :(")))

(defn print-budget
  [accounts]
  (println "Accounts Budget")
  (println "- Buyer: R$" (sum-operation-seq-amount (:buyer accounts)))
  (println "- Merchant: R$" (sum-operation-seq-amount (:merchant accounts))))

(defn print-items-summary
  [items]
  (println "Items purchased")
  (doseq [item items]
    (println "Item" (:name item) "- R$" (:amount item)))
  (def
    to-sum-amount (map #(extract-raw-value-from-item %) items))
  (println "Total: R$" (r/fold + to-sum-amount)))

(defn -main
  "Simple selling system"
  []
  (def
    accounts (ref (create-accounts-with-operations
                   [(create-operation "initial" 100)]
                   [(create-operation "initial" 0)])))
  (def
    items-purchased (ref []))

  (def
    iterations (rand-int 20))
  (println "Purchase iterations" iterations)
  (newline)

  (dosync
   (print-budget @accounts)
   (newline)

   (doseq [position (range iterations)]
     (def
       item (get-random-item))
     (simulate position accounts items-purchased item))

   (newline)
   (print-items-summary @items-purchased)
   (newline)
   (print-budget @accounts)))