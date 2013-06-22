;;
(defrule MOD-Rule-service ?fact<-(MOD Service ?serv ?value) ?pointer<-(Service (name ?serv)(value ?val) ) => ( if ( neq ?value ?val)  then (modify ?pointer (value ?value) ) (retract ?fact) ) );;
;;
( defrule ASSERT-rule-service (declare (salience -1)) ?fact<-(MOD Service ?serv ?value) => (assert (Service (name ?serv) (value ?value))) (retract ?fact) );;
;;
(defrule MOD-Rule-InvokeMessage ?fact<-(MOD InvokeMessage ?serv ?value) ?pointer<-(InvokeMessage(name ?serv)(value ?val) ) => ( if ( neq ?value ?val)  then (modify ?pointer (value ?value) ) (retract ?fact) ) );;
;;
( defrule ASSERT-rule-InvokeMessage (declare (salience -1)) ?fact<-(MOD InvokeMessage ?serv ?value) => (assert (InvokeMessage (name ?serv) (value ?value))) (retract ?fact) );;
;;rule 1 ::FOCUS:drozas@astra:go_fishing_3-reverse
(defrule FOCUS:drozas@astra:go_fishing_3-reverse
?Awareness1<-(Awareness ( name sleeping) (value ?sleeping_val))
?Awareness2<-(Awareness ( name eating) (value ?eating_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:go_fishing) (value ?drozas@astra:go_fishing_val))
(test ( and  ( or (eq ?sleeping_val true) (ne ?eating_val true) ) ( neq ?drozas@astra:go_fishing_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:go_fishing) (value true)))
(send-to-java "drozas@astra:go_fishing=true")
);;
;;rule 2 ::pepe@astra:example_0
(defrule pepe@astra:example_0
?Awareness1<-(Awareness ( name eating) (value ?eating_val))
?Awareness2<-(Awareness ( name watchingTV) (value ?watchingTV_val))
?InvokeMessage0<-(InvokeMessage ( name pepe@astra:example) (value ?pepe@astra:example_val))
(test ( and  ( or (eq ?eating_val true) (eq ?watchingTV_val true) ) ( neq ?pepe@astra:example_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name pepe@astra:example) (value true)))
(send-to-java "pepe@astra:example=true")
);;
;;rule 3 ::pepe@astra:example_0-reverse
(defrule pepe@astra:example_0-reverse
?Awareness1<-(Awareness ( name eating) (value ?eating_val))
?Awareness2<-(Awareness ( name watchingTV) (value ?watchingTV_val))
?InvokeMessage0<-(InvokeMessage ( name pepe@astra:example) (value ?pepe@astra:example_val))
(test ( and  ( and (ne ?eating_val true) (ne ?watchingTV_val true) ) ( neq ?pepe@astra:example_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name pepe@astra:example) (value true)))
(send-to-java "pepe@astra:example=true")
);;
;;rule 4 ::FOCUS:drozas@astra:go_fishing_0-reverse
(defrule FOCUS:drozas@astra:go_fishing_0-reverse
?Awareness1<-(Awareness ( name watchingTV) (value ?watchingTV_val))
?Awareness2<-(Awareness ( name Available) (value ?Available_val))
?Awareness0<-(Awareness ( name Sad) (value ?Sad_val))
(test ( and  ( or (eq ?watchingTV_val true) (ne ?Available_val true) ) ( neq ?Sad_val true)  ) )
=>
(retract ?Awareness0)
( assert (Awareness ( name Sad) (value true)))
(send-to-java "Sad=true")
);;
;;rule 5 ::pepe@astra:asdfasdf_0
(defrule pepe@astra:asdfasdf_0
?Awareness1<-(Awareness ( name cold) (value ?cold_val))
?Awareness0<-(Awareness ( name cold) (value ?cold_val))
(test ( and  (eq ?cold_val true) ( neq ?cold_val true)  ) )
=>
(retract ?Awareness0)
( assert (Awareness ( name cold) (value true)))
(send-to-java "cold=true")
);;
;;rule 6 ::FOCUS:drozas@astra:go_fishing_4
(defrule FOCUS:drozas@astra:go_fishing_4
?Awareness1<-(Awareness ( name cold) (value ?cold_val))
?Awareness0<-(Awareness ( name cold) (value ?cold_val))
(test ( and  (eq ?cold_val true) ( neq ?cold_val true)  ) )
=>
(retract ?Awareness0)
( assert (Awareness ( name cold) (value true)))
(send-to-java "cold=true")
);;
;;rule 7 ::FOCUS:drozas@astra:go_fishing_1-reverse
(defrule FOCUS:drozas@astra:go_fishing_1-reverse
?Awareness1<-(Awareness ( name cold) (value ?cold_val))
?Awareness0<-(Awareness ( name cold) (value ?cold_val))
(test ( and  (ne ?cold_val true) ( neq ?cold_val true)  ) )
=>
(retract ?Awareness0)
( assert (Awareness ( name cold) (value true)))
(send-to-java "cold=true")
);;
;;rule 8 ::FOCUS:drozas@astra:go_fishing_1
(defrule FOCUS:drozas@astra:go_fishing_1
?Awareness1<-(Awareness ( name cold) (value ?cold_val))
?Awareness0<-(Awareness ( name cold) (value ?cold_val))
(test ( and  (eq ?cold_val true) ( neq ?cold_val true)  ) )
=>
(retract ?Awareness0)
( assert (Awareness ( name cold) (value true)))
(send-to-java "cold=true")
);;
;;rule 9 ::FOCUS:drozas@astra:go_fishing_0
(defrule FOCUS:drozas@astra:go_fishing_0
?Awareness1<-(Awareness ( name watchingTV) (value ?watchingTV_val))
?Awareness2<-(Awareness ( name Available) (value ?Available_val))
?Awareness0<-(Awareness ( name Sad) (value ?Sad_val))
(test ( and  ( and (ne ?watchingTV_val true) (eq ?Available_val true) ) ( neq ?Sad_val true)  ) )
=>
(retract ?Awareness0)
( assert (Awareness ( name Sad) (value true)))
(send-to-java "Sad=true")
);;
;;rule 10 ::drozas@astra:go_fishing_0
(defrule drozas@astra:go_fishing_0
?Awareness1<-(Awareness ( name Available) (value ?Available_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:go_fishing) (value ?drozas@astra:go_fishing_val))
(test ( and  (eq ?Available_val true) ( neq ?drozas@astra:go_fishing_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:go_fishing) (value true)))
(send-to-java "drozas@astra:go_fishing=true")
);;
;;rule 11 ::drozas@astra:go_fishing_0-reverse
(defrule drozas@astra:go_fishing_0-reverse
?Awareness1<-(Awareness ( name Available) (value ?Available_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:go_fishing) (value ?drozas@astra:go_fishing_val))
(test ( and  (ne ?Available_val true) ( neq ?drozas@astra:go_fishing_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:go_fishing) (value true)))
(send-to-java "drozas@astra:go_fishing=true")
);;
;;rule 12 ::FOCUS:drozas@astra:go_fishing_3
(defrule FOCUS:drozas@astra:go_fishing_3
?Awareness1<-(Awareness ( name sleeping) (value ?sleeping_val))
?Awareness2<-(Awareness ( name eating) (value ?eating_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:go_fishing) (value ?drozas@astra:go_fishing_val))
(test ( and  ( and (ne ?sleeping_val true) (eq ?eating_val true) ) ( neq ?drozas@astra:go_fishing_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:go_fishing) (value true)))
(send-to-java "drozas@astra:go_fishing=true")
);;
;;rule 13 ::pepe@astra:asdfasdf_0-reverse
(defrule pepe@astra:asdfasdf_0-reverse
?Awareness1<-(Awareness ( name cold) (value ?cold_val))
?Awareness0<-(Awareness ( name cold) (value ?cold_val))
(test ( and  (ne ?cold_val true) ( neq ?cold_val true)  ) )
=>
(retract ?Awareness0)
( assert (Awareness ( name cold) (value true)))
(send-to-java "cold=true")
);;
;;rule 14 ::FOCUS:drozas@astra:go_fishing_4-reverse
(defrule FOCUS:drozas@astra:go_fishing_4-reverse
?Awareness1<-(Awareness ( name cold) (value ?cold_val))
?Awareness0<-(Awareness ( name cold) (value ?cold_val))
(test ( and  (ne ?cold_val true) ( neq ?cold_val true)  ) )
=>
(retract ?Awareness0)
( assert (Awareness ( name cold) (value true)))
(send-to-java "cold=true")
);;
