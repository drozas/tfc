;;
(defrule MOD-Rule-service ?fact<-(MOD Service ?serv ?value) ?pointer<-(Service (name ?serv)(value ?val) ) => ( if ( neq ?value ?val)  then (modify ?pointer (value ?value) ) (retract ?fact) ) );;
;;
( defrule ASSERT-rule-service (declare (salience -1)) ?fact<-(MOD Service ?serv ?value) => (assert (Service (name ?serv) (value ?value))) (retract ?fact) );;
;;
(defrule MOD-Rule-Awareness ?fact<-(MOD Awareness ?serv ?value) ?pointer<-(Awareness(name ?serv)(value ?val) ) => ( if ( neq ?value ?val)  then (modify ?pointer (value ?value) ) (retract ?fact) ) );;
;;
( defrule ASSERT-rule-Awareness (declare (salience -1)) ?fact<-(MOD Awareness ?serv ?value) => (assert (Awareness (name ?serv) (value ?value))) (retract ?fact) );;
;;
(defrule MOD-Rule-InvokeMessage ?fact<-(MOD InvokeMessage ?serv ?value) ?pointer<-(InvokeMessage(name ?serv)(value ?val) ) => ( if ( neq ?value ?val)  then (modify ?pointer (value ?value) ) (retract ?fact) ) );;
;;
( defrule ASSERT-rule-InvokeMessage (declare (salience -1)) ?fact<-(MOD InvokeMessage ?serv ?value) => (assert (InvokeMessage (name ?serv) (value ?value))) (retract ?fact) );;
;;rule 1 ::admin@astra:coffee_0-reverse
(defrule admin@astra:coffee_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:coffee) (value ?admin@astra:coffee_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:coffee_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:coffee) (value false)))
);;
;;rule 2 ::admin@astra:play_basketball_match_0
(defrule admin@astra:play_basketball_match_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:play_basketball_match) (value ?admin@astra:play_basketball_match_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:play_basketball_match_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:play_basketball_match) (value true)))
);;
;;rule 3 ::drozas@astra:thinking_of_you_0
(defrule drozas@astra:thinking_of_you_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:thinking_of_you) (value ?drozas@astra:thinking_of_you_val))
(test ( and  (eq ?inPlace_val true) ( neq ?drozas@astra:thinking_of_you_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:thinking_of_you) (value true)))
);;
;;rule 4 ::admin@astra:beer_0
(defrule admin@astra:beer_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:beer) (value ?admin@astra:beer_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:beer_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:beer) (value true)))
);;
;;rule 5 ::admin@astra:office_0
(defrule admin@astra:office_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:office) (value ?admin@astra:office_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:office_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:office) (value true)))
);;
;;rule 6 ::admin@astra:romantic_dinner_0-reverse
(defrule admin@astra:romantic_dinner_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:romantic_dinner) (value ?admin@astra:romantic_dinner_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:romantic_dinner_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:romantic_dinner) (value false)))
);;
;;rule 7 ::admin@astra:hating_you_0-reverse
(defrule admin@astra:hating_you_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:hating_you) (value ?admin@astra:hating_you_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:hating_you_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:hating_you) (value false)))
);;
;;rule 8 ::admin@astra:dance_0-reverse
(defrule admin@astra:dance_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:dance) (value ?admin@astra:dance_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:dance_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:dance) (value false)))
);;
;;rule 9 ::admin@astra:sad_0-reverse
(defrule admin@astra:sad_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:sad) (value ?admin@astra:sad_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:sad_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:sad) (value false)))
);;
;;rule 10 ::admin@astra:my_parents_0-reverse
(defrule admin@astra:my_parents_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:my_parents) (value ?admin@astra:my_parents_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:my_parents_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:my_parents) (value false)))
);;
;;rule 11 ::drozas@astra:tapas_0
(defrule drozas@astra:tapas_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:tapas) (value ?drozas@astra:tapas_val))
(test ( and  (eq ?inPlace_val true) ( neq ?drozas@astra:tapas_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:tapas) (value true)))
);;
;;rule 12 ::admin@astra:photography_exposition_0-reverse
(defrule admin@astra:photography_exposition_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:photography_exposition) (value ?admin@astra:photography_exposition_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:photography_exposition_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:photography_exposition) (value false)))
);;
;;rule 13 ::admin@astra:home_0
(defrule admin@astra:home_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:home) (value ?admin@astra:home_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:home_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:home) (value true)))
);;
;;rule 14 ::admin@astra:play_football_0
(defrule admin@astra:play_football_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:play_football) (value ?admin@astra:play_football_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:play_football_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:play_football) (value true)))
);;
;;rule 15 ::drozas@astra:beer_0
(defrule drozas@astra:beer_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:beer) (value ?drozas@astra:beer_val))
(test ( and  (eq ?inPlace_val true) ( neq ?drozas@astra:beer_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:beer) (value true)))
);;
;;rule 16 ::admin@astra:happy_0-reverse
(defrule admin@astra:happy_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:happy) (value ?admin@astra:happy_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:happy_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:happy) (value false)))
);;
;;rule 17 ::admin@astra:go_sightseeing_0
(defrule admin@astra:go_sightseeing_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:go_sightseeing) (value ?admin@astra:go_sightseeing_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:go_sightseeing_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:go_sightseeing) (value true)))
);;
;;rule 18 ::admin@astra:cinema_0-reverse
(defrule admin@astra:cinema_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:cinema) (value ?admin@astra:cinema_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:cinema_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:cinema) (value false)))
);;
;;rule 19 ::admin@astra:play_basketball_match_0-reverse
(defrule admin@astra:play_basketball_match_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:play_basketball_match) (value ?admin@astra:play_basketball_match_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:play_basketball_match_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:play_basketball_match) (value false)))
);;
;;rule 20 ::admin@astra:museum_0-reverse
(defrule admin@astra:museum_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:museum) (value ?admin@astra:museum_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:museum_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:museum) (value false)))
);;
;;rule 21 ::admin@astra:go_jogging_0
(defrule admin@astra:go_jogging_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:go_jogging) (value ?admin@astra:go_jogging_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:go_jogging_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:go_jogging) (value true)))
);;
;;rule 22 ::admin@astra:office_0-reverse
(defrule admin@astra:office_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:office) (value ?admin@astra:office_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:office_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:office) (value false)))
);;
;;rule 23 ::admin@astra:girlfriend_home_0
(defrule admin@astra:girlfriend_home_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:girlfriend_home) (value ?admin@astra:girlfriend_home_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:girlfriend_home_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:girlfriend_home) (value true)))
);;
;;rule 24 ::drozas@astra:ApplicationCondition_0-reverse
(defrule drozas@astra:ApplicationCondition_0-reverse
?InvokeMessage1<-(InvokeMessage ( name drozas@astra:3Conditions) (value ?drozas@astra:3Conditions_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:ApplicationCondition) (value ?drozas@astra:ApplicationCondition_val))
(test ( and  (neq ?drozas@astra:3Conditions_val true) ( neq ?drozas@astra:ApplicationCondition_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:ApplicationCondition) (value false)))
);;
;;rule 25 ::admin@astra:hating_you_0
(defrule admin@astra:hating_you_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:hating_you) (value ?admin@astra:hating_you_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:hating_you_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:hating_you) (value true)))
);;
;;rule 26 ::admin@astra:home_0-reverse
(defrule admin@astra:home_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:home) (value ?admin@astra:home_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:home_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:home) (value false)))
);;
;;rule 27 ::admin@astra:missing_you_0-reverse
(defrule admin@astra:missing_you_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:missing_you) (value ?admin@astra:missing_you_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:missing_you_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:missing_you) (value false)))
);;
;;rule 28 ::admin@astra:museum_0
(defrule admin@astra:museum_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:museum) (value ?admin@astra:museum_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:museum_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:museum) (value true)))
);;
;;rule 29 ::admin@astra:thinking_of_you_0-reverse
(defrule admin@astra:thinking_of_you_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:thinking_of_you) (value ?admin@astra:thinking_of_you_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:thinking_of_you_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:thinking_of_you) (value false)))
);;
;;rule 30 ::admin@astra:tapas_0-reverse
(defrule admin@astra:tapas_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:tapas) (value ?admin@astra:tapas_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:tapas_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:tapas) (value false)))
);;
;;rule 31 ::drozas@astra:3Conditions_0-reverse
(defrule drozas@astra:3Conditions_0-reverse
?Awareness1<-(Awareness ( name watchingTV) (value ?watchingTV_val))
?Service2<-(Service ( name Sofa@occupied) (value ?Sofa@occupied_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:3Conditions) (value ?drozas@astra:3Conditions_val))
(test ( and  ( or (neq ?watchingTV_val true) (neq ?Sofa@occupied_val true) ) ( neq ?drozas@astra:3Conditions_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:3Conditions) (value false)))
);;
;;rule 32 ::admin@astra:girlfriend_home_0-reverse
(defrule admin@astra:girlfriend_home_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:girlfriend_home) (value ?admin@astra:girlfriend_home_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:girlfriend_home_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:girlfriend_home) (value false)))
);;
;;rule 33 ::drozas@astra:thinking_of_you_0-reverse
(defrule drozas@astra:thinking_of_you_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:thinking_of_you) (value ?drozas@astra:thinking_of_you_val))
(test ( and  (neq ?inPlace_val true) ( neq ?drozas@astra:thinking_of_you_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:thinking_of_you) (value false)))
);;
;;rule 34 ::admin@astra:village_0-reverse
(defrule admin@astra:village_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:village) (value ?admin@astra:village_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:village_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:village) (value false)))
);;
;;rule 35 ::admin@astra:concert_0
(defrule admin@astra:concert_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:concert) (value ?admin@astra:concert_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:concert_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:concert) (value true)))
);;
;;rule 36 ::admin@astra:play_football_0-reverse
(defrule admin@astra:play_football_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:play_football) (value ?admin@astra:play_football_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:play_football_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:play_football) (value false)))
);;
;;rule 37 ::drozas@astra:ApplicationCondition_0
(defrule drozas@astra:ApplicationCondition_0
?InvokeMessage1<-(InvokeMessage ( name drozas@astra:3Conditions) (value ?drozas@astra:3Conditions_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:ApplicationCondition) (value ?drozas@astra:ApplicationCondition_val))
(test ( and  (eq ?drozas@astra:3Conditions_val true) ( neq ?drozas@astra:ApplicationCondition_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:ApplicationCondition) (value true)))
);;
;;rule 38 ::admin@astra:beer_0-reverse
(defrule admin@astra:beer_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:beer) (value ?admin@astra:beer_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:beer_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:beer) (value false)))
);;
;;rule 39 ::drozas@astra:3Conditions_0
(defrule drozas@astra:3Conditions_0
?Awareness1<-(Awareness ( name watchingTV) (value ?watchingTV_val))
?Service2<-(Service ( name Sofa@occupied) (value ?Sofa@occupied_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:3Conditions) (value ?drozas@astra:3Conditions_val))
(test ( and  ( and (eq ?watchingTV_val true) (eq ?Sofa@occupied_val true) ) ( neq ?drozas@astra:3Conditions_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:3Conditions) (value true)))
);;
;;rule 40 ::drozas@astra:play_basketball_match_0-reverse
(defrule drozas@astra:play_basketball_match_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:play_basketball_match) (value ?drozas@astra:play_basketball_match_val))
(test ( and  (neq ?inPlace_val true) ( neq ?drozas@astra:play_basketball_match_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:play_basketball_match) (value false)))
);;
;;rule 41 ::drozas@astra:village_0-reverse
(defrule drozas@astra:village_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:village) (value ?drozas@astra:village_val))
(test ( and  (neq ?inPlace_val true) ( neq ?drozas@astra:village_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:village) (value false)))
);;
;;rule 42 ::admin@astra:photography_exposition_0
(defrule admin@astra:photography_exposition_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:photography_exposition) (value ?admin@astra:photography_exposition_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:photography_exposition_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:photography_exposition) (value true)))
);;
;;rule 43 ::drozas@astra:village_0
(defrule drozas@astra:village_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:village) (value ?drozas@astra:village_val))
(test ( and  (eq ?inPlace_val true) ( neq ?drozas@astra:village_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:village) (value true)))
);;
;;rule 44 ::admin@astra:cinema_0
(defrule admin@astra:cinema_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:cinema) (value ?admin@astra:cinema_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:cinema_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:cinema) (value true)))
);;
;;rule 45 ::admin@astra:sad_0
(defrule admin@astra:sad_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:sad) (value ?admin@astra:sad_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:sad_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:sad) (value true)))
);;
;;rule 46 ::admin@astra:go_to_the_gym_0-reverse
(defrule admin@astra:go_to_the_gym_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:go_to_the_gym) (value ?admin@astra:go_to_the_gym_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:go_to_the_gym_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:go_to_the_gym) (value false)))
);;
;;rule 47 ::admin@astra:village_0
(defrule admin@astra:village_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:village) (value ?admin@astra:village_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:village_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:village) (value true)))
);;
;;rule 48 ::admin@astra:dance_0
(defrule admin@astra:dance_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:dance) (value ?admin@astra:dance_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:dance_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:dance) (value true)))
);;
;;rule 49 ::admin@astra:coffee_0
(defrule admin@astra:coffee_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:coffee) (value ?admin@astra:coffee_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:coffee_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:coffee) (value true)))
);;
;;rule 50 ::admin@astra:romantic_dinner_0
(defrule admin@astra:romantic_dinner_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:romantic_dinner) (value ?admin@astra:romantic_dinner_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:romantic_dinner_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:romantic_dinner) (value true)))
);;
;;rule 51 ::admin@astra:concert_0-reverse
(defrule admin@astra:concert_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:concert) (value ?admin@astra:concert_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:concert_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:concert) (value false)))
);;
;;rule 52 ::admin@astra:go_jogging_0-reverse
(defrule admin@astra:go_jogging_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:go_jogging) (value ?admin@astra:go_jogging_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:go_jogging_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:go_jogging) (value false)))
);;
;;rule 53 ::admin@astra:tapas_0
(defrule admin@astra:tapas_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:tapas) (value ?admin@astra:tapas_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:tapas_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:tapas) (value true)))
);;
;;rule 54 ::admin@astra:my_parents_0
(defrule admin@astra:my_parents_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:my_parents) (value ?admin@astra:my_parents_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:my_parents_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:my_parents) (value true)))
);;
;;rule 55 ::admin@astra:play_handball_0
(defrule admin@astra:play_handball_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:play_handball) (value ?admin@astra:play_handball_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:play_handball_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:play_handball) (value true)))
);;
;;rule 56 ::admin@astra:play_handball_0-reverse
(defrule admin@astra:play_handball_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:play_handball) (value ?admin@astra:play_handball_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:play_handball_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:play_handball) (value false)))
);;
;;rule 57 ::admin@astra:thinking_of_you_0
(defrule admin@astra:thinking_of_you_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:thinking_of_you) (value ?admin@astra:thinking_of_you_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:thinking_of_you_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:thinking_of_you) (value true)))
);;
;;rule 58 ::admin@astra:happy_0
(defrule admin@astra:happy_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:happy) (value ?admin@astra:happy_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:happy_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:happy) (value true)))
);;
;;rule 59 ::drozas@astra:tapas_0-reverse
(defrule drozas@astra:tapas_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:tapas) (value ?drozas@astra:tapas_val))
(test ( and  (neq ?inPlace_val true) ( neq ?drozas@astra:tapas_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:tapas) (value false)))
);;
;;rule 60 ::drozas@astra:play_basketball_match_0
(defrule drozas@astra:play_basketball_match_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name drozas@astra:play_basketball_match) (value ?drozas@astra:play_basketball_match_val))
(test ( and  (eq ?inPlace_val true) ( neq ?drozas@astra:play_basketball_match_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name drozas@astra:play_basketball_match) (value true)))
);;
;;rule 61 ::admin@astra:go_to_the_gym_0
(defrule admin@astra:go_to_the_gym_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:go_to_the_gym) (value ?admin@astra:go_to_the_gym_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:go_to_the_gym_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:go_to_the_gym) (value true)))
);;
;;rule 62 ::admin@astra:go_sightseeing_0-reverse
(defrule admin@astra:go_sightseeing_0-reverse
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:go_sightseeing) (value ?admin@astra:go_sightseeing_val))
(test ( and  (neq ?inPlace_val true) ( neq ?admin@astra:go_sightseeing_val false)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:go_sightseeing) (value false)))
);;
;;rule 63 ::admin@astra:missing_you_0
(defrule admin@astra:missing_you_0
?Awareness1<-(Awareness ( name inPlace) (value ?inPlace_val))
?InvokeMessage0<-(InvokeMessage ( name admin@astra:missing_you) (value ?admin@astra:missing_you_val))
(test ( and  (eq ?inPlace_val true) ( neq ?admin@astra:missing_you_val true)  ) )
=>
(retract ?InvokeMessage0)
( assert (InvokeMessage ( name admin@astra:missing_you) (value true)))
);;
