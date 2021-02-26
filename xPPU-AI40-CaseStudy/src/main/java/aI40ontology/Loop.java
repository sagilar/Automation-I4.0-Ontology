package aI40ontology;

import java.net.URI;
import java.util.Collection;
import javax.xml.datatype.XMLGregorianCalendar;

import org.protege.owl.codegeneration.WrappedIndividual;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: Loop <br>
 * @version generated on Fri Feb 26 14:03:01 COT 2021 by santiago
 */

public interface Loop extends SetElement {

    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#belongsToSequence
     */
     
    /**
     * Gets all property values for the belongsToSequence property.<p>
     * 
     * @returns a collection of values for the belongsToSequence property.
     */
    Collection<? extends Sequence> getBelongsToSequence();

    /**
     * Checks if the class has a belongsToSequence property value.<p>
     * 
     * @return true if there is a belongsToSequence property value.
     */
    boolean hasBelongsToSequence();

    /**
     * Adds a belongsToSequence property value.<p>
     * 
     * @param newBelongsToSequence the belongsToSequence property value to be added
     */
    void addBelongsToSequence(Sequence newBelongsToSequence);

    /**
     * Removes a belongsToSequence property value.<p>
     * 
     * @param oldBelongsToSequence the belongsToSequence property value to be removed.
     */
    void removeBelongsToSequence(Sequence oldBelongsToSequence);


    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#isVirtualizedIn
     */
     
    /**
     * Gets all property values for the isVirtualizedIn property.<p>
     * 
     * @returns a collection of values for the isVirtualizedIn property.
     */
    Collection<? extends AdministrationShell> getIsVirtualizedIn();

    /**
     * Checks if the class has a isVirtualizedIn property value.<p>
     * 
     * @return true if there is a isVirtualizedIn property value.
     */
    boolean hasIsVirtualizedIn();

    /**
     * Adds a isVirtualizedIn property value.<p>
     * 
     * @param newIsVirtualizedIn the isVirtualizedIn property value to be added
     */
    void addIsVirtualizedIn(AdministrationShell newIsVirtualizedIn);

    /**
     * Removes a isVirtualizedIn property value.<p>
     * 
     * @param oldIsVirtualizedIn the isVirtualizedIn property value to be removed.
     */
    void removeIsVirtualizedIn(AdministrationShell oldIsVirtualizedIn);


    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasDescription
     */
     
    /**
     * Gets all property values for the hasDescription property.<p>
     * 
     * @returns a collection of values for the hasDescription property.
     */
    Collection<? extends String> getHasDescription();

    /**
     * Checks if the class has a hasDescription property value.<p>
     * 
     * @return true if there is a hasDescription property value.
     */
    boolean hasHasDescription();

    /**
     * Adds a hasDescription property value.<p>
     * 
     * @param newHasDescription the hasDescription property value to be added
     */
    void addHasDescription(String newHasDescription);

    /**
     * Removes a hasDescription property value.<p>
     * 
     * @param oldHasDescription the hasDescription property value to be removed.
     */
    void removeHasDescription(String oldHasDescription);



    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasNumberOfStates
     */
     
    /**
     * Gets all property values for the hasNumberOfStates property.<p>
     * 
     * @returns a collection of values for the hasNumberOfStates property.
     */
    Collection<? extends Integer> getHasNumberOfStates();

    /**
     * Checks if the class has a hasNumberOfStates property value.<p>
     * 
     * @return true if there is a hasNumberOfStates property value.
     */
    boolean hasHasNumberOfStates();

    /**
     * Adds a hasNumberOfStates property value.<p>
     * 
     * @param newHasNumberOfStates the hasNumberOfStates property value to be added
     */
    void addHasNumberOfStates(Integer newHasNumberOfStates);

    /**
     * Removes a hasNumberOfStates property value.<p>
     * 
     * @param oldHasNumberOfStates the hasNumberOfStates property value to be removed.
     */
    void removeHasNumberOfStates(Integer oldHasNumberOfStates);



    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasNumberOfTasks
     */
     
    /**
     * Gets all property values for the hasNumberOfTasks property.<p>
     * 
     * @returns a collection of values for the hasNumberOfTasks property.
     */
    Collection<? extends Integer> getHasNumberOfTasks();

    /**
     * Checks if the class has a hasNumberOfTasks property value.<p>
     * 
     * @return true if there is a hasNumberOfTasks property value.
     */
    boolean hasHasNumberOfTasks();

    /**
     * Adds a hasNumberOfTasks property value.<p>
     * 
     * @param newHasNumberOfTasks the hasNumberOfTasks property value to be added
     */
    void addHasNumberOfTasks(Integer newHasNumberOfTasks);

    /**
     * Removes a hasNumberOfTasks property value.<p>
     * 
     * @param oldHasNumberOfTasks the hasNumberOfTasks property value to be removed.
     */
    void removeHasNumberOfTasks(Integer oldHasNumberOfTasks);



    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasNumberOfTransitions
     */
     
    /**
     * Gets all property values for the hasNumberOfTransitions property.<p>
     * 
     * @returns a collection of values for the hasNumberOfTransitions property.
     */
    Collection<? extends Integer> getHasNumberOfTransitions();

    /**
     * Checks if the class has a hasNumberOfTransitions property value.<p>
     * 
     * @return true if there is a hasNumberOfTransitions property value.
     */
    boolean hasHasNumberOfTransitions();

    /**
     * Adds a hasNumberOfTransitions property value.<p>
     * 
     * @param newHasNumberOfTransitions the hasNumberOfTransitions property value to be added
     */
    void addHasNumberOfTransitions(Integer newHasNumberOfTransitions);

    /**
     * Removes a hasNumberOfTransitions property value.<p>
     * 
     * @param oldHasNumberOfTransitions the hasNumberOfTransitions property value to be removed.
     */
    void removeHasNumberOfTransitions(Integer oldHasNumberOfTransitions);



    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasRelatedOntology
     */
     
    /**
     * Gets all property values for the hasRelatedOntology property.<p>
     * 
     * @returns a collection of values for the hasRelatedOntology property.
     */
    Collection<? extends Object> getHasRelatedOntology();

    /**
     * Checks if the class has a hasRelatedOntology property value.<p>
     * 
     * @return true if there is a hasRelatedOntology property value.
     */
    boolean hasHasRelatedOntology();

    /**
     * Adds a hasRelatedOntology property value.<p>
     * 
     * @param newHasRelatedOntology the hasRelatedOntology property value to be added
     */
    void addHasRelatedOntology(Object newHasRelatedOntology);

    /**
     * Removes a hasRelatedOntology property value.<p>
     * 
     * @param oldHasRelatedOntology the hasRelatedOntology property value to be removed.
     */
    void removeHasRelatedOntology(Object oldHasRelatedOntology);



    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#isParentSetElement
     */
     
    /**
     * Gets all property values for the isParentSetElement property.<p>
     * 
     * @returns a collection of values for the isParentSetElement property.
     */
    Collection<? extends Boolean> getIsParentSetElement();

    /**
     * Checks if the class has a isParentSetElement property value.<p>
     * 
     * @return true if there is a isParentSetElement property value.
     */
    boolean hasIsParentSetElement();

    /**
     * Adds a isParentSetElement property value.<p>
     * 
     * @param newIsParentSetElement the isParentSetElement property value to be added
     */
    void addIsParentSetElement(Boolean newIsParentSetElement);

    /**
     * Removes a isParentSetElement property value.<p>
     * 
     * @param oldIsParentSetElement the isParentSetElement property value to be removed.
     */
    void removeIsParentSetElement(Boolean oldIsParentSetElement);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
