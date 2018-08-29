(ns d_crud.handler
  (:require [d_crud.storage :as st]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.request :as req]
            [ring.util.response :as res]))

(defn get-link 
  [stg id]
  (if-let [url (st/get-link stg id)]
    (res/response url)
    (res/not-found "Sorry,that link doesn't exist.")
    ))

(defn create-link
  [stg id {url :body}]
  (if (st/create-link stg id url)
    (res/response (str "links" id))
    (-> (format "The id %s is already in use." id)
        res/response
        (res/status 422))))

(defn update-link
  [stg id {url :body}]
  (if (st/update-link stg id url)
    (res/response (str "/links" id))
    (-> (format "There is no link with the id %s." id)
        res/not-found)))

(defn delete-link
  [stg id]
  (st/delete-link stg id)
  (-> (res/response (str "delete : " id))
      ))
  

(defn list-links
  "Return a handler! call the handler if you want a response."
  [stg]
  (wrap-json-response
   (fn [_]
     (res/response (st/list-links stg)))))
