(ns transit-utils.time
  (:require [cognitect.transit :as transit]
            [cuerdas.core :as string]
            #?@(:clj [[clj-time.core :as t]
                      [clj-time.coerce :as tc]
                      [clj-time.format :as tf]]
                :cljs [[cljs-time.core :as t]
                       [cljs-time.coerce :as tc]
                       [cljs-time.format :as tf]
                       goog.date.UtcDateTime
                       goog.date.Date]))
  #?(:clj (:import [org.joda.time])))

(def DateTime #?(:clj org.joda.time.DateTime :cljs goog.date.UtcDateTime))
(def LocalDate #?(:clj org.joda.time.LocalDate :cljs goog.date.Date))

(defn write-date-time
  [d]
  (tf/unparse (tf/formatters :date-time) d))

(defn read-date-time
  [s]
  (tf/parse (tf/formatters :date-time) s))

(defn write-local-date
  [d]
  (tf/unparse-local-date (tf/formatters :date) d))

(defn read-local-date
  [s]
  (tf/parse-local-date (tf/formatters :date) s))

(def writers
  {DateTime (transit/write-handler (constantly "DateTime") write-date-time)
   LocalDate (transit/write-handler (constantly "LocalDate") write-local-date)})

(def readers
  {"DateTime" (transit/read-handler read-date-time)
   "LocalDate" (transit/read-handler read-local-date)})
