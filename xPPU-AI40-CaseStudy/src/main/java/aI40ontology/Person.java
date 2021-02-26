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
 * Source Class: Person <br>
 * @version generated on Fri Feb 26 14:03:01 COT 2021 by santiago
 */

public interface Person extends Actor {

    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#generates
     */
     
    /**
     * Gets all property values for the generates property.<p>
     * 
     * @returns a collection of values for the generates property.
     */
    Collection<? extends Data> getGenerates();

    /**
     * Checks if the class has a generates property value.<p>
     * 
     * @return true if there is a generates property value.
     */
    boolean hasGenerates();

    /**
     * Adds a generates property value.<p>
     * 
     * @param newGenerates the generates property value to be added
     */
    void addGenerates(Data newGenerates);

    /**
     * Removes a generates property value.<p>
     * 
     * @param oldGenerates the generates property value to be removed.
     */
    void removeGenerates(Data oldGenerates);


    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#interactsWith
     */
     
    /**
     * Gets all property values for the interactsWith property.<p>
     * 
     * @returns a collection of values for the interactsWith property.
     */
    Collection<? extends Actor> getInteractsWith();

    /**
     * Checks if the class has a interactsWith property value.<p>
     * 
     * @return true if there is a interactsWith property value.
     */
    boolean hasInteractsWith();

    /**
     * Adds a interactsWith property value.<p>
     * 
     * @param newInteractsWith the interactsWith property value to be added
     */
    void addInteractsWith(Actor newInteractsWith);

    /**
     * Removes a interactsWith property value.<p>
     * 
     * @param oldInteractsWith the interactsWith property value to be removed.
     */
    void removeInteractsWith(Actor oldInteractsWith);


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
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#receives
     */
     
    /**
     * Gets all property values for the receives property.<p>
     * 
     * @returns a collection of values for the receives property.
     */
    Collection<? extends Data> getReceives();

    /**
     * Checks if the class has a receives property value.<p>
     * 
     * @return true if there is a receives property value.
     */
    boolean hasReceives();

    /**
     * Adds a receives property value.<p>
     * 
     * @param newReceives the receives property value to be added
     */
    void addReceives(Data newReceives);

    /**
     * Removes a receives property value.<p>
     * 
     * @param oldReceives the receives property value to be removed.
     */
    void removeReceives(Data oldReceives);


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
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
