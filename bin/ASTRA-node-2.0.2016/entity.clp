(deftemplate ePlant (slot name) (slot LampDimmer) (slot LampTuner) (slot Flash)) 
(deffacts currentPlant (idle) (ePlant (name eDeskLamp) (LampDimmer FALSE) (LampTuner FALSE) (Flash FALSE)))