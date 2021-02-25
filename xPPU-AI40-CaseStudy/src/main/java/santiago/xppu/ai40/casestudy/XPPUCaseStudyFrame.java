/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santiago.xppu.ai40.casestudy;

import aI40ontology.Actor;
import aI40ontology.AdministrationShell;
import aI40ontology.Agent;
import aI40ontology.Asset;
import aI40ontology.DataInput;
import aI40ontology.DigitalInput;
import aI40ontology.DigitalOutput;
import aI40ontology.DynamicsModel;
import aI40ontology.ExecutionScope;
import aI40ontology.InitialState;
import aI40ontology.ObjectO;
import aI40ontology.ProcessO;
import aI40ontology.Service;
import aI40ontology.State;
import aI40ontology.aI40ontology;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.apache.commons.cli.TypeHandler;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.swrlapi.core.SWRLRuleEngine;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

/**
 *
 * @author sagga
 */
public class XPPUCaseStudyFrame extends javax.swing.JFrame {

    //Ontology instances
    aI40ontology ai40ontology;
    //Storage process Scenario 11
    State standby_state;
    InitialState init_state;
    State state1_wp_conv_ps;
    State state2_conv_fwd;
    State state3_wp_light_ind_1;
    State state4_wp_push_cyl_1;
    State state4_1_end_white_wp;
    State state5_wp_light_ind_2;
    State state6_wp_push_cyl_2;
    State state6_1_end_metallic_wp;
    State state7_1_end_black_wp;
    State state8_conv_stop;
    State state9_conv_full;
    State state9_1_conv_not_full;
    State stop_state;
    Agent convBeltAgent;
    Agent craneAgent;
    Agent stampAgent;
    Actor operator;
    Service storageService;
    Service movingService;
    Service stampingService;
    Service bufferingService;
    ObjectO convBelt;
    ObjectO crane;
    ObjectO stamp;
    ObjectO xPPU;
    Asset wpStorage;
    ProcessO xppuWPSeparation;
    DynamicsModel xppuWPSeparationModel;
    DigitalOutput convBeltForward;
    DigitalOutput valvePushingCylinderRamp1;
    DigitalOutput valvePushingCylinderRamp2;
    DigitalInput startButton;
    DigitalInput stopButton;
    DigitalInput presenceSensorStart;
    DigitalInput presenceSensorEnd;
    DigitalInput lightWPRamp1;
    DigitalInput lightWPRamp2;
    DigitalInput inductiveSensorRamp1;
    DigitalInput inductiveSensorRamp2;
    DigitalInput reedSwitchRamp1Extended;
    DigitalInput reedSwitchRamp1Retracted;
    DigitalInput reedSwitchRamp2Extended;
    DigitalInput reedSwitchRamp2Retracted;
    DataInput currentFillingRamp1;
    DataInput currentFillingRamp2;
    DataInput currentFillingRampEnd;
    AdministrationShell convBeltShell;
    AdministrationShell craneShell;
    AdministrationShell stampShell;
    ExecutionScope esxPPU;
    ExecutionScope esConvBelt;
    ExecutionScope esCrane;
    ExecutionScope esStamp;

    //SWRLAPI
    SWRLRuleEngine swrlEngine;
    SQWRLQueryEngine queryEngine;

    //Inference Log Messages
    String logMessage = "";
    InferencesFrame infFrame = new InferencesFrame();
    
    // Inference thread
    Thread inferenceThread;
    Timer inferenceTimer = new Timer();

