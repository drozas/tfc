(deftemplate Service (slot name) (slot value) )
(deftemplate Awareness (slot name) (slot value))
(deftemplate InvokeMessage (slot name) (slot value))

(deffacts serv0 (Service (name Desk@blur)(value false)))
(deffacts serv1 (Service (name Desk@proximity)(value false)))
(deffacts serv2 (Service (name Camera@streaming)(value false)))

(deffacts awa	(Awareness (name Working)(value FALSE)) )
(deffacts invokes (InvokeMessage (name notifyCoworkers)(value FALSE)) )