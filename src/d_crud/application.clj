(ns d_crud.application
  (:require [d_crud.routes :as routes]
            [d_crud.storage.in-memory :refer [in-memory-storage]]
            [ring.middleware.defaults :refer [api-defaults wrap-defaults]]))

;; (def app
;;   (wrap-defaults routes/app-routes api-defaults))

(def app
  (let [stg (in-memory-storage)
        app-routes (routes/shortener-routes stg)]
    (wrap-defaults app-routes api-defaults)))



