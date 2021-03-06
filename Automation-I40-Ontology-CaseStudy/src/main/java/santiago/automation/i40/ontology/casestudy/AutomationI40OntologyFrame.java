/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package automationi40ontology.example;
package santiago.automation.i40.ontology.casestudy;

import aI40ontology.*;
import aI40ontology.impl.*;
//import com.google.common.collect.ImmutableList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import java.io.File;
import java.util.Optional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.UIManager;
import opcua.OPCUAServer;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.swrlapi.core.SWRLAPIRule;
import org.swrlapi.core.SWRLRuleEngine;
import org.swrlapi.exceptions.SWRLRuleException;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Santiago
 */
public class AutomationI40OntologyFrame extends javax.swing.JFrame {

    aI40ontology ai40ontology;
    Domain industrialAutomationDomain;
    Domain iotDomain;

    Standard std_iec_61131;
    Standard std_opcua;
    Standard std_iec_61499;
    Standard std_iec_62264;
    Standard std_iec_61512;
    Standard std_iec_62890;
    Standard std_w3c;
    Architecture distributedAutomationArchitecture;
    Architecture iotArchitecture;
    Network applicationNetwork;
    Data pieceMaterialData;
    Data pieceProcessData;
    Data serialData;
    Asset AssetMetalSeparation;
    ProcessO processMetalSeparation;
    Agent agentThing;
    Agent agentDevice;
    Agent agentServer;
    Service sendPieceDataService;
    Service saveDataService;
    Service performMaterialSeparationService;
    DynamicsModel metalSeparationDDModel;
    ExecutionScope esMetalSeparationModel;
    InitialState state0_ba;
    State state1_ba;
    State state2_ba;
    State state3_ba;
    State state4_ba;
    State state5_ba;
    FinalState state6_ba;
    FinalState state7_ba; //for test only
    Transition transition1_ba;
    Transition transition2_ba;
    Transition transition3_ba;
    Transition transition4_ba;
    Transition transition5_ba;
    Transition transition6_ba;
    Transition transition7_ba;
    Sequence sequence_ba;
    DigitalOutput convBeltOut;
    DigitalOutput metallicGate;
    DigitalOutput nonMetallicGate;
    DigitalInput metalDetector;
    DigitalInput p1Detector;
    DigitalInput p2Detector;
    DigitalInput pfDetector;
    AnalogInput weight;
    DataInput serialNumber;
    DataOutput pieceData;
    Function readMetalDetectorFunction;
    Function requestPieceDataFunction;
    Function sendPieceDataFunction;
    Function performSeparationFunction;
    Function saveDataFunction;
    ObjectO conveyorBelt;

    DigitalInput startButton;

    //thread and timers
    Thread threadMetalProcess;
    Timer timerMetalProcess = new Timer();
    Thread inferenceThread;
    Timer inferenceTimer = new Timer();

    //SWRLAPI
    SWRLRuleEngine swrlEngine;
    SQWRLQueryEngine queryEngine;
    boolean updateInferCheckbox = false;

    //boolean states: in sequence order
    Boolean[] statesMSProcess = new Boolean[8];

    //Inference Log Messages
    String logMessage = "";
    InferencesFrame infFrame = new InferencesFrame();

    //OPC-UA Server
    OPCUAServer server;
    OpcUaServer uaServer;
    AttributeContext uaServerContext;
    Thread opcserverThread;
    Thread opcserverEventsThread;
    Timer opcEventsTimer = new Timer();
    
    //Save Data Function Emulation
    boolean saveResult = false; 

