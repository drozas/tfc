 .d888888  .d88888b  d888888P  888888ba   .d888888  
d8'    88  88.    "'    88     88    `8b d8'    88  
88aaaaa88a `Y88888b.    88    a88aaaa8P' 88aaaaa88a 
88     88        `8b    88     88   `8b. 88     88  
88     88  d8'   .8P    88     88     88 88     88  
88     88   Y88888P     dP     dP     dP 88     88  


"Development and integration of an awareness applications manager into ASTRA"
=============================================================================

Author: David Rozas Domingo
Supervisors: Soto Montalvo (URJC) & Monica Divitini (NTNU)

The latest version of the report can be found at http://davidrozas.com/pub/Rozas2009d.pdf


CD contents:
============

- Source code of all the bundles developed for this project in folder src.
- ASTRA executables (Backend and Node) in folder bin. Revision 2016.
- Report (pdf + source in Latex) in folder report.

Requirements:
=============

- Java 6
- MySQL >= 5.0.67. Default values for the DB (they can be changed in the configuration files db.props, dbclient.props):
	- Backend: DB: ASTRABACKEND, username: ASTRABACKEND, password: ASTRABACKEND
	- Node: DB: ASTRANODE, username: ASTRANODE, password: ASTRANODE
- If you are executing it under GNU/Linux, you may need to create a soft link in /usr/lib to the clips library.


Basic configuration:
====================

- The bundles configuration can be changed in the configuration files:
	- bin/ASTRA-backend-2.0.2016/props.xargs
	- bin/ASTRA-node-2.0.2016/props.xargs
- The bundles execution priorities can be changed in the configuration files:
	- bin/ASTRA-backend-2.0.2016/init.xargs
	- bin/ASTRA-node-2.0.2016/init.xargs

- The default configuration is prepared to execute Backend and Node in the localhost.
- If you just need to run the Node, you can conigure it to use the latest version of the Backend at: http://astra.perseum.com:8080/
- In that case, you can also use the PHP End User Tools at: http://astra.perseum.com/eut/

Execution (GNU/Linux):
======================
Backend: java -jar bin/ASTRA-backend-2.0.2016/startframework.sh
Node: java -jar bin/ASTRA-node-2.0.2016/startframework.sh

If you want to execute the Backend in the Background (i.e.: as a cron task) you can use the script:
bin/ASTRA-backend-2.0.2016/server_startup.sh


Execution (Windows):
====================
Backend: java -jar bin/ASTRA-backend-2.0.2016/framework.bat
Node: java -jar bin/ASTRA-node-2.0.2016/framework.bat


More information:
=================
- Official website: http://www.astra-project.net/
- Wiki: http://www.astra-project.net/wiki
- ASTRA NTNU server: http://astra.perseum.com/

Or you can also send me an e-mail: david.rozas@gmail.com
