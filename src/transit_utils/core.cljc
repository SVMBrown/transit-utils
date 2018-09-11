(ns transit-utils.core
(:require [cognitect.transit :as transit]
          [transit-utils.time :as time]))


(def read-handlers
  (merge
   time/readers))

(defn reader
  ([] (reader {}))
  ([opts] (transit/reader :json (merge
                                 opts
                                 {:handlers read-handlers}))))

(def write-handlers
  (merge
   time/writers))

(defn writer
  ([] (writer {}))
  ([opts] (transit/writer :json (merge
                                 opts
                                 {:handlers write-handlers}))))

