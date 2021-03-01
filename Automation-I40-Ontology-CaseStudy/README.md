# Automation I4.0 Case Study Application
This is a Java Maven application which employs the Automation I4.0 Ontology online and dynamically.

### The Java Application
The main application runs in Java. It contains the generated Java code from Protégé of the Automation I4.0 Min Ontology. The modeled system consists in a metal separation process which has 3 agents in the distributed network; one is the server (which runs directly in the Java application) and the other 2 are intended to be Raspberries PI (2 Raspberries or 1 Raspberry executing 2 processes).




#### Features:
- Process execution with graphical support (GUI).
- OPC UA server and dynamic data updating using the Eclipse Milo OPC UA stack.
- Dynamic infereces performing through SQWRL rules and updating the SWRL Engine. Please refer to the file `inferences.txt` to see the generated file with the performed inferences along the tests.
-----------------------------------------------------------------------------------------------------


### The OPC UA Clients: Distributed automation systems through OPC UA
The folder `OPC UA Clients` contains two files for two different Raspberries PI or just one with two processes. The opc ua clients use the Free OPC UA stack. The variables in the OPC UA network are associated for the adding dynamics to the metal separation process at this time.  
Both Python scripts have the feature to work with IO events to manage the variables automatically or an IPython interface to manage the variables manually.  


#### Features:
- IO event manager.
- OPC UA client using the Free OPC UA stack.
- IPython interface for manual entries.
- Can run on a Raspberry PI or similar system.

-------------------------------------------------------------------------------------------------------

### Dependencies
- SWRLAPI v2.0.6
- OWLAPI v4.2.8
- Eclipse Milo Stack v0.2.5
- python-opcua (for clients)
Maven should supply the dependencies when needed.

--------------------------------------------------------------------------------------------------------

### Process description and diagram of the example
Diagram of the proposed application:  
<img src="../images/metal%20separation%20process%20plant.png">


Associated discrete dynamics model to example:  
<img src="../images/metal%20separation%20process%20ddm.png">


--------------------------------------------------------------------------------------------------------

### Some results of the application
<img src="../images/Java%20Application%20GUI%20v2.png">





-------------------------------------------------------------------------------------------------------

If you have any doubt, do not hesitate to contact me.
