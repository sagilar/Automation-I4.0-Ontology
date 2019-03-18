# Automation I4.0 Example Application
This is the example application to check the suitability of the Automation I4.0 Ontology in real systems. The application employs the minified version of the ontology.  

### The Java Application
The main application runs in Java. It contains the generated Java code from Protégé of the Automation I4.0 Min Ontology. The modeled system consists in a metal separation process which has 3 actors in the distributed network; one is the server (which runs directly in the Java application) and the other 2 are intended to be Raspberries PI (2 Raspberries or 1 Raspberry executing 2 processes). The model has also many middle-level and high-level concepts such as standards, the architecture, the domain, etc.  
  
  
#### The definition of classes and asserted properties is showed in detail in the folder `instances`.
Word table (`Instances for application - Word table.docx`) or excel table (`Instances for application.xlsx`) with all the asserted definitions.
  
  
Features:
- Process execution with graphical support (GUI). See main package `automationi40ontology.example` the Frames and Thread `threadMetalProcess`.
- OPC UA server and dynamic data updating using the Eclipse Milo OPC UA stack: extended from Eclipse Milo server example. See package opcua and main Frame in Thread `opcserverThread`.
- Dynamic infereces performing through SQWRL rules. See main frame and log frame and Thread `inferenceThread`.
-----------------------------------------------------------------------------------------------------


### The OPC UA Clients: Distributed automation systems through OPC UA
The folder `OPC UA Clients` contains two files for two different Raspberries PI or just one with two processes. The opc ua clients use the Free OPC UA stack. The variables in the OPC UA network are associated for the adding dynamics to the metal separation process at this time. Of course, many other variables and services can be added.  
Both Python scripts have the feature to work with IO events to manage the variables automatically or an IPython interface to manage the variables manually. It was extended from Free OPC UA client example.  
  
  
Features:
- IO event manager.
- OPC UA client using the Free OPC UA stack.
- IPython interface for manual entries.
- Can run on a Raspberry PI or similar system.

-------------------------------------------------------------------------------------------------------

### Dependencies
- SWRLAPI v2.0.6
- OWLAPI v5.1.10
- Eclipse Milo Stack 0.2.5
- python-opcua (for clients)

--------------------------------------------------------------------------------------------------------

### Process description and diagram of the example
Diagram of the proposed application:  
<img src="../images/metal%20separation%20process%20plant.png">
  
  
Associated discrete dynamics model to example:  
<img src="../images/metal%20separation%20process%20ddm.png">
  
  
--------------------------------------------------------------------------------------------------------

### Some results of the application
<img src="../images/Java%20Application%20GUI.png">

  
  
  

-------------------------------------------------------------------------------------------------------

If you have any doubt, do not hesitate to contact me.
