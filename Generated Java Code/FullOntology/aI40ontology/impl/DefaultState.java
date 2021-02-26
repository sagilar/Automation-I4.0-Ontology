package aI40ontology.impl;

import aI40ontology.*;


import java.net.URI;
import java.util.Collection;
import javax.xml.datatype.XMLGregorianCalendar;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultState <br>
 * @version generated on Fri Feb 26 14:35:08 COT 2021 by santiago
 */
public class DefaultState extends WrappedIndividualImpl implements State {

    public DefaultState(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#belongsToSequence
     */
     
    public Collection<? extends Sequence> getBelongsToSequence() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_BELONGSTOSEQUENCE,
                                               DefaultSequence.class);
    }

    public boolean hasBelongsToSequence() {
	   return !getBelongsToSequence().isEmpty();
    }

    public void addBelongsToSequence(Sequence newBelongsToSequence) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_BELONGSTOSEQUENCE,
                                       newBelongsToSequence);
    }

    public void removeBelongsToSequence(Sequence oldBelongsToSequence) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_BELONGSTOSEQUENCE,
                                          oldBelongsToSequence);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#finalizesLoop
     */
     
    public Collection<? extends Loop> getFinalizesLoop() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_FINALIZESLOOP,
                                               DefaultLoop.class);
    }

    public boolean hasFinalizesLoop() {
	   return !getFinalizesLoop().isEmpty();
    }

    public void addFinalizesLoop(Loop newFinalizesLoop) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_FINALIZESLOOP,
                                       newFinalizesLoop);
    }

    public void removeFinalizesLoop(Loop oldFinalizesLoop) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_FINALIZESLOOP,
                                          oldFinalizesLoop);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasNextState
     */
     
    public Collection<? extends State> getHasNextState() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASNEXTSTATE,
                                               DefaultState.class);
    }

    public boolean hasHasNextState() {
	   return !getHasNextState().isEmpty();
    }

    public void addHasNextState(State newHasNextState) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASNEXTSTATE,
                                       newHasNextState);
    }

    public void removeHasNextState(State oldHasNextState) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASNEXTSTATE,
                                          oldHasNextState);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasPreviousState
     */
     
    public Collection<? extends State> getHasPreviousState() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASPREVIOUSSTATE,
                                               DefaultState.class);
    }

    public boolean hasHasPreviousState() {
	   return !getHasPreviousState().isEmpty();
    }

    public void addHasPreviousState(State newHasPreviousState) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASPREVIOUSSTATE,
                                       newHasPreviousState);
    }

    public void removeHasPreviousState(State oldHasPreviousState) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASPREVIOUSSTATE,
                                          oldHasPreviousState);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasStateFunction
     */
     
    public Collection<? extends StateFunction> getHasStateFunction() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASSTATEFUNCTION,
                                               DefaultStateFunction.class);
    }

    public boolean hasHasStateFunction() {
	   return !getHasStateFunction().isEmpty();
    }

    public void addHasStateFunction(StateFunction newHasStateFunction) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASSTATEFUNCTION,
                                       newHasStateFunction);
    }

    public void removeHasStateFunction(StateFunction oldHasStateFunction) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASSTATEFUNCTION,
                                          oldHasStateFunction);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#initializesLoop
     */
     
    public Collection<? extends Loop> getInitializesLoop() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_INITIALIZESLOOP,
                                               DefaultLoop.class);
    }

    public boolean hasInitializesLoop() {
	   return !getInitializesLoop().isEmpty();
    }

    public void addInitializesLoop(Loop newInitializesLoop) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_INITIALIZESLOOP,
                                       newInitializesLoop);
    }

    public void removeInitializesLoop(Loop oldInitializesLoop) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_INITIALIZESLOOP,
                                          oldInitializesLoop);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#isConnectedToTransition
     */
     
    public Collection<? extends Transition> getIsConnectedToTransition() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISCONNECTEDTOTRANSITION,
                                               DefaultTransition.class);
    }

    public boolean hasIsConnectedToTransition() {
	   return !getIsConnectedToTransition().isEmpty();
    }

    public void addIsConnectedToTransition(Transition newIsConnectedToTransition) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISCONNECTEDTOTRANSITION,
                                       newIsConnectedToTransition);
    }

    public void removeIsConnectedToTransition(Transition oldIsConnectedToTransition) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISCONNECTEDTOTRANSITION,
                                          oldIsConnectedToTransition);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#isVirtualizedIn
     */
     
    public Collection<? extends Admin_Shell> getIsVirtualizedIn() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISVIRTUALIZEDIN,
                                               DefaultAdmin_Shell.class);
    }

    public boolean hasIsVirtualizedIn() {
	   return !getIsVirtualizedIn().isEmpty();
    }

    public void addIsVirtualizedIn(Admin_Shell newIsVirtualizedIn) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISVIRTUALIZEDIN,
                                       newIsVirtualizedIn);
    }

    public void removeIsVirtualizedIn(Admin_Shell oldIsVirtualizedIn) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISVIRTUALIZEDIN,
                                          oldIsVirtualizedIn);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#receivesExecutionScope
     */
     
    public Collection<? extends ExecutionScope> getReceivesExecutionScope() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_RECEIVESEXECUTIONSCOPE,
                                               DefaultExecutionScope.class);
    }

    public boolean hasReceivesExecutionScope() {
	   return !getReceivesExecutionScope().isEmpty();
    }

    public void addReceivesExecutionScope(ExecutionScope newReceivesExecutionScope) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_RECEIVESEXECUTIONSCOPE,
                                       newReceivesExecutionScope);
    }

    public void removeReceivesExecutionScope(ExecutionScope oldReceivesExecutionScope) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_RECEIVESEXECUTIONSCOPE,
                                          oldReceivesExecutionScope);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasDescription
     */
     
    public Collection<? extends String> getHasDescription() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDESCRIPTION, String.class);
    }

    public boolean hasHasDescription() {
		return !getHasDescription().isEmpty();
    }

    public void addHasDescription(String newHasDescription) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDESCRIPTION, newHasDescription);
    }

    public void removeHasDescription(String oldHasDescription) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDESCRIPTION, oldHasDescription);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasNumberOfTasks
     */
     
    public Collection<? extends Integer> getHasNumberOfTasks() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASNUMBEROFTASKS, Integer.class);
    }

    public boolean hasHasNumberOfTasks() {
		return !getHasNumberOfTasks().isEmpty();
    }

    public void addHasNumberOfTasks(Integer newHasNumberOfTasks) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASNUMBEROFTASKS, newHasNumberOfTasks);
    }

    public void removeHasNumberOfTasks(Integer oldHasNumberOfTasks) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASNUMBEROFTASKS, oldHasNumberOfTasks);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasRelatedOntology
     */
     
    public Collection<? extends Object> getHasRelatedOntology() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASRELATEDONTOLOGY, Object.class);
    }

    public boolean hasHasRelatedOntology() {
		return !getHasRelatedOntology().isEmpty();
    }

    public void addHasRelatedOntology(Object newHasRelatedOntology) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASRELATEDONTOLOGY, newHasRelatedOntology);
    }

    public void removeHasRelatedOntology(Object oldHasRelatedOntology) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASRELATEDONTOLOGY, oldHasRelatedOntology);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#isActive
     */
     
    public Collection<? extends Boolean> getIsActive() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISACTIVE, Boolean.class);
    }

    public boolean hasIsActive() {
		return !getIsActive().isEmpty();
    }

    public void addIsActive(Boolean newIsActive) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISACTIVE, newIsActive);
    }

    public void removeIsActive(Boolean oldIsActive) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISACTIVE, oldIsActive);
    }


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#abbreviation
     */
     
    public Collection<? extends Object> getHas_Abbreviated_Name() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_ABBREVIATED_NAME, Object.class);
    }

    public boolean hasHas_Abbreviated_Name() {
		return !getHas_Abbreviated_Name().isEmpty();
    }

    public void addHas_Abbreviated_Name(Object newHas_Abbreviated_Name) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_ABBREVIATED_NAME, newHas_Abbreviated_Name);
    }

    public void removeHas_Abbreviated_Name(Object oldHas_Abbreviated_Name) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_ABBREVIATED_NAME, oldHas_Abbreviated_Name);
    }


}
