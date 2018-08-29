(ns d_crud.routes
  (:require [d_crud.handler :as handler ]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [d_crud.middleware :as mw ]))



(defroutes app-route
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(defn shortener-routes
  [stg]
  (-> (routes
       (POST "/links/:id" [id :as request] (handler/create-link stg id request))
       (GET "/links/:id" [id] (handler/get-link stg id))
       (PUT "/links/:id" [id :as request]
            (handler/update-link stg id request))
       (DELETE "/links/:id" [id] (handler/delete-link stg id))
       (GET "/links" [] (handler/list-links stg))
       (route/not-found "Not Found"))
      (wrap-routes mw/wrap-slurp-body)))

