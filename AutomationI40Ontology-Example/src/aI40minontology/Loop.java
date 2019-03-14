package aI40minontology;

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
 * @version generated on Sun Feb 10 20:58:15 COT 2019 by Santiago
 */

public interface Loop extends Sequence {

    /* ***************************************************
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#belongsToSequence
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
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#belongsToSubsequence
     */
     
    /**
     * Gets all property values for the belongsToSubsequence property.<p>
     * 
     * @returns a collection of values for the belongsToSubsequence property.
     */
    Collection<? extends Subsequence> getBelongsToSubsequence();

    /**
     * Checks if the class has a belongsToSubsequence property value.<p>
     * 
     * @return true if there is a belongsToSubsequence property value.
     */
    boolean hasBelongsToSubsequence();

    /**
     * Adds a belongsToSubsequence property value.<p>
     * 
     * @param newBelongsToSubsequence the belongsToSubsequence property value to be added
     */
    void addBelongsToSubsequence(Subsequence newBelongsToSubsequence);

    /**
     * Removes a belongsToSubsequence property value.<p>
     * 
     * @param oldBelongsToSubsequence the belongsToSubsequence property value to be removed.
     */
    void removeBelongsToSubsequence(Subsequence oldBelongsToSubsequence);


    /* ***************************************************
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#isConnectedToArc
     */
     
    /**
     * Gets all property values for the isConnectedToArc property.<p>
     * 
     * @returns a collection of values for the isConnectedToArc property.
     */
    Collection<? extends Arc> getIsConnectedToArc();

    /**
     * Checks if the class has a isConnectedToArc property value.<p>
     * 
     * @return true if there is a isConnectedToArc property value.
     */
    boolean hasIsConnectedToArc();

    /**
     * Adds a isConnectedToArc property value.<p>
     * 
     * @param newIsConnectedToArc the isConnectedToArc property value to be added
     */
    void addIsConnectedToArc(Arc newIsConnectedToArc);

    /**
     * Removes a isConnectedToArc property value.<p>
     * 
     * @param oldIsConnectedToArc the isConnectedToArc property value to be removed.
     */
    void removeIsConnectedToArc(Arc oldIsConnectedToArc);


    /* ***************************************************
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#hasDescription
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
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#hasIdentifier
     */
     
    /**
     * Gets all property values for the hasIdentifier property.<p>
     * 
     * @returns a collection of values for the hasIdentifier property.
     */
    Collection<? extends Integer> getHasIdentifier();

    /**
     * Checks if the class has a hasIdentifier property value.<p>
     * 
     * @return true if there is a hasIdentifier property value.
     */
    boolean hasHasIdentifier();

    /**
     * Adds a hasIdentifier property value.<p>
     * 
     * @param newHasIdentifier the hasIdentifier property value to be added
     */
    void addHasIdentifier(Integer newHasIdentifier);

    /**
     * Removes a hasIdentifier property value.<p>
     * 
     * @param oldHasIdentifier the hasIdentifier property value to be removed.
     */
    void removeHasIdentifier(Integer oldHasIdentifier);



    /* ***************************************************
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#hasNumberOfStates
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
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#hasNumberOfTasks
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
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#hasNumberOfTransitions
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
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#isChildSetElement
     */
     
    /**
     * Gets all property values for the isChildSetElement property.<p>
     * 
     * @returns a collection of values for the isChildSetElement property.
     */
    Collection<? extends Boolean> getIsChildSetElement();

    /**
     * Checks if the class has a isChildSetElement property value.<p>
     * 
     * @return true if there is a isChildSetElement property value.
     */
    boolean hasIsChildSetElement();

    /**
     * Adds a isChildSetElement property value.<p>
     * 
     * @param newIsChildSetElement the isChildSetElement property value to be added
     */
    void addIsChildSetElement(Boolean newIsChildSetElement);

    /**
     * Removes a isChildSetElement property value.<p>
     * 
     * @param oldIsChildSetElement the isChildSetElement property value to be removed.
     */
    void removeIsChildSetElement(Boolean oldIsChildSetElement);



    /* ***************************************************
     * Property http://www.semanticweb.org/Automation-I4.0-Ontology#isParentSetElement
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