    /**
     * Creates new form AutomationI40OntologyFrame
     */
    public AutomationI40OntologyFrame(Optional<File> owlFile) {
        initComponents();
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton3.setContentAreaFilled(false);
        jButton3.setOpaque(true);
        jButton4.setContentAreaFilled(false);
        jButton4.setOpaque(true);
        jButton5.setContentAreaFilled(false);
        jButton5.setOpaque(true);
        jButton6.setContentAreaFilled(false);
        jButton6.setOpaque(true);
        jButton7.setContentAreaFilled(false);
        jButton7.setOpaque(true);
        jButton8.setContentAreaFilled(false);
        jButton8.setOpaque(true);
        try {
            // Create an OWL ontology using the OWLAPI
            OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
            OWLOntology ontology = ontologyManager.loadOntologyFromOntologyDocument(new File("Automation-I4.0-Min-Ontology.owl"));
            //OWLOntology ontology = ontologyManager.createOntology();
            /*OWLOntology ontology = owlFile.isPresent()
                    ? ontologyManager.loadOntologyFromOntologyDocument(owlFile.get())
                    : ontologyManager.createOntology();*/

            ontologyManager.getOntologyFormat(ontology).asPrefixOWLOntologyFormat().setDefaultPrefix("http://www.semanticweb.org/santiago/Automation-I4.0-Min-Ontology" + "#");

            /**
             * *
             **** Instantiating the ontology *
             */
            ai40ontology = new aI40ontology(ontology);

            /**
             * *
             **** Instances / Individuals *
             */

            //instantiating the domains
            industrialAutomationDomain = ai40ontology.createDomain("Industrial-Automation-Domain");

            iotDomain = ai40ontology.createDomain("IIoT-Domain");

            //instantiating the standards
            std_iec_61131 = ai40ontology.createStandard("Standard-IEC-61131");
            std_opcua = ai40ontology.createStandard("Standard-OPC-UA");
            std_iec_61499 = ai40ontology.createStandard("Standard-IEC-61499");
            std_iec_62264 = ai40ontology.createStandard("Standard-IEC-62264-ISA-95");
            std_iec_61512 = ai40ontology.createStandard("Standard-IEC-61512-ISA-88");
            std_iec_62890 = ai40ontology.createStandard("Standard-IEC-62890");
            std_w3c = ai40ontology.createStandard("Standard-W3C");

            //instantiating the architectures
            distributedAutomationArchitecture = ai40ontology.createArchitecture("3-Agent-Distributed-Architecture-for-Metal-Separation-Process");
            //iotArchitecture = ai40ontology.createArchitecture("internet-of-things-architecture");



            //instantiating the networks
            applicationNetwork = ai40ontology.createNetwork("Application-Network");

            //instantiating the data
            pieceMaterialData = ai40ontology.createData("Piece-Material-Data");
            pieceProcessData = ai40ontology.createData("Piece-Process-Data");
            serialData = ai40ontology.createData("Serial-Data");

            //instantiating the assets
            AssetMetalSeparation = ai40ontology.createAsset("Metal-Separation-Asset");

            //instantiating the processes
            processMetalSeparation = ai40ontology.createProcessO("Metal-Separation-Process");

            //instantiating the actors
            agentThing = ai40ontology.createAgent("Agent-I-RA-Reader");
            agentDevice = ai40ontology.createAgent("Agent-II-RA-Separator");
            agentServer = ai40ontology.createAgent("Agent-III-RA-Saver");

            sendPieceDataService = ai40ontology.createService("Send-Piece-Data-Service");
            saveDataService = ai40ontology.createService("Save-Data-Service");
            performMaterialSeparationService = ai40ontology.createService("Perform-Separation-Service");

            //instantiating the dynamics models
            metalSeparationDDModel = ai40ontology.createDynamicsModel("Metal-Separation-Discrete-Dynamics-Model");

            //instantiating the execution scope
            esMetalSeparationModel = ai40ontology.createExecutionScope("Metal-Separation-Execution-Scope");

            //instantiating the states for the application model
            // remark: _ba suffix
            state0_ba = ai40ontology.createInitialState("State-0");
            state1_ba = ai40ontology.createState("State-1");
            state2_ba = ai40ontology.createState("State-2");
            state3_ba = ai40ontology.createState("State-3");
            state4_ba = ai40ontology.createState("State-4");
            state5_ba = ai40ontology.createState("State-5");
            state6_ba = ai40ontology.createFinalState("State-6");
            state7_ba = ai40ontology.createFinalState("State-7");

            //instantiating the transitions for the application model
            transition1_ba = ai40ontology.createTransition("Transition-1");
            transition2_ba = ai40ontology.createTransition("Transition-2");
            transition3_ba = ai40ontology.createTransition("Transition-3");
            transition4_ba = ai40ontology.createTransition("Transition-4");
            transition5_ba = ai40ontology.createTransition("Transition-5");
            transition6_ba = ai40ontology.createTransition("Transition-6");
            transition7_ba = ai40ontology.createTransition("Transition-7");


            //instantiating the set elements for the application model
            sequence_ba = ai40ontology.createSequence("Metal-Separation-Sequence");

            //instantiating the variables for the application model
            //Digital Outputs:
            convBeltOut = ai40ontology.createDigitalOutput("Conveyor-Belt-Output");
            metallicGate = ai40ontology.createDigitalOutput("Metallic-Gate-Output");
            nonMetallicGate = ai40ontology.createDigitalOutput("Non-Metallic-Gate-Output");

            //Digital Inputs:
            startButton = ai40ontology.createDigitalInput("Start-Button-Input");
            metalDetector = ai40ontology.createDigitalInput("Metal-Detector-Input");
            p1Detector = ai40ontology.createDigitalInput("Position-1-Detector");
            p2Detector = ai40ontology.createDigitalInput("Position-2-Detector");
            pfDetector = ai40ontology.createDigitalInput("Final-Position-Detector");

            //Analog Inputs:
            weight = ai40ontology.createAnalogInput("Weight");

            //Data Input:
            serialNumber = ai40ontology.createDataInput("Serial-Number");

            pieceData = ai40ontology.createDataOutput("Piece-Data");

            //instantiating the functions for the application model
            readMetalDetectorFunction = ai40ontology.createFunction("Read-Metal-Detector-Function");
            requestPieceDataFunction = ai40ontology.createRequestFunction("Request-Piece-Data-Function");
            sendPieceDataFunction = ai40ontology.createFunction("Send-Piece-Data-Function");
            performSeparationFunction = ai40ontology.createFunction("Perform-Separation-Function");
            saveDataFunction = ai40ontology.createFunction("Save-Data-Function");

            //instantiating entities which are not actors (non-processing capabilities)
            conveyorBelt = ai40ontology.createObjectO("Conveyor-Belt");


            /**
             * *
             **** Properties *
             */
            //defining domain properties
            industrialAutomationDomain.addIsStandardizedBy(std_iec_62890);
            industrialAutomationDomain.addIsStandardizedBy(std_iec_61131);
            industrialAutomationDomain.addIsStandardizedBy(std_iec_62264);
            industrialAutomationDomain.addIsStandardizedBy(std_iec_61512);
            industrialAutomationDomain.addIsStandardizedBy(std_iec_61499);
            industrialAutomationDomain.addIsStandardizedBy(std_opcua);
            industrialAutomationDomain.addIsStandardizedBy(std_w3c);

            iotDomain.addIsStandardizedBy(std_opcua);


            std_iec_61131.addHasDBpediaResource("http://dbpedia.org/page/IEC_61131");
            std_iec_61131.addHasOfficialResource("https://webstore.iec.ch/publication/62427");
            std_iec_61131.addHasSoftwareContent("SFC,LD,FBD,IL,ST");
            std_iec_61131.addConcernsTo("Device");

            std_iec_61499.addHasDBpediaResource("http://dbpedia.org/page/IEC_61499");
            std_iec_61499.addHasOfficialResource("https://webstore.iec.ch/publication/5506");
            std_iec_61499.addHasSoftwareContent("FB,ECC");
            std_iec_61499.addConcernsTo("Device");

            std_iec_61512.addHasDBpediaResource("http://dbpedia.org/page/ISA-88");
            std_iec_61512.addHasOfficialResource("https://webstore.iec.ch/publication/5531");
            std_iec_61512.addHasSoftwareContent("");
            std_iec_61512.addConcernsTo("SoftwareResource");

            std_iec_62264.addHasDBpediaResource("http://dbpedia.org/page/IEC_62264");
            std_iec_62264.addHasOfficialResource("https://webstore.iec.ch/publication/6675");
            std_iec_62264.addHasSoftwareContent("ECSIntegration");
            std_iec_62264.addConcernsTo("Thing,Device,SoftwareResource");

            std_iec_62890.addHasOfficialResource("https://www.iec.ch/dyn/www/f?p=103:38:15939379842517::::FSP_ORG_ID,FSP_APEX_PAGE,FSP_PROJECT_ID:1250,23,20929");
            std_iec_62890.addHasDBpediaResource("");
            std_iec_62890.addHasSoftwareContent("");
            std_iec_62890.addConcernsTo("SoftwareResource");

            std_opcua.addHasDBpediaResource("http://live.dbpedia.org/page/OPC_Unified_Architecture");
            std_opcua.addHasOfficialResource("https://opcfoundation.org/about/opc-technologies/opc-ua/");
            std_opcua.addHasSoftwareContent("OPC-UA,M2M");
            std_opcua.addConcernsTo("Thing,Device,SoftwareResource");

            std_w3c.addHasOfficialResource("https://www.w3.org/");
            std_w3c.addHasDBpediaResource("http://dbpedia.org/page/Semantic_Web");
            std_w3c.addHasSoftwareContent("OWL,RDF,SWRL,SPARQL");
            std_w3c.addConcernsTo("SoftwareResource");

            //defining the architecture properties
            distributedAutomationArchitecture.addIsDeployedThrough(applicationNetwork);
            //iotArchitecture.addIsImplementedBy(applicationNetwork);

            distributedAutomationArchitecture.addIsComposedOf(agentThing);
            distributedAutomationArchitecture.addIsComposedOf(agentDevice);
            distributedAutomationArchitecture.addIsComposedOf(agentServer);
            
            //defining the network properties
            applicationNetwork.addTransmits(serialData);
            applicationNetwork.addTransmits(pieceProcessData);
            applicationNetwork.addTransmits(pieceMaterialData);
            
            applicationNetwork.addSpreads(saveDataService);
            applicationNetwork.addSpreads(sendPieceDataService);
            applicationNetwork.addSpreads(performMaterialSeparationService);

            //iotArchitecture.addIsComposedOf(actorIII);
            //defining the asset properties
            AssetMetalSeparation.addIsCarriedOutIn(processMetalSeparation);

            //defining the process_ properties
            processMetalSeparation.addHasAssociatedModel(metalSeparationDDModel);

            /**
             *** defining the actor properties
             *
             */
            //functions
            agentThing.addExecutes(readMetalDetectorFunction);
            agentThing.addExecutes(sendPieceDataFunction);

            agentDevice.addExecutes(performSeparationFunction);
            agentDevice.addExecutes(requestPieceDataFunction);

            agentServer.addExecutes(saveDataFunction);

            //languages and protocols
            agentThing.addSupportsProtocol("OPC UA");
            agentThing.addSupportsProtocol("TCP");
            agentDevice.addSupportsProtocol("OPC UA");
            //deviceII.addSupportsProtocol("TCP");
            agentServer.addSupportsProtocol("OPC UA");
            agentServer.addSupportsProtocol("TCP");

            //info
            agentThing.addGenerates(pieceProcessData);
            agentThing.addGenerates(pieceMaterialData);
            agentThing.addReceives(serialData);
            agentDevice.addReceives(pieceMaterialData);
            agentServer.addGenerates(serialData);
            agentServer.addReceives(pieceProcessData);

            pieceMaterialData.addHasTransmitter(1);
            pieceMaterialData.addHasReceiver(2);
            pieceProcessData.addHasTransmitter(1);
            pieceProcessData.addHasReceiver(3);
            serialData.addHasTransmitter(3);
            serialData.addHasReceiver(1);

            //characteristics
            agentThing.addHasFeature("Thing");
            agentThing.addHasFeature("SoftwareResource");
            agentDevice.addHasFeature("Device");
            agentServer.addHasFeature("SoftwareResource");
            agentThing.addHasFeature("Reactive");
            agentDevice.addHasFeature("Reactive");
            agentServer.addHasFeature("Proactive");

            //description
            agentThing.addHasDescription("I am a thing and I manage the data in the metal separation process");
            agentDevice.addHasDescription("I am a controller device and I perform the operation of the metal separation process");
            agentServer.addHasDescription("I am a server and I manage the data in the network");

            //architecture level
            agentThing.addBelongsToArchitectureLayer(1);
            agentThing.addBelongsToArchitectureLayer(3);
            agentDevice.addBelongsToArchitectureLayer(2);
            agentServer.addBelongsToArchitectureLayer(4);

            //identifiers
            agentThing.addHasIdentifier(1);
            agentDevice.addHasIdentifier(2);
            agentServer.addHasIdentifier(3);

            //services
            agentThing.addOffers(sendPieceDataService);
            agentServer.addOffers(saveDataService);
            agentDevice.addOffers(performMaterialSeparationService);

            //defining the service properties
            sendPieceDataService.addHasDescription("This service sends the data of the scanned element with material, weight, and serial");
            saveDataService.addHasDescription("This service saves the data of the finished process loop in the database");
            performMaterialSeparationService.addHasDescription("This service does the separation according to metallic or non-metallic element");

            //defining the discrete dynamics models properties
            metalSeparationDDModel.addHasModelElement(esMetalSeparationModel);
            metalSeparationDDModel.addHasModelElement(state0_ba);
            metalSeparationDDModel.addHasModelElement(state1_ba);
            metalSeparationDDModel.addHasModelElement(state2_ba);
            metalSeparationDDModel.addHasModelElement(state3_ba);
            metalSeparationDDModel.addHasModelElement(state4_ba);
            metalSeparationDDModel.addHasModelElement(state5_ba);
            metalSeparationDDModel.addHasModelElement(state6_ba);
            metalSeparationDDModel.addHasModelElement(state7_ba);
            metalSeparationDDModel.addHasModelElement(transition1_ba);
            metalSeparationDDModel.addHasModelElement(transition2_ba);
            metalSeparationDDModel.addHasModelElement(transition3_ba);
            metalSeparationDDModel.addHasModelElement(transition4_ba);
            metalSeparationDDModel.addHasModelElement(transition5_ba);
            metalSeparationDDModel.addHasModelElement(transition6_ba);
            metalSeparationDDModel.addHasModelElement(transition7_ba);
            metalSeparationDDModel.addHasModelElement(sequence_ba);
            //other instances from Model Element in this application

            //defining the data streams properties
            esMetalSeparationModel.addContains(metalDetector);
            esMetalSeparationModel.addContains(p1Detector);
            esMetalSeparationModel.addContains(p2Detector);
            esMetalSeparationModel.addContains(pfDetector);
            esMetalSeparationModel.addContains(weight);
            esMetalSeparationModel.addContains(pieceData);
            esMetalSeparationModel.addContains(serialNumber);
            esMetalSeparationModel.addContains(convBeltOut);
            esMetalSeparationModel.addContains(metallicGate);
            esMetalSeparationModel.addContains(nonMetallicGate);

            //defining the states properties
            state0_ba.addInitializesSequence(sequence_ba);
            state0_ba.addHasDescription("Initial state (state 0) of metal separation model");
            state0_ba.addHasNextState(state1_ba);
            state0_ba.addHasPreviousState(state6_ba);
            state0_ba.addHasPreviousState(state7_ba);
            state0_ba.addHasNumberOfTasks(0);
            state0_ba.addIsConnectedToTransition(transition1_ba);
            state0_ba.addBelongsToSequence(sequence_ba);
            state0_ba.addIsActive(true);

            state1_ba.addHasDescription("State 1 of metal separation model");
            state1_ba.addHasNextState(state2_ba);
            state1_ba.addHasPreviousState(state0_ba);
            state1_ba.addHasNumberOfTasks(0);
            state1_ba.addIsConnectedToTransition(transition2_ba);
            state1_ba.addBelongsToSequence(sequence_ba);

            state2_ba.addHasDescription("State 2 of metal separation model");
            state2_ba.addHasNextState(state3_ba);
            state2_ba.addHasPreviousState(state1_ba);
            state2_ba.addHasNumberOfTasks(1);
            state2_ba.addIsConnectedToTransition(transition3_ba);
            state2_ba.addBelongsToSequence(sequence_ba);

            state3_ba.addHasDescription("State 3 of metal separation model");
            state3_ba.addHasNextState(state4_ba);
            state3_ba.addHasPreviousState(state2_ba);
            state3_ba.addHasNumberOfTasks(0);
            state3_ba.addIsConnectedToTransition(transition4_ba);
            state3_ba.addBelongsToSequence(sequence_ba);

            state4_ba.addHasDescription("State 4 of metal separation model");
            state4_ba.addHasNextState(state5_ba);
            state4_ba.addHasPreviousState(state3_ba);
            state4_ba.addHasNumberOfTasks(3);
            state4_ba.addIsConnectedToTransition(transition5_ba);
            state4_ba.addBelongsToSequence(sequence_ba);

            state5_ba.addHasDescription("State 5 of metal separation model");
            state5_ba.addHasNextState(state6_ba);
            state5_ba.addHasPreviousState(state4_ba);
            state5_ba.addHasNumberOfTasks(0);
            state5_ba.addIsConnectedToTransition(transition6_ba);
            state4_ba.addBelongsToSequence(sequence_ba);

            state6_ba.addFinalizesSequence(sequence_ba);
            state6_ba.addHasDescription("Final state (state 6) of metal separation model");
            state6_ba.addHasNextState(state0_ba);
            state6_ba.addHasPreviousState(state5_ba);
            state6_ba.addHasNumberOfTasks(1);
            state6_ba.addIsConnectedToTransition(transition7_ba);
            state6_ba.addBelongsToSequence(sequence_ba);

            state7_ba.addFinalizesSequence(sequence_ba);
            state7_ba.addHasDescription("Final state (state 7) concurrent state of metal separation model - only for testing purposes");
            state7_ba.addHasNextState(state0_ba);
            state7_ba.addHasPreviousState(state5_ba);
            state7_ba.addHasNumberOfTasks(0);
            state7_ba.addIsConnectedToTransition(transition7_ba);
            state7_ba.addBelongsToSequence(sequence_ba);

            //defining the transitions properties
            transition1_ba.addHasDescription("Transition 1 between state 0 and state 1 of the metal separation model");
            transition1_ba.addIsConnectedToState(state1_ba);
            transition1_ba.addIsEnabled(false);
            transition1_ba.addBelongsToSequence(sequence_ba);

            transition2_ba.addHasDescription("Transition 2 between state 1 and state 2 of the metal separation model");
            transition2_ba.addIsConnectedToState(state2_ba);
            transition2_ba.addIsEnabled(false);
            transition2_ba.addBelongsToSequence(sequence_ba);

            transition3_ba.addHasDescription("Transition 3 between state 2 and state 3 of the metal separation model");
            transition3_ba.addIsConnectedToState(state3_ba);
            transition3_ba.addIsEnabled(false);
            transition3_ba.addBelongsToSequence(sequence_ba);

            transition4_ba.addHasDescription("Transition 4 between state 3 and state 4 of the metal separation model");
            transition4_ba.addIsConnectedToState(state4_ba);
            transition4_ba.addIsEnabled(false);
            transition4_ba.addBelongsToSequence(sequence_ba);

            transition5_ba.addHasDescription("Transition 5 between state 4 and state 5 of the metal separation model");
            transition5_ba.addIsConnectedToState(state5_ba);
            transition5_ba.addIsEnabled(false);
            transition5_ba.addBelongsToSequence(sequence_ba);

            transition6_ba.addHasDescription("Transition 6 between state 5 and states 6-7 of the metal separation model");
            transition6_ba.addIsConnectedToState(state6_ba);
            transition6_ba.addIsConnectedToState(state7_ba);
            transition6_ba.addIsEnabled(false);
            transition6_ba.addBelongsToSequence(sequence_ba);

            transition7_ba.addHasDescription("Transition 7 between states 6-7 and state 0 of the metal separation model");
            transition7_ba.addIsConnectedToState(state0_ba);
            transition7_ba.addIsEnabled(false);
            transition7_ba.addBelongsToSequence(sequence_ba);

            //defining the set elements properties
            sequence_ba.addHasNumberOfStates(8);
            sequence_ba.addHasNumberOfTransitions(7);
            sequence_ba.addIsParentSetElement(true);

            //defining the variables properties
            convBeltOut.addActsOver(conveyorBelt);
            convBeltOut.addHasDataType("boolean");
            convBeltOut.addHasDescription("Motor activation control for the conveyor belt in the metal separation process");
            convBeltOut.addHasValue(false);
            convBeltOut.addIsContextVariableOf(esMetalSeparationModel);

            metallicGate.addActsOver(conveyorBelt);
            metallicGate.addHasDataType("boolean");
            metallicGate.addHasDescription("Gate activation for the metal elements in the metal separation process");
            metallicGate.addHasValue(false);
            metallicGate.addIsContextVariableOf(esMetalSeparationModel);

            nonMetallicGate.addActsOver(conveyorBelt);
            nonMetallicGate.addHasDataType("boolean");
            nonMetallicGate.addHasDescription("Gate activation for the non-metallic elements in the metal separation process");
            nonMetallicGate.addHasValue(false);
            nonMetallicGate.addIsContextVariableOf(esMetalSeparationModel);

            startButton.addMonitors(conveyorBelt);
            startButton.addHasDataType("boolean");
            startButton.addHasDescription("Start button in the metal separation model");
            startButton.addHasValue(false);
            startButton.addIsContextVariableOf(esMetalSeparationModel);

            metalDetector.addMonitors(conveyorBelt);
            metalDetector.addHasDataType("boolean");
            metalDetector.addHasDescription("Metal detector for pieces in the metal separation model");
            metalDetector.addHasValue(false);
            metalDetector.addIsContextVariableOf(esMetalSeparationModel);

            p1Detector.addMonitors(conveyorBelt);
            p1Detector.addHasDataType("boolean");
            p1Detector.addHasDescription("Position 1 reached detector in the metal separation process");
            p1Detector.addHasValue(false);
            p1Detector.addIsContextVariableOf(esMetalSeparationModel);

            p2Detector.addMonitors(conveyorBelt);
            p2Detector.addHasDataType("boolean");
            p2Detector.addHasDescription("Position 2 reached detector in the metal separation process");
            p2Detector.addHasValue(false);
            p2Detector.addIsContextVariableOf(esMetalSeparationModel);

            pfDetector.addMonitors(conveyorBelt);
            pfDetector.addHasDataType("boolean");
            pfDetector.addHasDescription("Final position reached detector in the metal separation process");
            pfDetector.addHasValue(false);
            pfDetector.addIsContextVariableOf(esMetalSeparationModel);

            weight.addMonitors(conveyorBelt);
            weight.addHasDataType("double");
            weight.addHasDescription("Weight of piece sensor in the metal separation process");
            weight.addHasValue("0.0");
            weight.addIsContextVariableOf(esMetalSeparationModel);

            serialNumber.addHasDataType("string");
            serialNumber.addHasDescription("Serial data input in the metal separation process");
            serialNumber.addHasValue("");
            serialNumber.addIsContextVariableOf(esMetalSeparationModel);

            pieceData.addHasDataType("string");
            pieceData.addHasDescription("Piece output data in the metal separation process");
            pieceData.addHasValue("");
            pieceData.addIsContextVariableOf(esMetalSeparationModel);

            //defining the functions properties
            readMetalDetectorFunction.addHasDescription("reads the metal detector state");
            readMetalDetectorFunction.addHasNumberOfParameters(0);
            readMetalDetectorFunction.addHasReturnDataType("boolean");
            readMetalDetectorFunction.addHasReturnValue(false);
            readMetalDetectorFunction.addIsExecutedBy(agentThing);

            requestPieceDataFunction.addHasDescription("requests the piece data from the device I");
            requestPieceDataFunction.addHasNumberOfParameters(0);
            requestPieceDataFunction.addHasReturnDataType("string");
            requestPieceDataFunction.addHasReturnValue("");
            requestPieceDataFunction.addIsExecutedBy(agentDevice);

            sendPieceDataFunction.addHasDescription("sends the piece data to the device II");
            sendPieceDataFunction.addHasNumberOfParameters(0);
            sendPieceDataFunction.addHasReturnDataType("void");
            sendPieceDataFunction.addHasReturnValue("");
            sendPieceDataFunction.addIsExecutedBy(agentThing);

            performSeparationFunction.addHasDescription("performs the metal separation operation");
            performSeparationFunction.addHasNumberOfParameters(1);
            performSeparationFunction.addReceivesParameter(pieceData);
            performSeparationFunction.addHasReturnDataType("boolean");
            performSeparationFunction.addHasReturnValue(false);
            performSeparationFunction.addIsExecutedBy(agentDevice);

            saveDataFunction.addHasDescription("saves the process data");
            saveDataFunction.addHasNumberOfParameters(0);
            saveDataFunction.addHasReturnDataType("boolean");
            saveDataFunction.addHasReturnValue(false);
            saveDataFunction.addIsExecutedBy(agentServer);

            //defining entities properties
            conveyorBelt.addHasDescription("Conveyor belt of the metal separation process");

            //System.out.println(ai40ontology.getOwlOntology());
            queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);
            swrlEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);
            
            
            //swrlEngine.infer();

