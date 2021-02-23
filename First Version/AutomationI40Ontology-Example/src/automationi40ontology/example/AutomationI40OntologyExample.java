/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automationi40ontology.example;
import aI40minontology.*;
import aI40minontology.impl.*;



import java.io.File;
import java.util.Optional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
/**
 *
 * @author Santiago Gil
 * @copyright Santiago Gil
 * Licensed under Apache License 2.0 (see more at http://www.apache.org/licenses/LICENSE-2.0)
 */
public class AutomationI40OntologyExample {
    

    public AutomationI40OntologyExample()
    {
        
    }

    /**
     * @param args the command line arguments
     */
    
//    public static void main(String[] args) {
//        if (args.length > 1) {
//            Usage();
//        }
//
//        
//        Optional<String> owlFilename = args.length == 0 ? Optional.<String>empty() : Optional.of(args[0]);
//        Optional<File> owlFile = (owlFilename != null && owlFilename.isPresent())
//                ? Optional.of(new File(owlFilename.get()))
//                : Optional.<File>empty();
//
//        try {
//            // Create an OWL ontology using the OWLAPI
//            OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
//            OWLOntology ontology = owlFile.isPresent()
//                    ? ontologyManager.loadOntologyFromOntologyDocument(owlFile.get())
//                    : ontologyManager.createOntology();
//            
//            /***
//            **** Instantiating the ontology
//            ***/
//            
//            AI40MinOntology ai40ontology = new AI40MinOntology(ontology);
//            
//            
//            /***
//            **** Instances / Individuals
//            ***/
//            
//            
//            
//            //instantiating the semantics
//            Semantics automationApplicationSemantics = ai40ontology.createSemantics("automation-application-semantics");
//            
//            //instantiating the domains
//            Domain industrialAutomationDomain = ai40ontology.createDomain("industrial-automation-domain");
//            Domain iotDomain = ai40ontology.createDomain("internet-of-things-domain");
//            
//            //instantiating the interoperabilities
//            SemanticInteroperability semanticInteroperability = ai40ontology.createSemanticInteroperability("semantic-application-interoperability");
//            SyntacticInteroperability syntacticInteroperability = ai40ontology.createSyntacticInteroperability("syntactic-application-interoperability");
//            
//            //instantiating the representations
//            Representation applicationRepresentation = ai40ontology.createRepresentation("example-application-representation");
//            
//            //instantiating the standards
//            Standard std_iec_61131 = ai40ontology.createStandard("iec-61131-standard");
//            Standard std_opcua = ai40ontology.createStandard("opc_ua-standard");
//            Standard std_iec_61499 = ai40ontology.createStandard("iec-61499-standard");
//            Standard std_iec_62264 = ai40ontology.createStandard("iec-62264-isa-95-standard");
//            Standard std_iec_61512 = ai40ontology.createStandard("iec-61512-isa-88-standard");
//            Standard std_iec_62890 = ai40ontology.createStandard("iec-62890-standard");
//            
//            //instantiating the architectures
//            Architecture distributedAutomationArchitecture = ai40ontology.createArchitecture("distributed-industrial-automation-architecture");
//            Architecture iotArchitecture = ai40ontology.createArchitecture("internet-of-things-architecture");
//            
//            //instantiating the languages
//            Language plainTextLanguage = ai40ontology.createLanguage("plain-text-language");
//            Language jsonFormattedTextLanguage = ai40ontology.createLanguage("json-formatted-text-language");
//            Language proprietaryLanguage = ai40ontology.createLanguage("example-proprietary-language");
//            
//            //instantiating the protocols
//            Protocol requestResponseProtocol = ai40ontology.createProtocol("request-response-protocol");
//            Protocol broadcastProtocol = ai40ontology.createProtocol("broadcast-protocol");
//            Protocol proprietaryProtocol = ai40ontology.createProtocol("example-proprietary-protocol");
//            
//            //instantiating the networks
//            Network applicationNetwork = ai40ontology.createNetwork("application-network");
//            
//            //instantiating the informations
//            Information pieceMaterialData = ai40ontology.createInformation("piece-material-data");
//            Information pieceProcessData = ai40ontology.createInformation("piece-process-data");
//            Information serialData = ai40ontology.createInformation("serial-data");
//            
//            //instantiating the product-assets
//            Product_Asset productAssetA = ai40ontology.createProduct_Asset("product-asset-a");
//            Product_Asset productAssetB = ai40ontology.createProduct_Asset("product-asset-b");            
//            Product_Asset productAssetC = ai40ontology.createProduct_Asset("product-asset-c");
//            Product_Asset productAssetSeparation = ai40ontology.createProduct_Asset("metal-separation");
//            
//            //instantiating the processes
//            Process_ processA = ai40ontology.createProcess_("process-a");
//            Process_ processB = ai40ontology.createProcess_("process-b");
//            Process_ processC = ai40ontology.createProcess_("process-c");
//            Process_ processMetalSeparation = ai40ontology.createProcess_("metallic/non-metallic-separation-process");
//            
//            //instantiating the actors
//            Actor actorI = ai40ontology.createActor("actorI");
//            Actor actorII = ai40ontology.createActor("actorII");
//            Actor actorIII = ai40ontology.createActor("actorIII");
//            
//            Actor deviceI = ai40ontology.createActor("deviceI");
//            Actor deviceII = ai40ontology.createActor("deviceII");
//            
//            //instantiating the services
//            Service serviceI = ai40ontology.createService("serviceI");
//            Service serviceII = ai40ontology.createService("serviceII");
//            Service serviceIII = ai40ontology.createService("serviceIII");
//            
//            Service sendPieceDataService = ai40ontology.createService("send-piece-material-data-service");
//            Service saveDataService = ai40ontology.createService("save-piece-data-service");
//            
//            //instantiating the discrete dynamics models
//            DiscreteDynamicsModel complexApplicationDDModel = ai40ontology.createDiscreteDynamicsModel("application-complex-discrete-dynamics-model");
//            DiscreteDynamicsModel metalSeparationDDModel = ai40ontology.createDiscreteDynamicsModel("metal-separation-discrete-dynamics-model");
//            
//            //instantiating the data streams
//            DataStream dsComplexModel = ai40ontology.createDataStream("data-stream-for-the-complex-application");
//            DataStream dsMetalSeparationModel = ai40ontology.createDataStream("data-stream-for-the-metal-separation-application");
//            
//            //instantiating the states for the complex application model
//            
//            //instantiating the states for the brief application model
//            // remark: ba_ suffix
//            InitialState state0_ba = ai40ontology.createInitialState("s0_ba");
//            State state1_ba = ai40ontology.createState("s1_ba");
//            State state2_ba = ai40ontology.createState("s2_ba");
//            State state3_ba = ai40ontology.createState("s3_ba");
//            State state4_ba = ai40ontology.createState("s4_ba");
//            State state5_ba = ai40ontology.createState("s5_ba");
//            FinalState state6_ba = ai40ontology.createFinalState("s6_ba");
//            
//            
//            
//            //instantiating the transitions for the complex application model
//            
//            //instantiating the transitions for the brief application model
//            Transition transition1_ba = ai40ontology.createTransition("t1_ba");
//            Transition transition2_ba = ai40ontology.createTransition("t2_ba");
//            Transition transition3_ba = ai40ontology.createTransition("t3_ba");
//            Transition transition4_ba = ai40ontology.createTransition("t4_ba");
//            Transition transition5_ba = ai40ontology.createTransition("t5_ba");
//            Transition transition6_ba = ai40ontology.createTransition("t6_ba");
//            Transition transition7_ba = ai40ontology.createTransition("t7_ba");
//            
//            //instantiating the arcs (only for the complex application model)
//            
//            //instantiating the set elements (complex application model)
//            
//            //instantiating the set elements (brief application model)
//            Sequence sequence_ba = ai40ontology.createSequence("main-sequence_ba");
//            
//            //instantiating the variables for the complex application model
//            
//            //instantiating the variables for the brief application model
//            //Digital Outputs:
//            DigitalOutput convBeltOut = ai40ontology.createDigitalOutput("conveyor-belt");
//            DigitalOutput metallicGate = ai40ontology.createDigitalOutput("metallic-gate");
//            DigitalOutput nonMetallicGate = ai40ontology.createDigitalOutput("non-metallic-gate");
//            
//            //Digital Inputs:
//            DigitalInput metalDetector = ai40ontology.createDigitalInput("metal-detector");
//            DigitalInput p1Detector = ai40ontology.createDigitalInput("position-1-detector");
//            DigitalInput p2Detector = ai40ontology.createDigitalInput("position-2-detector");
//            DigitalInput pfDetector = ai40ontology.createDigitalInput("position-final-detector");
//            
//            //Analog Inputs:
//            AnalogInput weight = ai40ontology.createAnalogInput("weight");
//            
//            //Data Input:
//            DataInput serialNumber = ai40ontology.createDataInput("serial-number");
//            
//            DataOutput pieceData = ai40ontology.createDataOutput("piece-data");
//            
//            
//            //instantiating the functions for the complex applicaton model
//            
//            
//            //instantiating the functions for the brief application model
//            Function readMetalDetectorFunction = ai40ontology.createFunction("read-metal-detector-function");
//            Function requestPieceDataFunction = ai40ontology.createRequestFunction("request-piece-material-data-function");
//            Function sendPieceDataFunction = ai40ontology.createAs_a_ServiceFunction("send-piece-material-data-function");
//            Function performSeparationFunction = ai40ontology.createFunction("perform-metal-separation-function");
//            Function saveDataFunction = ai40ontology.createAs_a_ServiceFunction("save-piece-data-function");
//            
//            //instantiating entities which are not actors (non-processing capabilities)
//            Entity conveyorBelt = ai40ontology.createEntity("conveyor-belt");
//            
//            //instantiating the Resources for the brief application model
//            Resource state0_resource_ba = ai40ontology.createResource("resource-state-0");
//            Resource state1_resource_ba = ai40ontology.createResource("resource-state-1");
//            Resource state2_resource_ba = ai40ontology.createResource("resource-state-2");
//            Resource state3_resource_ba = ai40ontology.createResource("resource-state-3");
//            Resource state4_resource_ba = ai40ontology.createResource("resource-state-4");
//            Resource state5_resource_ba = ai40ontology.createResource("resource-state-5");
//            Resource state6_resource_ba = ai40ontology.createResource("resource-state-6");
//            
//            
//            
//            
//            
//            /***
//            **** Properties
//            ***/
//            
//            //defining domain properties
//            industrialAutomationDomain.addEstablishes(automationApplicationSemantics);
//            industrialAutomationDomain.addIsNormalizedBy(std_iec_62890);
//            industrialAutomationDomain.addIsNormalizedBy(std_iec_61131);
//            industrialAutomationDomain.addIsNormalizedBy(std_iec_62264);
//            industrialAutomationDomain.addIsNormalizedBy(std_iec_61512);
//            industrialAutomationDomain.addIsNormalizedBy(std_iec_61499);
//            
//            iotDomain.addEstablishes(automationApplicationSemantics);
//            iotDomain.addIsNormalizedBy(std_opcua);
//            
//            //defining the semantics properties
//            automationApplicationSemantics.addEnsures(semanticInteroperability);
//            automationApplicationSemantics.addFormalizes(applicationRepresentation);
//            
//            //defining the representation properties
//            applicationRepresentation.addDefines(distributedAutomationArchitecture);
//            applicationRepresentation.addDefines(iotArchitecture);
//            
//            applicationRepresentation.addDefines(plainTextLanguage);
//            applicationRepresentation.addDefines(jsonFormattedTextLanguage);
//            
//            applicationRepresentation.addDefines(requestResponseProtocol);
//            applicationRepresentation.addDefines(broadcastProtocol);
//            
//            //defining standard properties
//            std_iec_61131.addStandardizes(applicationRepresentation);
//            std_iec_61499.addStandardizes(applicationRepresentation);
//            std_iec_61512.addStandardizes(applicationRepresentation);
//            std_iec_62264.addStandardizes(applicationRepresentation);
//            std_iec_62890.addStandardizes(applicationRepresentation);
//            std_opcua.addStandardizes(applicationRepresentation);
//            
//            std_iec_61131.addHasDBpediaResource("http://dbpedia.org/page/IEC_61131");
//            std_iec_61131.addHasOfficialResource(new URI("https://webstore.iec.ch/publication/62427"));
//            
//            std_iec_61499.addHasDBpediaResource("http://dbpedia.org/page/IEC_61499");
//            std_iec_61499.addHasOfficialResource(new URI("https://webstore.iec.ch/publication/5506"));
//            
//            std_iec_61512.addHasDBpediaResource("http://dbpedia.org/page/ISA-88");
//            std_iec_61512.addHasOfficialResource(new URI("https://webstore.iec.ch/publication/5531"));
//            
//            std_iec_62264.addHasDBpediaResource("http://dbpedia.org/page/IEC_62264");
//            std_iec_62264.addHasOfficialResource(new URI("https://webstore.iec.ch/publication/6675"));
//            
//            std_iec_62890.addHasOfficialResource(new URI("https://www.iec.ch/dyn/www/f?p=103:38:15939379842517::::FSP_ORG_ID,FSP_APEX_PAGE,FSP_PROJECT_ID:1250,23,20929"));
//            
//            std_opcua.addHasDBpediaResource("http://live.dbpedia.org/page/OPC_Unified_Architecture");
//            std_opcua.addHasOfficialResource(new URI("https://opcfoundation.org/about/opc-technologies/opc-ua/"));
//
//            //defining the architecture properties
//            distributedAutomationArchitecture.addIsImplementedBy(applicationNetwork);
//            iotArchitecture.addIsImplementedBy(applicationNetwork);
//            
//            distributedAutomationArchitecture.addIsComposedBy(actorI);
//            distributedAutomationArchitecture.addIsComposedBy(actorII);
//            
//            iotArchitecture.addIsComposedBy(actorIII);
//            
//            //defining the product-asset properties
//            productAssetA.addRequires(processA);
//            productAssetB.addRequires(processC);
//            productAssetC.addRequires(processC);
//            productAssetSeparation.addRequires(processMetalSeparation);
//            
//            //defining the process_ properties
//            processA.addHasAssociatedModel(complexApplicationDDModel);
//            processB.addHasAssociatedModel(complexApplicationDDModel);
//            //processC.addHasAssociatedModel(briefApplicationDDModel);
//            processMetalSeparation.addHasAssociatedModel(metalSeparationDDModel);
//            
//            /**
//            *** defining the actor properties
//            **/
//            
//            //functions
//            deviceI.addExecutes(readMetalDetectorFunction);
//            deviceI.addExecutes(sendPieceDataFunction);
//            deviceI.addExecutes(saveDataFunction);
//            
//            deviceII.addExecutes(performSeparationFunction);
//            deviceII.addExecutes(requestPieceDataFunction);
//            
//            //info
//            deviceI.addGenerates(pieceProcessData);
//            deviceI.addGenerates(pieceMaterialData);
//            deviceI.addReceives(serialData);
//            deviceII.addReceives(pieceMaterialData);
//            
//            //characteristics
//            deviceI.addHasCharacteristic("Thing");
//            deviceI.addHasCharacteristic("SoftwareResource");
//            deviceII.addHasCharacteristic("Device");
//            
//            //description
//            deviceI.addHasDescription("I am a thing and I manage the data in the metal separation process");
//            deviceII.addHasDescription("I am a controller device and I perform the operation of the metal separation process");
//            
//            //languages and protocols
//            deviceI.addUnderstands(requestResponseProtocol);
//            deviceI.addUnderstands(jsonFormattedTextLanguage);
//            deviceI.addUnderstands(plainTextLanguage);
//            deviceII.addUnderstands(requestResponseProtocol);
//            deviceII.addUnderstands(jsonFormattedTextLanguage);
//            
//            //services
//            deviceI.addOffers(sendPieceDataService);
//            deviceI.addOffers(saveDataService);
//
//            
//            //defining the service properties
//            
//            sendPieceDataService.addHasDescription("This service sends the data of the scanned element with material, weight, and serial");
//            saveDataService.addHasDescription("This service saves the data of the finished process loop in the database");
//            
//            
//            //defining the discrete dynamics models properties
//            //complexApplicationDDModel.addHasModelElement(dsBriefModel);
//            
//            
//            metalSeparationDDModel.addHasModelElement(dsMetalSeparationModel);
//            //metalSeparationDDModel.addHasModelElement()
//            
//
//
//            //defining the data streams properties
//            //dsComplexModel.addContains();
//            
//            
//            dsMetalSeparationModel.addContains(metalDetector);
//            dsMetalSeparationModel.addContains(p1Detector);
//            dsMetalSeparationModel.addContains(p2Detector);
//            dsMetalSeparationModel.addContains(pfDetector);
//            dsMetalSeparationModel.addContains(weight);
//            dsMetalSeparationModel.addContains(pieceData);
//            dsMetalSeparationModel.addContains(serialNumber);
//            dsMetalSeparationModel.addContains(convBeltOut);
//            dsMetalSeparationModel.addContains(metallicGate);
//            dsMetalSeparationModel.addContains(nonMetallicGate);
//            dsMetalSeparationModel.addProvidesInformationToState(state0_ba);
//            dsMetalSeparationModel.addProvidesInformationToState(state1_ba);
//            dsMetalSeparationModel.addProvidesInformationToState(state2_ba);
//            dsMetalSeparationModel.addProvidesInformationToState(state3_ba);
//            dsMetalSeparationModel.addProvidesInformationToState(state4_ba);
//            dsMetalSeparationModel.addProvidesInformationToState(state5_ba);
//            dsMetalSeparationModel.addProvidesInformationToState(state6_ba);
//            
//            //defining the states (complex application) properties
//            
//            //defining the states (brief application) properties
//            state0_ba.addInitializesSequence(sequence_ba);
//            state0_ba.addHasDescription("Initial state (state 0) of metal separation model");
//            state0_ba.addHasNextState(state1_ba);
//            state0_ba.addHasNumberOfAssignment(1);
//            state0_ba.addHasPreviousState(state6_ba);
//            state0_ba.addHasNumberOfTasks(0);
//            state0_ba.addHasResource(state0_resource_ba);
//            state0_ba.addIsConnectedToTransition(transition1_ba);
//            state0_ba.addReceivesDataStream(dsMetalSeparationModel);
//            
//            
//            state1_ba.addHasDescription("State 1 of metal separation model");
//            state1_ba.addHasNextState(state2_ba);
//            state1_ba.addHasNumberOfAssignment(2);
//            state1_ba.addHasPreviousState(state0_ba);
//            state1_ba.addHasNumberOfTasks(0);
//            state1_ba.addHasResource(state1_resource_ba);
//            state1_ba.addIsConnectedToTransition(transition2_ba);
//            state1_ba.addReceivesDataStream(dsMetalSeparationModel);
//            
//            state2_ba.addHasDescription("State 2 of metal separation model");
//            state2_ba.addHasNextState(state3_ba);
//            state2_ba.addHasNumberOfAssignment(3);
//            state2_ba.addHasPreviousState(state1_ba);
//            state2_ba.addHasNumberOfTasks(1);
//            state2_ba.addHasResource(state2_resource_ba);
//            state2_ba.addIsConnectedToTransition(transition3_ba);
//            state2_ba.addReceivesDataStream(dsMetalSeparationModel);
//            
//            state3_ba.addHasDescription("State 3 of metal separation model");
//            state3_ba.addHasNextState(state4_ba);
//            state3_ba.addHasNumberOfAssignment(4);
//            state3_ba.addHasPreviousState(state2_ba);
//            state3_ba.addHasNumberOfTasks(0);
//            state3_ba.addHasResource(state3_resource_ba);
//            state3_ba.addIsConnectedToTransition(transition4_ba);
//            state3_ba.addReceivesDataStream(dsMetalSeparationModel);
//            
//            state4_ba.addHasDescription("State 4 of metal separation model");
//            state4_ba.addHasNextState(state5_ba);
//            state4_ba.addHasNumberOfAssignment(5);
//            state4_ba.addHasPreviousState(state3_ba);
//            state4_ba.addHasNumberOfTasks(3);
//            state4_ba.addHasResource(state4_resource_ba);
//            state4_ba.addIsConnectedToTransition(transition5_ba);
//            state4_ba.addReceivesDataStream(dsMetalSeparationModel);
//            
//            state5_ba.addHasDescription("State 5 of metal separation model");
//            state5_ba.addHasNextState(state6_ba);
//            state5_ba.addHasNumberOfAssignment(6);
//            state5_ba.addHasPreviousState(state4_ba);
//            state5_ba.addHasNumberOfTasks(0);
//            state5_ba.addHasResource(state5_resource_ba);
//            state5_ba.addIsConnectedToTransition(transition6_ba);
//            state5_ba.addReceivesDataStream(dsMetalSeparationModel);
//            
//            state6_ba.addHasDescription("Final state (state 6) of metal separation model");
//            state6_ba.addHasNextState(state0_ba);
//            state6_ba.addHasNumberOfAssignment(7);
//            state6_ba.addHasPreviousState(state5_ba);
//            state6_ba.addHasNumberOfTasks(1);
//            state6_ba.addHasResource(state6_resource_ba);
//            state6_ba.addIsConnectedToTransition(transition7_ba);
//            state6_ba.addReceivesDataStream(dsMetalSeparationModel);
//            
//            //defining the transitions (complex application) properties
//            
//            
//            
//            //defining the transitions (brief application) properties
//            transition1_ba.addHasDescription("Transition 1 between state 0 and state 1 of the metal separation model");
//            transition1_ba.addIsConnectedToState(state1_ba);
//            
//            transition2_ba.addHasDescription("Transition 2 between state 1 and state 2 of the metal separation model");
//            transition2_ba.addIsConnectedToState(state2_ba);
//            
//            transition3_ba.addHasDescription("Transition 3 between state 2 and state 3 of the metal separation model");
//            transition3_ba.addIsConnectedToState(state3_ba);
//            
//            transition4_ba.addHasDescription("Transition 4 between state 3 and state 4 of the metal separation model");
//            transition4_ba.addIsConnectedToState(state4_ba);
//            
//            transition5_ba.addHasDescription("Transition 5 between state 4 and state 5 of the metal separation model");
//            transition5_ba.addIsConnectedToState(state5_ba);
//            
//            transition6_ba.addHasDescription("Transition 6 between state 5 and state 6 of the metal separation model");
//            transition6_ba.addIsConnectedToState(state6_ba);
//            
//            transition7_ba.addHasDescription("Transition 7 between state 6 and state 0 of the metal separation model");
//            transition7_ba.addIsConnectedToState(state0_ba);
//            
//            
//            
//            //defining the arcs (complex application) properties
//            
//            //defining the set elements (complex application) properties
//            
//            //defining the set elements (brief application) properties
//            sequence_ba.addHasNumberOfStates(7);
//            sequence_ba.addHasNumberOfTransitions(7);
//            sequence_ba.addIsParentSetElement(true);
//            
//            
//            
//            //defining the variables (complex application) properties
//            
//            //defining the variables (brief application) properties
//            convBeltOut.addActsOver(conveyorBelt);
//            convBeltOut.addHasDataType("boolean");
//            convBeltOut.addHasDescription("Motor activation control for the conveyor belt in the metal separation process");
//            convBeltOut.addHasValue(String.valueOf(false));
//            convBeltOut.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            metallicGate.addActsOver(conveyorBelt);
//            metallicGate.addHasDataType("boolean");
//            metallicGate.addHasDescription("Gate activation for the metal elements in the metal separation process");
//            metallicGate.addHasValue(String.valueOf(false));
//            metallicGate.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            nonMetallicGate.addActsOver(conveyorBelt);
//            nonMetallicGate.addHasDataType("boolean");
//            nonMetallicGate.addHasDescription("Gate activation for the non-metallic elements in the metal separation process");
//            nonMetallicGate.addHasValue(String.valueOf(false));
//            nonMetallicGate.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            metalDetector.addReadsAbout(conveyorBelt);
//            metalDetector.addHasDataType("boolean");
//            metalDetector.addHasDescription("Metal detector for pieces in the metal separation model");
//            metalDetector.addHasValue(String.valueOf(false));
//            metalDetector.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            p1Detector.addReadsAbout(conveyorBelt);
//            p1Detector.addHasDataType("boolean");
//            p1Detector.addHasDescription("Position 1 reached detector in the metal separation process");
//            p1Detector.addHasValue(String.valueOf(false));
//            p1Detector.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            p2Detector.addReadsAbout(conveyorBelt);
//            p2Detector.addHasDataType("boolean");
//            p2Detector.addHasDescription("Position 2 reached detector in the metal separation process");
//            p2Detector.addHasValue(String.valueOf(false));
//            p2Detector.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            pfDetector.addReadsAbout(conveyorBelt);
//            pfDetector.addHasDataType("boolean");
//            pfDetector.addHasDescription("Final position reached detector in the metal separation process");
//            pfDetector.addHasValue(String.valueOf(false));
//            pfDetector.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            weight.addReadsAbout(conveyorBelt);
//            weight.addHasDataType("double");
//            weight.addHasDescription("Weight of piece sensor in the metal separation process");
//            weight.addHasValue(String.valueOf(0.0));
//            weight.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            serialNumber.addHasDataType("String");
//            serialNumber.addHasDescription("Serial data input in the metal separation process");
//            serialNumber.addHasValue("");
//            serialNumber.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            pieceData.addHasDataType("String");
//            pieceData.addHasDescription("Piece output data in the metal separation process");
//            pieceData.addHasValue("");
//            pieceData.addIsContextVariableOf(dsMetalSeparationModel);
//            
//            
//            //defining the functions (complex application) properties
//            
//            //defining the functions (brief application) properties
//            readMetalDetectorFunction.addHasDescription("reads the metal detector state");
//            readMetalDetectorFunction.addHasNumberOfParameters(0);
//            readMetalDetectorFunction.addHasReturnDataType("boolean");
//            readMetalDetectorFunction.addHasReturnValue(String.valueOf(false));
//            readMetalDetectorFunction.addIsExecutedBy(deviceI);
//            readMetalDetectorFunction.addReturnsResult(metalDetector);
//            
//            requestPieceDataFunction.addHasDescription("requests the piece data from the device I");
//            requestPieceDataFunction.addHasNumberOfParameters(0);
//            requestPieceDataFunction.addHasReturnDataType("String");
//            requestPieceDataFunction.addHasReturnValue("");
//            requestPieceDataFunction.addIsExecutedBy(deviceII);
//            requestPieceDataFunction.addReturnsResult(pieceData);
//            
//            sendPieceDataFunction.addHasDescription("sends the piece data to the device II");
//            sendPieceDataFunction.addHasNumberOfParameters(0);
//            sendPieceDataFunction.addHasReturnDataType("void");
//            sendPieceDataFunction.addHasReturnValue("");
//            sendPieceDataFunction.addIsExecutedBy(deviceI);
//
//            performSeparationFunction.addHasDescription("performs the metal separation operation");
//            performSeparationFunction.addHasNumberOfParameters(1);
//            performSeparationFunction.addReceivesParameter(pieceData);
//            performSeparationFunction.addHasReturnDataType("void");
//            performSeparationFunction.addHasReturnValue("");
//            performSeparationFunction.addIsExecutedBy(deviceII);
//
//            saveDataFunction.addHasDescription("saves the process data");
//            saveDataFunction.addHasNumberOfParameters(0);
//            saveDataFunction.addHasReturnDataType("boolean");
//            saveDataFunction.addHasReturnValue(String.valueOf(false));
//            saveDataFunction.addIsExecutedBy(deviceI);
//            
//            
//            //defining entities properties
//            conveyorBelt.addHasDescription("Conveyor belt of the metal separation process");
//            
//            //defining the resource properties
//            state0_resource_ba.addHasDataType("boolean");
//            state0_resource_ba.addHasValue(String.valueOf(true));
//            
//            state1_resource_ba.addHasDataType("boolean");
//            state1_resource_ba.addHasValue(String.valueOf(false));
//            
//            state2_resource_ba.addHasDataType("boolean");
//            state2_resource_ba.addHasValue(String.valueOf(false));
//            
//            state3_resource_ba.addHasDataType("boolean");
//            state3_resource_ba.addHasValue(String.valueOf(false));
//            
//            state4_resource_ba.addHasDataType("boolean");
//            state4_resource_ba.addHasValue(String.valueOf(false));
//            
//            state5_resource_ba.addHasDataType("boolean");
//            state5_resource_ba.addHasValue(String.valueOf(false));
//            
//            state6_resource_ba.addHasDataType("boolean");
//            state6_resource_ba.addHasValue(String.valueOf(false));
//            
//            
//            
//            //defining service properties
//            serviceI.addHasDescription("Testing service 1");
//            serviceII.addHasDescription("Testing service 2");
//            serviceIII.addHasDescription("Testing service 3");
//            
//            //defining actor properties
//            actorI.addOffers(serviceI);
//            actorII.addOffers(serviceII);
//            actorIII.addOffers(serviceIII);
//            System.out.println(actorI.getOffers());
//            System.out.println(actorII.getOffers());
//            System.out.println(serviceI.getHasDescription());
//            
//            System.out.println(ai40ontology.getOwlOntology());
//
//            // Create SQWRL query engine using the SWRLAPI
//            SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);
//
//            // Create and execute a SQWRL query using the SWRLAPI
//            SQWRLResult result = queryEngine.runSQWRLQuery("q1", "swrlb:add(?x, 2, 4) -> sqwrl:select(?x)");
//            SQWRLResult result2 = queryEngine.runSQWRLQuery("q2", "swrlb:add(?y, 2, 7) -> sqwrl:select(?y)");
//            // Process the SQWRL result
//            if (result.next()) {
//                System.out.println("x: " + result.getLiteral("x").getInteger());
//                
//            }
//            if (result2.next()) {
//                System.out.println("y: " + result2.getLiteral("y").getInteger());
//                
//            }
//            
//
//        } catch (OWLOntologyCreationException e) {
//            System.err.println("Error creating OWL ontology: " + e.getMessage());
//            System.exit(-1);
//        } catch (SWRLParseException e) {
//            System.err.println("Error parsing SWRL rule or SQWRL query: " + e.getMessage());
//            System.exit(-1);
//        } catch (SQWRLException e) {
//            System.err.println("Error running SWRL rule or SQWRL query: " + e.getMessage());
//            System.exit(-1);
//        } catch (RuntimeException e) {
//            System.err.println("Error starting application: " + e.getMessage());
//            System.exit(-1);
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(AutomationI40OntologyExample.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    private static void Usage() {
        System.err.println("Usage: " + AutomationI40OntologyExample.class.getName() + " [ <owlFileName> ]");
        System.exit(1);
    }
    
    
    
    
}
