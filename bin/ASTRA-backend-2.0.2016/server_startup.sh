#!/bin/bash
#This is an small script to execute the Astra-Back-End in background for testing purposes.
#If you want to execute it normally, you should use startframework.sh
#drozas&alfredo 
nohup java -Djava.library.path=./ -jar framework.jar > ./log/log`date +%Y%m%d%H%M`.txt 2> ./error_log/error_log`date +%Y%m%d%H%M`.txt &