            // Create SQWRL query engine using the SWRLAPI
            // Create and execute a SQWRL query using the SWRLAPI
            //SQWRLResult result = queryEngine.runSQWRLQuery("q1", "swrlb:add(?x, 2, 4) -> sqwrl:select(?x)");
            //SQWRLResult result2 = queryEngine.runSQWRLQuery("q2", "swrlb:add(?y, 2, 7) -> sqwrl:select(?y)");
            /*try {
                Optional<SWRLAPIRule> activeStateRule = swrlEngine.getSWRLRule("StateActiveByResourceRule");
                System.out.println(activeStateRule);
            } catch (SWRLRuleException ex) {
                Logger.getLogger(AutomationI40OntologyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }*/

 /*
            
            // Process the SQWRL result
            if (result.next()) {
                System.out.println("x: " + result.getLiteral("x").getInteger());

            }
            if (result2.next()) {
                System.out.println("y: " + result2.getLiteral("y").getInteger());

            }*/
            //setting the timer for the process
            threadMetalProcess = new Thread() {
                @Override
                public void run() {
                    //timerMetalProcess.purge();
                    timerMetalProcess.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            metalSeparationSequenceOperation();
                            jPanelModel.repaint();
                        }
                    }, 1000, 2000);
                }
            };
            threadMetalProcess.run();

            inferenceThread = new Thread() {
                @Override
                public void run() {
                    inferenceTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            callQueries();
                        }
                    }, 1000, 20000);

                }
            };
            inferenceThread.run();
            opcserverThread = new Thread() {
                public void run() {
                    /**
                     * Begins OPC SERVER
                     *
                     */

                    try {
                        server = new OPCUAServer();

                        server.startup().get();
                        uaServer = server.getServer();
                        uaServerContext = new AttributeContext(uaServer);

                        final CompletableFuture<Void> future = new CompletableFuture<>();

                        //Runtime.getRuntime().addShutdownHook(new Thread(() -> future.complete(null)));
                        //future.get();
                    } catch (Exception ex) {
                        Logger.getLogger(AutomationI40OntologyFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    /**
                     * Ends OPC SERVER
                     *
                     */
                }
            };
            opcserverThread.run();

            opcserverEventsThread = new Thread() {
                public void run() {
                    opcEventsTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {

                            try {
                                ServerNode nodeDeviceI = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceI")).get();
                                ServerNode nodeDeviceII = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceII")).get();
                                ServerNode nodeServerI = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ServerI")).get();
                                
                                ServerNode state0Node = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 0")).get();
                                ServerNode state1Node = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 1")).get();
                                ServerNode state2Node = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 2")).get();
                                ServerNode state3Node = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 3")).get();
                                ServerNode state4Node = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 4")).get();
                                ServerNode state5Node = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 5")).get();
                                ServerNode state6Node = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 6")).get();
                                ServerNode state7Node = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 7")).get();
                                
                                DataValue eventNotifierDevI = nodeDeviceI.getAttribute(uaServerContext, AttributeId.EventNotifier);
                                //System.out.println("Event notifier DevI: " + eventNotifierDevI);
                                
                                ServerNode nodeDeviceI_readOk = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceI.ReadOk")).get();
                                ServerNode nodeDeviceI_pieceMaterialData = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceI.PieceMaterialData")).get();
                                ServerNode nodeDeviceI_sendMaterialDataService = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceI.sendPieceMaterialData()")).get();
                                
                                
                                ServerNode nodeDeviceII_performOk = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceII.PerformOk")).get();
                                ServerNode nodeDeviceII_requestMaterialData = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceII.requestMaterialData()")).get();
                                
                                ServerNode nodeServerI_saveOk = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ServerI.SaveOk")).get();
                                ServerNode nodeServerI_serialData = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ServerI.SerialData")).get();
                                ServerNode nodeServerI_requestMaterialData = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ServerI.savePieceMaterialProcessData()")).get();

                                ServerNode nodeConvBelt_p1 = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.Position1Detector")).get();
                                ServerNode nodeConvBelt_p2 = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.Position2Detector")).get();
                                ServerNode nodeConvBelt_pf = server.getServer().getNodeMap().getNode(NodeId.parse("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.PositionFinalDetector")).get();
                                
                                DataValue attributeS0 = state0Node.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute S0: " + attributeS0);
                                
                                DataValue attributeS1 = state1Node.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute S1: " + attributeS1);
                                
                                DataValue attributeS2 = state2Node.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute S2: " + attributeS2);
                                
                                DataValue attributeS3 = state3Node.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute S3: " + attributeS3);
                                
                                DataValue attributeS4 = state4Node.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute S4: " + attributeS4);
                                
                                DataValue attributeS5 = state5Node.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute S5: " + attributeS5);
                                
                                DataValue attributeS6 = state6Node.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute S6: " + attributeS6);
                                
                                DataValue attributeS7 = state7Node.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute S7: " + attributeS7);
                                
                                DataValue attributeReadOk = nodeDeviceI_readOk.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute DeviceI.ReadOk: " + attributeReadOk);
                                
                                DataValue attributePieceMaterialData = nodeDeviceI_pieceMaterialData.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute DeviceI.PieceMaterialData: " + attributePieceMaterialData);
                                                                
                                DataValue attributePerformOk = nodeDeviceII_performOk.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute DeviceII.PerformOk: " + attributePerformOk);
                                
                                DataValue attributeSaveOk = nodeServerI_saveOk.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute ServerI.SaveOk: " + attributeSaveOk);
                                
                                DataValue attributeSerialData = nodeServerI_serialData.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute ServerI.SerialData: " + attributeSerialData);
                                
                                DataValue attributePos1 = nodeConvBelt_p1.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute ConveyorBelt.P1Detector: " + attributePos1);
                                DataValue attributePos2 = nodeConvBelt_p2.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute ConveyorBelt.P2Detector: " + attributePos2);
                                DataValue attributePosf = nodeConvBelt_pf.getAttribute(uaServerContext, AttributeId.Value);
                                //System.out.println("Attribute ConveyorBelt.PFDetector: " + attributePosf);
                                
                                
                                //Setting the attributes
                                nodeServerI_serialData.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant("00013564")));
                                nodeServerI_saveOk.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(saveResult)));
                                
                                //System.out.println("Is Active? : " + state0_ba.getIsActive());
                                for (boolean act : state0_ba.getIsActive())
                                {
                                    state0Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                for (boolean act : state1_ba.getIsActive())
                                {
                                    state1Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                for (boolean act : state2_ba.getIsActive())
                                {
                                    state2Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                for (boolean act : state3_ba.getIsActive())
                                {
                                    state3Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                for (boolean act : state4_ba.getIsActive())
                                {
                                    state4Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                for (boolean act : state4_ba.getIsActive())
                                {
                                    state4Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                for (boolean act : state5_ba.getIsActive())
                                {
                                    state5Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                for (boolean act : state6_ba.getIsActive())
                                {
                                    state6Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                for (boolean act : state7_ba.getIsActive())
                                {
                                    state7Node.setAttribute(uaServerContext, AttributeId.Value, new DataValue(new Variant(act)));
                                }
                                
                                //Adding the features for the automatic process
                                saveDataFunction.addHasReturnValue(attributeSaveOk.getValue().getValue());
                                readMetalDetectorFunction.addHasReturnValue(attributeReadOk.getValue().getValue());
                                performSeparationFunction.addHasReturnValue(attributePerformOk.getValue().getValue());
                                p1Detector.addHasValue(attributePos1.getValue().getValue());
                                p2Detector.addHasValue(attributePos2.getValue().getValue());
                                pfDetector.addHasValue(attributePosf.getValue().getValue());
                                
                                
                                
                                
                                
                                

                                
                            } catch (UaException ex) {
                                Logger.getLogger(AutomationI40OntologyFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }, 1000, 500);

                }
            };
            opcserverEventsThread.run();

        }catch (OWLOntologyCreationException e) {
            System.err.println("Error creating OWL ontology: " + e.getMessage());
            System.exit(-1);
        }
        //System.exit(-1);
        

    }
    
    
    public void saveData()
    {
        if (saveDataFunction.getHasReturnValue().contains(true)){
            saveResult = true;
        }else
        {
            saveResult = false;
        }
        
    }

    public void metalSeparationSequenceOperation() {

        //initializing the sequence
        /*state0_resource_ba.addHasValue(true);
        state1_resource_ba.addHasValue(false);
        state2_resource_ba.addHasValue(false);
        state3_resource_ba.addHasValue(false);
        state4_resource_ba.addHasValue(false);
        state5_resource_ba.addHasValue(false);
        state6_resource_ba.addHasValue(false);*/
        

        if (startButton.getHasValue().contains(true)) {
            transition1_ba.addIsEnabled(true);
            transition1_ba.removeIsEnabled(false);

            startButton.removeHasValue(true);
            //startButton.addHasValue(false);

        } else {
            transition1_ba.addIsEnabled(false);
            transition1_ba.removeIsEnabled(true);
        }

        if (p1Detector.getHasValue().contains(true)) {
            transition2_ba.addIsEnabled(true);
            transition2_ba.removeIsEnabled(false);

            p1Detector.removeHasValue(true);
        } else {
            transition2_ba.addIsEnabled(false);
            transition2_ba.removeIsEnabled(true);
        }

        if (readMetalDetectorFunction.getHasReturnValue().contains(true)) {
            transition3_ba.addIsEnabled(true);
            transition3_ba.removeIsEnabled(false);

            readMetalDetectorFunction.removeHasReturnValue(true);
        } else {
            transition3_ba.addIsEnabled(false);
            transition3_ba.removeIsEnabled(true);
        }

        if (p2Detector.getHasValue().contains(true)) {
            transition4_ba.addIsEnabled(true);
            transition4_ba.removeIsEnabled(false);

            p2Detector.removeHasValue(true);
        } else {
            transition4_ba.addIsEnabled(false);
            transition4_ba.removeIsEnabled(true);
        }

        if (performSeparationFunction.getHasReturnValue().contains(true)) {
            transition5_ba.addIsEnabled(true);
            transition5_ba.removeIsEnabled(false);

            performSeparationFunction.removeHasReturnValue(true);
        } else {
            transition5_ba.addIsEnabled(false);
            transition5_ba.removeIsEnabled(true);
        }

        if (pfDetector.getHasValue().contains(true)) {
            transition6_ba.addIsEnabled(true);
            transition6_ba.removeIsEnabled(false);

            pfDetector.removeHasValue(true);
        } else {
            transition6_ba.addIsEnabled(false);
            transition6_ba.removeIsEnabled(true);
        }

        if (saveDataFunction.getHasReturnValue().contains(true)) {
            transition7_ba.addIsEnabled(true);
            transition7_ba.removeIsEnabled(false);

            saveDataFunction.removeHasReturnValue(true);
        } else {
            transition7_ba.addIsEnabled(false);
            transition7_ba.removeIsEnabled(true);
        }

        //State 0 execution
        if (state0_ba.getIsActive().contains(true)) {
            saveDataFunction.removeHasReturnValue(true);
            if (updateInferCheckbox) {
                swrlEngine.infer();
            }
            if (transition1_ba.getIsEnabled().contains(true)) {
                state0_ba.addIsActive(false);
                state0_ba.removeIsActive(true);
                state1_ba.addIsActive(true);
                state1_ba.removeIsActive(false);
            }
        }

        //State 1 execution
        if (state1_ba.getIsActive().contains(true)) {
            if (updateInferCheckbox) {
                swrlEngine.infer();
            }
            if (transition2_ba.getIsEnabled().contains(true)) {
                state1_ba.addIsActive(false);
                state1_ba.removeIsActive(true);
                state2_ba.addIsActive(true);
                state2_ba.removeIsActive(false);
            }
        }

        //State 2 execution
        if (state2_ba.getIsActive().contains(true)) {
            if (updateInferCheckbox) {
                swrlEngine.infer();
            }
            if (transition3_ba.getIsEnabled().contains(true)) {
                state2_ba.addIsActive(false);
                state2_ba.removeIsActive(true);
                state3_ba.addIsActive(true);
                state3_ba.removeIsActive(false);
            }
        }

        //State 3 execution
        if (state3_ba.getIsActive().contains(true)) {
            if (updateInferCheckbox) {
                swrlEngine.infer();
            }
            if (transition4_ba.getIsEnabled().contains(true)) {
                state3_ba.addIsActive(false);
                state3_ba.removeIsActive(true);
                state4_ba.addIsActive(true);
                state4_ba.removeIsActive(false);
            }
        }

        //State 4 execution
        if (state4_ba.getIsActive().contains(true)) {
            if (updateInferCheckbox) {
                swrlEngine.infer();
            }
            if (transition5_ba.getIsEnabled().contains(true)) {
                state4_ba.addIsActive(false);
                state4_ba.removeIsActive(true);
                state5_ba.addIsActive(true);
                state5_ba.removeIsActive(false);
            }
        }

        //State 5 execution
        if (state5_ba.getIsActive().contains(true)) {
            if (updateInferCheckbox) {
                swrlEngine.infer();
            }
            if (transition6_ba.getIsEnabled().contains(true)) {
                state5_ba.addIsActive(false);
                state5_ba.removeIsActive(true);
                state6_ba.addIsActive(true);
                state6_ba.removeIsActive(false);
                state7_ba.addIsActive(true);
                state7_ba.removeIsActive(false);
            }
        }

        //State 6 execution
        if (state6_ba.getIsActive().contains(true)) {
            saveData();
            if (updateInferCheckbox) {
                swrlEngine.infer();
            }

            if (transition7_ba.getIsEnabled().contains(true)) {
                saveResult = false;
                state6_ba.addIsActive(false);
                state6_ba.removeIsActive(true);
                state0_ba.addIsActive(true);
                state0_ba.removeIsActive(false);
            }
        }

        if (state7_ba.getIsActive().contains(true)) {

            if (transition7_ba.getIsEnabled().contains(true)) {
                state7_ba.addIsActive(false);
                state7_ba.removeIsActive(true);
                if (!state0_ba.getIsActive().contains(true)) {
                    state0_ba.addIsActive(true);
                    state0_ba.removeIsActive(false);
                }
            }
        }

    }

    private void callQueries() {
        try {
            long startTime = System.nanoTime();
            
            SQWRLResult q0Result = queryEngine.runSQWRLQuery("QueryActiveStates");

            String messageResult = "Active State Query Inference Result:\n";
            while (q0Result.next()) {
                messageResult += "Active State: " + q0Result.getValue("s") + "\n";
            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            SQWRLResult q1Result = queryEngine.runSQWRLQuery("QueryCurrentStates");

            messageResult = "Current State Query Inference Result:\n";
            while (q1Result.next()) {
                messageResult += "Current State: " + q1Result.getValue("cs") + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Next State Query Inference Result:\n";
            SQWRLResult q2Result = queryEngine.runSQWRLQuery("QueryNextStates");
            while (q2Result.next()) {
                messageResult += "Next State: " + q2Result.getValue("ns") + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Previous State Query Inference Result:\n";
            SQWRLResult q3Result = queryEngine.runSQWRLQuery("QueryPreviousStates");
            while (q3Result.next()) {
                messageResult += "Previous State: " + q3Result.getValue("ps") + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Things Query Inference Result:\n";
            SQWRLResult q5Result = queryEngine.runSQWRLQuery("QueryThings");
            while (q5Result.next()) {
                messageResult += "I am a Thing: " + q5Result.getValue("x") + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Services per Agent Query Inference Result:\n";
            SQWRLResult q6Result = queryEngine.runSQWRLQuery("QueryServicesPerAgent");
            while (q6Result.next()) {
                messageResult += "I am: " + q6Result.getValue("a") + " and I offer: " + q6Result.getValue("s") + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Standards and Embedding Capability Query Inference Result:\n";
            SQWRLResult q7Result = queryEngine.runSQWRLQuery("QueryStandardAndEmbeddingCapability");
            while (q7Result.next()) {
                messageResult += "Standard: " + q7Result.getValue("s") + " | Embedding capability: " + q7Result.getValue("c") + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Parent Sequences Query Inference Result:\n";
            SQWRLResult q8Result = queryEngine.runSQWRLQuery("QueryParentSequence");
            while (q8Result.next()) {
                messageResult += "Parent Sequence: " + q8Result.getValue("s") + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Agent Architecture Layer Query Inference Result:\n";
            SQWRLResult q9Result = queryEngine.runSQWRLQuery("QueryAgentAndArchitectureLayer");
            while (q9Result.next()) {
                messageResult += "Agent " + q9Result.getValue("a") + " located in : " + q9Result.getValue("lvl") + " architecture layer (ISA95)\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Agent with 'controller' Description Query Inference Result:\n";
            SQWRLResult q10Result = queryEngine.runSQWRLQuery("QueryAgentWithControllerDescription");
            while (q10Result.next()) {
                messageResult += "Agent " + q10Result.getValue("a").toString() + " contains 'controller' in description\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Concurrent State Query Inference Result:\n";
            SQWRLResult q11Result = queryEngine.runSQWRLQuery("QueryConcurrentStates");
            while (q11Result.next()) {
                messageResult += "Concurrent State : " + q11Result.getValue("cs").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Devices Query Inference Result:\n";
            SQWRLResult q12Result = queryEngine.runSQWRLQuery("QueryDevices");
            while (q12Result.next()) {
                messageResult += "I am a Device : " + q12Result.getValue("x").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Final State Query Inference Result:\n";
            SQWRLResult q13Result = queryEngine.runSQWRLQuery("QueryFinalStates");
            while (q13Result.next()) {
                messageResult += "Final State : " + q13Result.getValue("fs").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Agents with High Interoperability Degree Query Inference Result:\n";
            SQWRLResult q14Result = queryEngine.runSQWRLQuery("QueryForAgentsWithHighInteroperabilityDegree");
            while (q14Result.next()) {
                messageResult += "Agent with high interoperability degree : " + q14Result.getValue("x").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Initial State Query Inference Result:\n";
            SQWRLResult q15Result = queryEngine.runSQWRLQuery("QueryInitialStates");
            while (q15Result.next()) {
                messageResult += "Initial state : " + q15Result.getValue("is").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Services with 'database' Description Query Inference Result:\n";
            SQWRLResult q16Result = queryEngine.runSQWRLQuery("QueryServicesWithDatabaseDescription");
            while (q16Result.next()) {
                messageResult += "Service : " + q16Result.getValue("s").toString() + " contains 'database' in service description\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Software Resources Query Inference Result:\n";
            SQWRLResult q17Result = queryEngine.runSQWRLQuery("QuerySoftwareResource");
            while (q17Result.next()) {
                messageResult += "I am a Software Resource : " + q17Result.getValue("x").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Synchronous State Query Inference Result:\n";
            SQWRLResult q19Result = queryEngine.runSQWRLQuery("QuerySynchronousStates");
            while (q19Result.next()) {
                messageResult += "Synchronous State : " + q19Result.getValue("ss").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Standards Which Concern to Devices Query Inference Result:\n";
            SQWRLResult q20Result = queryEngine.runSQWRLQuery("QueryWhatStandardsStandardizeDevices");
            while (q20Result.next()) {
                messageResult += "Standard : " + q20Result.getValue("s").toString() + " concerns to devices\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Interaction Pairs (Agents) Query Inference Result:\n";
            SQWRLResult q21Result = queryEngine.runSQWRLQuery("QueryForInteractionPairsAgent");
            while (q21Result.next()) {
                messageResult += "Agent " + q21Result.getValue("x").toString() + " interacts with Agent " + q21Result.getValue("y").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Proactive Agents Query Inference Result:\n";
            SQWRLResult q22Result = queryEngine.runSQWRLQuery("QueryProactiveAgents");
            while (q22Result.next()) {
                messageResult += "Agent " + q22Result.getValue("a").toString() + " is proactive\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Reactive Agents Query Inference Result:\n";
            SQWRLResult q23Result = queryEngine.runSQWRLQuery("QueryReactiveAgents");
            while (q23Result.next()) {
                messageResult += "Agent " + q23Result.getValue("a").toString() + " is reactive\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            

        } catch (SQWRLException ex) {
            Logger.getLogger(AutomationI40OntologyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanelModel = new PaintPane();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automation Ontology I4.0 Example");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Automation I4.0 Ontology Case Study GUI");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Controls");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 167, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setText("Monitoring");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("Discrete Dynamics Model");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, -1, -1));

        jButton1.setBackground(new java.awt.Color(204, 227, 253));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton1.setText("Start Button");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jPanelModel.setBackground(new java.awt.Color(204, 204, 204));
        jPanelModel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel7.setText("State 0");
        jPanelModel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 40, 20));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel8.setText("State 1");
        jPanelModel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 40, 20));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel9.setText("State 2");
        jPanelModel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 40, 20));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel10.setText("State 3");
        jPanelModel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 40, 20));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel11.setText("State 4");
        jPanelModel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 40, 20));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel12.setText("State 5");
        jPanelModel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 40, 20));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel13.setText("State 6");
        jPanelModel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 40, 20));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel14.setText("State 7");
        jPanelModel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 40, 20));

        jPanel1.add(jPanelModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 330, 430));

        jButton2.setBackground(new java.awt.Color(204, 227, 253));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton2.setText("P1Detector");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, -1));

        jButton3.setBackground(new java.awt.Color(204, 227, 253));
        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton3.setText("P2Detector");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 110, -1));

        jButton4.setBackground(new java.awt.Color(204, 227, 253));
        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton4.setText("PFDetector");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 110, -1));

        jButton5.setBackground(new java.awt.Color(204, 227, 253));
        jButton5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton5.setText("read done");
        jButton5.setToolTipText("");
        jButton5.setPreferredSize(new java.awt.Dimension(80, 27));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 110, -1));

        jButton6.setBackground(new java.awt.Color(204, 227, 253));
        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton6.setText("sep done");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 110, -1));

        jButton7.setBackground(new java.awt.Color(204, 227, 253));
        jButton7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton7.setText("save done");
        jButton7.setToolTipText("");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 110, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setText("Metal Separation Process");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, -1, 40));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 470, 180));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel6.setText("Inferences");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, 20));

        jButton8.setBackground(new java.awt.Color(204, 227, 253));
        jButton8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton8.setText("Complete Log");
        jButton8.setToolTipText("");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 610, 140, 30));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel15.setText("Optional (Simulation)");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 160, 20));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("© Santiago Gil - Automation I4.0 Ontology Project");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 630, -1, -1));

        jCheckBox1.setBackground(new java.awt.Color(204, 242, 244));
        jCheckBox1.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jCheckBox1.setText("Update SWRL Infer on state change");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        startButton.addHasValue(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        p1Detector.addHasValue(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        p2Detector.addHasValue(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pfDetector.addHasValue(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        readMetalDetectorFunction.addHasReturnValue(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        performSeparationFunction.addHasReturnValue(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        saveDataFunction.addHasReturnValue(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        infFrame.setVisible(true);
        infFrame.setLog(logMessage);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
            updateInferCheckbox = true;
        }else
        {
            updateInferCheckbox = false;
        }
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AutomationI40OntologyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutomationI40OntologyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutomationI40OntologyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutomationI40OntologyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        //</editor-fold>

        if (args.length > 1) {
            Usage();
        }

        Optional<String> owlFilename = args.length == 0 ? Optional.<String>empty() : Optional.of(args[0]);
        Optional<File> owlFile = (owlFilename != null && owlFilename.isPresent())
                ? Optional.of(new File(owlFilename.get()))
                : Optional.<File>empty();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutomationI40OntologyFrame(owlFile).setVisible(true);
            }
        });
    }

    private static void Usage() {
        System.err.println("Usage: " + AutomationI40OntologyFrame.class.getName() + " [ <owlFileName> ]");
        System.exit(1);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelModel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    private class PaintPane extends JPanel {

        public PaintPane() {
        }

        /**
         * Draws Star shape
         *
         * @param g2
         */
        private void drawShape(Graphics2D g2) {
            // Set parameters 
            int x = 25;
            int w = 25;
            int h = 5;
            Rectangle bounds = new Rectangle(10, 10, 100, 100);
            double tipSize = 40;
            Ellipse2D.Double s0 = new Ellipse2D.Double(80, 10, x, x);

            Ellipse2D.Double s1 = new Ellipse2D.Double(80, 90, x, x);

            Ellipse2D.Double s2 = new Ellipse2D.Double(80, 170, x, x);
            Ellipse2D.Double s3 = new Ellipse2D.Double(80, 250, x, x);
            Ellipse2D.Double s4 = new Ellipse2D.Double(80, 330, x, x);
            Ellipse2D.Double s5 = new Ellipse2D.Double(160, 330, x, x);
            Ellipse2D.Double s6 = new Ellipse2D.Double(240, 300, x, x);
            Ellipse2D.Double s7 = new Ellipse2D.Double(240, 360, x, x);

            Rectangle2D.Double t1 = new Rectangle2D.Double(80, 50, w, h);
            Rectangle2D.Double t2 = new Rectangle2D.Double(80, 130, w, h);
            Rectangle2D.Double t3 = new Rectangle2D.Double(80, 210, w, h);
            Rectangle2D.Double t4 = new Rectangle2D.Double(80, 290, w, h);
            Rectangle2D.Double t5 = new Rectangle2D.Double(120, 330, h, w);
            Rectangle2D.Double t6 = new Rectangle2D.Double(200, 330, h, w);
            Rectangle2D.Double t7 = new Rectangle2D.Double(280, 210, w, h);

            if (state0_ba.getIsActive().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(s0);

                g2.setPaint(Color.green);

                g2.draw(s0);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(s0);

                g2.setPaint(Color.gray);

                g2.draw(s0);
            }

            if (state1_ba.getIsActive().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(s1);

                g2.setPaint(Color.green);

                g2.draw(s1);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(s1);

                g2.setPaint(Color.gray);

                g2.draw(s1);
            }

            if (state2_ba.getIsActive().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(s2);

                g2.setPaint(Color.green);

                g2.draw(s2);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(s2);

                g2.setPaint(Color.gray);

                g2.draw(s2);
            }

            if (state3_ba.getIsActive().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(s3);

                g2.setPaint(Color.green);

                g2.draw(s3);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(s3);

                g2.setPaint(Color.gray);

                g2.draw(s3);
            }

            if (state4_ba.getIsActive().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(s4);

                g2.setPaint(Color.green);

                g2.draw(s4);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(s4);

                g2.setPaint(Color.gray);

                g2.draw(s4);
            }
            if (state5_ba.getIsActive().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(s5);

                g2.setPaint(Color.green);

                g2.draw(s5);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(s5);

                g2.setPaint(Color.gray);

                g2.draw(s5);
            }
            if (state6_ba.getIsActive().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(s6);

                g2.setPaint(Color.green);

                g2.draw(s6);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(s6);

                g2.setPaint(Color.gray);

                g2.draw(s6);
            }
            if (state7_ba.getIsActive().contains(true)) {
                g2.setPaint(Color.orange);
                g2.fill(s7);

                g2.setPaint(Color.orange);

                g2.draw(s7);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(s7);

                g2.setPaint(Color.gray);

                g2.draw(s7);
            }

            if (transition1_ba.getIsEnabled().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(t1);

                g2.setPaint(Color.green);

                g2.draw(t1);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(t1);

                g2.setPaint(Color.gray);

                g2.draw(t1);
            }

            if (transition2_ba.getIsEnabled().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(t2);

                g2.setPaint(Color.green);

                g2.draw(t2);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(t2);

                g2.setPaint(Color.gray);

                g2.draw(t2);
            }
            if (transition3_ba.getIsEnabled().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(t3);

                g2.setPaint(Color.green);

                g2.draw(t3);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(t3);

                g2.setPaint(Color.gray);

                g2.draw(t3);
            }
            if (transition4_ba.getIsEnabled().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(t4);

                g2.setPaint(Color.green);

                g2.draw(t4);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(t4);

                g2.setPaint(Color.gray);

                g2.draw(t4);
            }
            if (transition5_ba.getIsEnabled().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(t5);

                g2.setPaint(Color.green);

                g2.draw(t5);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(t5);

                g2.setPaint(Color.gray);

                g2.draw(t5);
            }
            if (transition6_ba.getIsEnabled().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(t6);

                g2.setPaint(Color.green);

                g2.draw(t6);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(t6);

                g2.setPaint(Color.gray);

                g2.draw(t6);
            }
            if (transition7_ba.getIsEnabled().contains(true)) {
                g2.setPaint(Color.green);
                g2.fill(t7);

                g2.setPaint(Color.green);

                g2.draw(t7);
            } else {
                g2.setPaint(Color.gray);
                g2.fill(t7);

                g2.setPaint(Color.gray);

                g2.draw(t7);
            }

            /*String style = Star.STAR_16_POINTS;

        // 1. Create Shape
        Star star = new Star( bounds,  tipSize, style );

        // 2. Fill Shape
        g2.setPaint( Color.yellow );
        g2.fill( star );

        // 3. Draw Shape
        g2.setPaint( Color.blue );
        g2.draw(star);*/
        }

        /**
         * Paints this component
         *
         * @param g
         */
        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Rectangle bounds = this.getBounds();
            Shape oldClip = g2.getClip();
            g2.setClip(0, 0, bounds.width, bounds.height);

            drawShape(g2);

            g2.setClip(oldClip);

        }

        /**
         * Main method to run this program
         *
         * @param args
         */
    }

    private String getDate() {
        java.util.Calendar calendar;
        int day, month, year;
        calendar = new java.util.GregorianCalendar();
        java.util.Date actual = new java.util.Date();
        calendar.setTime(actual);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = (calendar.get(Calendar.MONTH) + 1);
        year = calendar.get(Calendar.YEAR);
        String date = String.format("%02d-%02d-%04d", day, month, year);
        return date;
    }

    private String getHour() {
        java.util.Calendar calendar;
        int hour, mins, segs;
        calendar = new java.util.GregorianCalendar();
        segs = calendar.get(Calendar.SECOND);
        java.util.Date actual = new java.util.Date();
        calendar.setTime(actual);
//dia = calendario.get(Calendar.DAY_OF_MONTH); 
//mes = (calendario.get(Calendar.MONTH) + 1); 
//año = calendario.get(Calendar.YEAR); 
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        mins = calendar.get(Calendar.MINUTE);
        segs = calendar.get(Calendar.SECOND);
        String returnHour = String.format("%02d:%02d:%02d", hour, mins, segs);
        return returnHour;
    }

}
