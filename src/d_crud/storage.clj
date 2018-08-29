(ns d_crud.storage)


(defprotocol Storage
  (create-link [this id url]
    "Store the url under the id.Return the id if successful,nil if the id is already in use.")
  (get-link [this id]
    "Given an ID,returns the associated URL.Returns nil if there is no associated URL.")
  (update-link [this id new-url]
    "Update id to point to new-url.Returns the id if successful,nil if the id has not yet been created.")
  (delete-link [this id]
    "delete id link.")
  (list-links [this]
    "Returns a map of all known IDs to URLs."))