    /**
     * Creates new form XPPUCaseStudyFrame
     */
    public XPPUCaseStudyFrame() {
        initComponents();
        try {

            // Create an OWL ontology using the OWLAPI
            OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
            OWLOntology ontology = ontologyManager.loadOntologyFromOntologyDocument(new File("Automation-I4.0-Min-Ontology.owl"));
            ontologyManager.getOntologyFormat(ontology).asPrefixOWLOntologyFormat().setDefaultPrefix("http://www.semanticweb.org/santiago/Automation-I4.0-Min-Ontology" + "#");
            System.out.println("Classes:");
            System.out.println(ontology.getAxioms());
            
            
            
    

            /**
             * *
             **** Instantiating the ontology *
             */
            ai40ontology = new aI40ontology(ontology);

            /**
             * *
             **** Instances / Individuals *
             */
            //instantiating the assets
            wpStorage = ai40ontology.createAsset("Work Piece Separation Asset");

            //instantiating the processes
            xppuWPSeparation = ai40ontology.createProcessO("xPPU Work Piece separation by White, Black, and Metallic pieces");

            //instantiating the agents/actors
            convBeltAgent = ai40ontology.createAgent("Agent Conveyor Belt");
            craneAgent = ai40ontology.createAgent("Agent Crane");
            stampAgent = ai40ontology.createAgent("Agent Stamp");
            operator = ai40ontology.createActor("Human Operator");

            //States
            standby_state = ai40ontology.createState("Stand by state - before working");
            init_state = ai40ontology.createInitialState("Initial State WP Process");
            state1_wp_conv_ps = ai40ontology.createState("State 1: Work Piece in ConvBelt, Presence Sensor activated");
            state2_conv_fwd = ai40ontology.createState("State 2: ConvBelt moving forward");
            state3_wp_light_ind_1 = ai40ontology.createState("State 3: WP reaches light and inductive sensors ramp 1");
            state4_wp_push_cyl_1 = ai40ontology.createState("State 4: WP reaches pushing cylinder ramp 1");
            state4_1_end_white_wp = ai40ontology.createState("State 4_1: WP is white and pushed into ramp 1 for storage");
            state5_wp_light_ind_2 = ai40ontology.createState("State 5: WP reaches light and inductive sensors ramp 2");
            state6_wp_push_cyl_2 = ai40ontology.createState("State 6: WP reaches pushing cylinder ramp 2");
            state6_1_end_metallic_wp = ai40ontology.createState("State 6_1: WP is metallic and pushed into ramp 2 for storage");
            state7_1_end_black_wp = ai40ontology.createState("State 7_1: WP is black and reaches end ramp for storage");
            state8_conv_stop = ai40ontology.createState("State 8: ConvBelt stopping");
            state9_conv_full = ai40ontology.createState("State 9: ConvBelt is full of capacity");
            state9_1_conv_not_full = ai40ontology.createState("State 9_1: ConvBelt is not full of capacity");
            stop_state = ai40ontology.createState("Stop state WP process");
            
            //Services
            storageService = ai40ontology.createService("Storaging Service");
            movingService = ai40ontology.createService("Moving WPs Service");
            stampingService = ai40ontology.createService("Stamping WPs Service");
            bufferingService = ai40ontology.createService("Buffering Service");
            
            //Objects
            convBelt = ai40ontology.createObjectO("Conveyor Belt");
            crane = ai40ontology.createObjectO("Crane");
            stamp = ai40ontology.createObjectO("Stamp");
            xPPU = ai40ontology.createObjectO("xPPU");
            
            //Dynamics Model
            xppuWPSeparationModel = ai40ontology.createDynamicsModel("xPPU separation model (Conveyor Belt sequence)");
            
            //Inputs / Outputs
            convBeltForward = ai40ontology.createDigitalOutput("Conveyor Belt Forward Output");
            valvePushingCylinderRamp1 = ai40ontology.createDigitalOutput("Pushing Cylinder Ramp 1 Valve Extension Output");
            valvePushingCylinderRamp2 = ai40ontology.createDigitalOutput("Pushing Cylinder Ramp 2 Valve Extension Output");
            startButton = ai40ontology.createDigitalInput("Start Button");
            stopButton = ai40ontology.createDigitalInput("Stop Button");
            presenceSensorStart = ai40ontology.createDigitalInput("Presence Sensor Start Position");
            presenceSensorEnd = ai40ontology.createDigitalInput("Presence Sensor End Position");
            lightWPRamp1 = ai40ontology.createDigitalInput("Light Sensor Ramp 1");
            lightWPRamp2 = ai40ontology.createDigitalInput("Light Sensor Ramp 2");
            inductiveSensorRamp1 = ai40ontology.createDigitalInput("Inductive Sensor Ramp 1");
            inductiveSensorRamp2 = ai40ontology.createDigitalInput("Inductive Sensor Ramp 2");
            reedSwitchRamp1Extended = ai40ontology.createDigitalInput("Reed Switch Pushing Cylinder Ramp 1 Extended");
            reedSwitchRamp1Retracted = ai40ontology.createDigitalInput("Reed Switch Pushing Cylinder Ramp 1 Retracted");
            reedSwitchRamp2Extended = ai40ontology.createDigitalInput("Reed Switch Pushing Cylinder Ramp 2 Extended");
            reedSwitchRamp2Retracted = ai40ontology.createDigitalInput("Reed Switch Pushing Cylinder Ramp 2 Retracted");
            currentFillingRamp1 = ai40ontology.createDataInput("Current Filling Ramp 1");
            currentFillingRamp2 = ai40ontology.createDataInput("Current Filling Ramp 2");
            currentFillingRampEnd = ai40ontology.createDataInput("Current Filling Ramp End");
            
            //Digital Twins
            convBeltShell = ai40ontology.createAdministrationShell("Digital Twin Conveyor Belt xPPU");
            craneShell = ai40ontology.createAdministrationShell("Digital Twin Crane xPPU");
            stampShell = ai40ontology.createAdministrationShell("Digital Twin Stamp xPPU");
            
            //Execution scopes
            esxPPU = ai40ontology.createExecutionScope("Execution Scope xPPU - General");
            esConvBelt = ai40ontology.createExecutionScope("Execution Scope Conveyor Belt - xPPU - Local");
            esCrane = ai40ontology.createExecutionScope("Execution Scope Crane xPPU - Local");
            esStamp = ai40ontology.createExecutionScope("Execution Scope Stamp xPPU - Local");
            
            /**
             * *
             **** Properties *
             */
            
            //Assets
            wpStorage.addHasDescription("Task of separating work pieces into different ramps for storaging according to color and material");
            wpStorage.addIsCarriedOutIn(xppuWPSeparation);
            
            //Processes
            xppuWPSeparation.addHasAssociatedModel(xppuWPSeparationModel);
            xppuWPSeparation.addHasDescription("Conveyor behavior in Scenario 11 - xPPU AIS TUM");
            
            //agents/actors
            convBeltAgent.addOffers(storageService);
            convBeltAgent.addHasFeature("Reactive");
            convBeltAgent.addHasFeature("Device");
            convBeltAgent.addHasIdentifier(1);
            convBeltAgent.addHasDescription("Agent which controls the conveyor belt of the xPPU");
            
            craneAgent.addOffers(movingService);
            craneAgent.addHasFeature("Reactive");
            craneAgent.addHasFeature("Device");
            craneAgent.addHasIdentifier(2);
            craneAgent.addHasDescription("Agent which controls the crane of the xPPU");
            
            stampAgent.addOffers(stampingService);
            stampAgent.addOffers(bufferingService);
            stampAgent.addHasFeature("Reactive");
            stampAgent.addHasFeature("Device");
            stampAgent.addHasIdentifier(3);
            stampAgent.addHasDescription("Agent which controls the stamp of the xPPU");
            
            operator.addHasDescription("Human operator of xPPU plant");
            operator.addInteractsWith(convBeltAgent);
            
            
            //States
            standby_state.addHasNextState(init_state);
            standby_state.addHasPreviousState(stop_state);
            standby_state.addIsActive(true);
            
            init_state.addHasNextState(state1_wp_conv_ps);
            init_state.addHasPreviousState(state9_1_conv_not_full);
            init_state.addHasPreviousState(state9_conv_full);
            init_state.addIsActive(false);
            
            state1_wp_conv_ps.addHasNextState(state2_conv_fwd);
            state1_wp_conv_ps.addHasPreviousState(init_state);
            state1_wp_conv_ps.addIsActive(false);
            
            state2_conv_fwd.addHasNextState(state3_wp_light_ind_1);
            state2_conv_fwd.addHasPreviousState(state1_wp_conv_ps);
            state2_conv_fwd.addIsActive(false);
            
            state3_wp_light_ind_1.addHasNextState(state4_wp_push_cyl_1);
            
            state3_wp_light_ind_1.addHasPreviousState(state2_conv_fwd);
            state3_wp_light_ind_1.addIsActive(false);
            
            state4_wp_push_cyl_1.addHasNextState(state5_wp_light_ind_2);
            state4_wp_push_cyl_1.addHasNextState(state4_1_end_white_wp);
            state4_wp_push_cyl_1.addHasPreviousState(state3_wp_light_ind_1);
            state4_wp_push_cyl_1.addIsActive(false);
            
            state4_1_end_white_wp.addHasNextState(state8_conv_stop);
            state4_1_end_white_wp.addHasPreviousState(state4_wp_push_cyl_1);
            state4_1_end_white_wp.addIsActive(false);
            
            state5_wp_light_ind_2.addHasNextState(state6_wp_push_cyl_2);
            state5_wp_light_ind_2.addHasPreviousState(state3_wp_light_ind_1);
            state5_wp_light_ind_2.addIsActive(false);
            
            state6_wp_push_cyl_2.addHasNextState(state6_1_end_metallic_wp);
            state6_wp_push_cyl_2.addHasNextState(state7_1_end_black_wp);
            state6_wp_push_cyl_2.addHasPreviousState(state5_wp_light_ind_2);
            state6_wp_push_cyl_2.addIsActive(false);
            
            state6_1_end_metallic_wp.addHasNextState(state8_conv_stop);
            state6_1_end_metallic_wp.addHasPreviousState(state6_wp_push_cyl_2);
            state6_1_end_metallic_wp.addIsActive(false);
            
            state7_1_end_black_wp.addHasNextState(state8_conv_stop);
            state7_1_end_black_wp.addHasPreviousState(state6_wp_push_cyl_2);
            state7_1_end_black_wp.addIsActive(false);
            
            state8_conv_stop.addHasNextState(state9_1_conv_not_full);
            state8_conv_stop.addHasNextState(state9_conv_full);
            state8_conv_stop.addHasPreviousState(state4_1_end_white_wp);
            state8_conv_stop.addHasPreviousState(state6_1_end_metallic_wp);
            state8_conv_stop.addHasPreviousState(state7_1_end_black_wp);
            state8_conv_stop.addIsActive(false);
            
            state9_conv_full.addHasNextState(init_state);
            state9_conv_full.addHasPreviousState(state8_conv_stop);
            state9_conv_full.addIsActive(false);
            
            state9_1_conv_not_full.addHasNextState(init_state);
            state9_1_conv_not_full.addHasPreviousState(state8_conv_stop);
            state9_1_conv_not_full.addIsActive(false);
            
            stop_state.addHasNextState(standby_state);
            stop_state.addHasPreviousState(init_state);
            stop_state.addHasPreviousState(state1_wp_conv_ps);
            stop_state.addHasPreviousState(state2_conv_fwd);
            stop_state.addHasPreviousState(state3_wp_light_ind_1);
            stop_state.addHasPreviousState(state4_wp_push_cyl_1);
            stop_state.addHasPreviousState(state4_1_end_white_wp);
            stop_state.addHasPreviousState(state5_wp_light_ind_2);
            stop_state.addHasPreviousState(state6_wp_push_cyl_2);
            stop_state.addHasPreviousState(state6_1_end_metallic_wp);
            stop_state.addHasPreviousState(state7_1_end_black_wp);
            stop_state.addHasPreviousState(state8_conv_stop);
            stop_state.addHasPreviousState(state9_conv_full);
            stop_state.addHasPreviousState(state9_1_conv_not_full);
            stop_state.addIsActive(false);
            
            //Services
            storageService.addHasDescription("This service separates work pieces by white, black, and metallic and storages them into 3 different ramps");
            storageService.addHasURI(new URI("conveyoragent/storage"));
            storageService.addIsExecutedBy(convBeltAgent);
            
            movingService.addHasDescription("This service moves the work pieces from the stamp and stack to the conveyor belt or viceversa");
            movingService.addHasURI(new URI("craneagent/pickandplace"));
            movingService.addIsExecutedBy(craneAgent);
            
            stampingService.addHasDescription("This service stamps metallic and white work pieces at the stamp");
            stampingService.addHasURI(new URI("stampagent/stamping"));
            stampingService.addIsExecutedBy(stampAgent);
            
            bufferingService.addHasDescription("This service serves for buffering work pieces");
            bufferingService.addHasURI(new URI("stampagent/buffering"));
            bufferingService.addIsExecutedBy(stampAgent);
            
            //Objects
            convBelt.addHasDescription("Conveyor Belt (Physical resource) of the xPPU");
            crane.addHasDescription("Crane (Physical resource) of the xPPU");
            stamp.addHasDescription("Stamp (Physical resource) of the xPPU");
            xPPU.addHasDescription("xPPU Unit (Physical resource)");
            
            //Dynamics Model
            xppuWPSeparationModel.addHasModelElement(standby_state);
            xppuWPSeparationModel.addHasModelElement(init_state);
            xppuWPSeparationModel.addHasModelElement(state1_wp_conv_ps);
            xppuWPSeparationModel.addHasModelElement(state2_conv_fwd);
            xppuWPSeparationModel.addHasModelElement(state3_wp_light_ind_1);
            xppuWPSeparationModel.addHasModelElement(state4_wp_push_cyl_1);
            xppuWPSeparationModel.addHasModelElement(state4_1_end_white_wp);
            xppuWPSeparationModel.addHasModelElement(state5_wp_light_ind_2);
            xppuWPSeparationModel.addHasModelElement(state6_wp_push_cyl_2);
            xppuWPSeparationModel.addHasModelElement(state6_1_end_metallic_wp);
            xppuWPSeparationModel.addHasModelElement(state7_1_end_black_wp);
            xppuWPSeparationModel.addHasModelElement(state8_conv_stop);
            xppuWPSeparationModel.addHasModelElement(state9_conv_full);
            xppuWPSeparationModel.addHasModelElement(state9_1_conv_not_full);
            xppuWPSeparationModel.addHasModelElement(stop_state);
            
            xppuWPSeparationModel.addHasModelElement(convBeltForward);
            xppuWPSeparationModel.addHasModelElement(valvePushingCylinderRamp1);
            xppuWPSeparationModel.addHasModelElement(valvePushingCylinderRamp2);
            xppuWPSeparationModel.addHasModelElement(startButton);
            xppuWPSeparationModel.addHasModelElement(stopButton);
            xppuWPSeparationModel.addHasModelElement(presenceSensorStart);
            xppuWPSeparationModel.addHasModelElement(presenceSensorEnd);
            xppuWPSeparationModel.addHasModelElement(lightWPRamp1);
            xppuWPSeparationModel.addHasModelElement(lightWPRamp2);
            xppuWPSeparationModel.addHasModelElement(inductiveSensorRamp1);
            xppuWPSeparationModel.addHasModelElement(inductiveSensorRamp2);
            xppuWPSeparationModel.addHasModelElement(reedSwitchRamp1Extended);
            xppuWPSeparationModel.addHasModelElement(reedSwitchRamp1Retracted);
            xppuWPSeparationModel.addHasModelElement(reedSwitchRamp2Extended);
            xppuWPSeparationModel.addHasModelElement(reedSwitchRamp2Retracted);
            xppuWPSeparationModel.addHasModelElement(currentFillingRamp1);
            xppuWPSeparationModel.addHasModelElement(currentFillingRamp2);
            xppuWPSeparationModel.addHasModelElement(currentFillingRampEnd);
            
            xppuWPSeparationModel.addHasModelElement(storageService);
            xppuWPSeparationModel.addHasModelElement(movingService);
            xppuWPSeparationModel.addHasModelElement(stampingService);
            xppuWPSeparationModel.addHasModelElement(bufferingService);
            
            
            //Inputs / Outputs
            convBeltForward.addActsOver(convBelt);
            convBeltForward.addHasValue(false);
            
            valvePushingCylinderRamp1.addActsOver(convBelt);
            valvePushingCylinderRamp1.addHasValue(false);
            
            valvePushingCylinderRamp2.addActsOver(convBelt);
            valvePushingCylinderRamp2.addHasValue(false);
            
            startButton.addMonitors(xPPU);
            startButton.addHasValue(false);
            
            stopButton.addMonitors(xPPU);
            stopButton.addHasValue(false);
            
            presenceSensorStart.addMonitors(convBelt);
            presenceSensorStart.addHasValue(false);
            
            presenceSensorEnd.addMonitors(convBelt);
            presenceSensorEnd.addHasValue(false);
            
            lightWPRamp1.addMonitors(convBelt);
            lightWPRamp1.addHasValue(false);
            
            lightWPRamp2.addMonitors(convBelt);
            lightWPRamp2.addHasValue(false);
            
            inductiveSensorRamp1.addMonitors(convBelt);
            inductiveSensorRamp1.addHasValue(false);
            
            inductiveSensorRamp2.addMonitors(convBelt);
            inductiveSensorRamp2.addHasValue(false);
            
            reedSwitchRamp1Extended.addMonitors(convBelt);
            reedSwitchRamp1Extended.addHasValue(false);
            
            reedSwitchRamp1Retracted.addMonitors(convBelt);
            reedSwitchRamp1Retracted.addHasValue(false);
            
            reedSwitchRamp2Extended.addMonitors(convBelt);
            reedSwitchRamp2Extended.addHasValue(false);
            
            reedSwitchRamp2Retracted.addMonitors(convBelt);
            reedSwitchRamp2Retracted.addHasValue(false);
            
            currentFillingRamp1.addMonitors(convBelt);
            currentFillingRamp1.addHasValue(false);
            
            currentFillingRamp2.addMonitors(convBelt);
            currentFillingRamp2.addHasValue(false);
            
            currentFillingRampEnd.addMonitors(convBelt);
            currentFillingRampEnd.addHasValue(false);
            
            //Digital Twins
            convBeltShell.addHasDescription("Virtual representation of the 'Conveyor Belt' resource of the xPPU");
            convBeltShell.addHasFile("xppu_conveyor_belt_example.aasx");
            
            craneShell.addHasDescription("Virtual representation of the 'Crane' resource of the xPPU");
            craneShell.addHasFile("xppu_crane_example.aasx");
            
            stampShell.addHasDescription("Virtual representation of the 'Stamp' resource of the xPPU");
            stampShell.addHasFile("xppu_stamp_example.aasx");
            
            //Execution scopes
            esConvBelt.addContains(convBeltForward);
            esConvBelt.addContains(valvePushingCylinderRamp1);
            esConvBelt.addContains(valvePushingCylinderRamp2);
            esConvBelt.addContains(presenceSensorStart);
            esConvBelt.addContains(presenceSensorEnd);
            esConvBelt.addContains(lightWPRamp1);
            esConvBelt.addContains(lightWPRamp2);
            esConvBelt.addContains(inductiveSensorRamp1);
            esConvBelt.addContains(inductiveSensorRamp2);
            esConvBelt.addContains(reedSwitchRamp1Extended);
            esConvBelt.addContains(reedSwitchRamp1Retracted);
            esConvBelt.addContains(reedSwitchRamp2Extended);
            esConvBelt.addContains(reedSwitchRamp2Retracted);
            esConvBelt.addContains(currentFillingRamp1);
            esConvBelt.addContains(currentFillingRamp2);
            esConvBelt.addContains(currentFillingRampEnd);
            
            esxPPU.addContains(convBeltForward);
            esxPPU.addContains(valvePushingCylinderRamp1);
            esxPPU.addContains(valvePushingCylinderRamp2);
            esxPPU.addContains(startButton);
            esxPPU.addContains(stopButton);
            esxPPU.addContains(presenceSensorStart);
            esxPPU.addContains(presenceSensorEnd);
            esxPPU.addContains(lightWPRamp1);
            esxPPU.addContains(lightWPRamp2);
            esxPPU.addContains(inductiveSensorRamp1);
            esxPPU.addContains(inductiveSensorRamp2);
            esxPPU.addContains(reedSwitchRamp1Extended);
            esxPPU.addContains(reedSwitchRamp1Retracted);
            esxPPU.addContains(reedSwitchRamp2Extended);
            esxPPU.addContains(reedSwitchRamp2Retracted);
            esxPPU.addContains(currentFillingRamp1);
            esxPPU.addContains(currentFillingRamp2);
            esxPPU.addContains(currentFillingRampEnd);
            
            
            queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);
            swrlEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);
            
            
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
            
            
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(XPPUCaseStudyFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(XPPUCaseStudyFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automation Ontology I4.0 - xPPU");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Automation I4.0 Ontology Case Study- xPPU");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setText("Monitoring");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setText("xPPU Case Study 11");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, -1, 40));

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

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("https://mediatum.ub.tum.de/node?id=1468863");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 610, -1, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("© Santiago Gil - Automation I4.0 Ontology Project");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 630, -1, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Material from TUM -AIS:https://github.com/x-PPU ");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 590, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        infFrame.setVisible(true);
        infFrame.setLog(logMessage);
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(XPPUCaseStudyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XPPUCaseStudyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XPPUCaseStudyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XPPUCaseStudyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XPPUCaseStudyFrame().setVisible(true);
            }
        });
    }
    
    private void callQueries() {
        try {
            long startTime = System.nanoTime();
            

            String messageResult = "";

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            
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
            messageResult = "Outputs by Object Query Inference Result:\n";
            SQWRLResult q1Result = queryEngine.runSQWRLQuery("q0_1","Output(?o) , Object(?ob) , actsOver(?o,?ob) -> sqwrl:selectDistinct(?o, ?ob)");
            while (q1Result.next()) {
                messageResult += "Output " + q1Result.getValue("o").toString() + " acts over the object " + q1Result.getValue("ob").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            
            startTime = System.nanoTime();
            messageResult = "Data Inputs by Object Query Inference Result:\n";
            SQWRLResult q2Result = queryEngine.runSQWRLQuery("q0_2","DataInput(?o) , Object(?ob) , monitors(?o,?ob) -> sqwrl:selectDistinct(?o, ?ob)");
            while (q2Result.next()) {
                messageResult += "Input " + q2Result.getValue("o").toString() + " is a data input which monitors " + q2Result.getValue("ob").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            
            startTime = System.nanoTime();
            messageResult = "Agent with 'xPPU' Description Query Inference Result:\n";
            SQWRLResult q10Result = queryEngine.runSQWRLQuery("q1","Agent(?a) ^ hasDescription(?a, ?d) ^ swrlb:contains(?d, \"xPPU\") -> sqwrl:select(?a)");
            queryEngine.runSQWRLQuery("q1", "swrlb:add(?x, 2, 4) -> sqwrl:select(?x)");
            while (q10Result.next()) {
                messageResult += "Agent " + q10Result.getValue("a").toString() + " contains 'xPPU' in description\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
  
            startTime = System.nanoTime();
            messageResult = "States of the Model Query Inference Result:\n";
            SQWRLResult q15Result = queryEngine.runSQWRLQuery("q1_1","State(?s) , DynamicsModel(?m) , hasModelElement(?m,?s) -> sqwrl:selectDistinct(?m,?s)");
            while (q15Result.next()) {
                messageResult += "Model " + q15Result.getValue("m").toString() + "contains the state" + q15Result.getValue("s").toString() + "to perform the automatic operation\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Services with 'Buffering' Description Query Inference Result:\n";
            SQWRLResult q16Result = queryEngine.runSQWRLQuery("q2","Service(?s) , hasDescription(?s, ?d) , swrlb:contains(?d, \"Buffering\") -> sqwrl:select(?s)");
            while (q16Result.next()) {
                messageResult += "Service : " + q16Result.getValue("s").toString() + " contains 'Buffering' in service description\n";
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
            
            startTime = System.nanoTime();
            messageResult = "Digital Twins Query Inference Result:\n";
            SQWRLResult q24Result = queryEngine.runSQWRLQuery("q3","Agent(?a) , isVirtualizedIn(?a, ?dt) , AdministrationShell(?dt) , hasFile(?dt, ?f) -> sqwrl:selectDistinct(?a, ?dt, ?f)");
            while (q24Result.next()) {
                messageResult += "Agent " + q24Result.getValue("a").toString() + " has digital twin " + q24Result.getValue("dt").toString() + " with file " + q24Result.getValue("f").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Count of Agents Query Inference Result:\n";
            SQWRLResult q25Result = queryEngine.runSQWRLQuery("q4","Agent(?a) -> sqwrl:count(?a)");
            while (q25Result.next()) {
                messageResult += "Count of agents: " + q25Result.getValue("a").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Count of outputs Query Inference Result:\n";
            SQWRLResult q26Result = queryEngine.runSQWRLQuery("q5","Output(?o) -> sqwrl:count(?o)");
            while (q26Result.next()) {
                messageResult += "Count of outputs: " + q26Result.getValue("o").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);
            
            startTime = System.nanoTime();
            messageResult = "Count of inputs Query Inference Result:\n";
            SQWRLResult q27Result = queryEngine.runSQWRLQuery("q6","Input(?i) -> sqwrl:count(?i)");
            while (q27Result.next()) {
                messageResult += "Count of inputs: " + q27Result.getValue("i").toString() + "\n";
            }
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            messageResult += "Elapsed time (ms): " + String.valueOf(timeElapsed/1000000) + "\n";
            jTextArea1.setText(messageResult);
            logMessage += messageResult + "\n\n" + getDate() + " " + getHour() + "\n\n------------------------------\n\n";
            infFrame.setLog(logMessage);


        } catch (SQWRLException ex) {
            Logger.getLogger(XPPUCaseStudyFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SWRLParseException ex) {
            Logger.getLogger(XPPUCaseStudyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
