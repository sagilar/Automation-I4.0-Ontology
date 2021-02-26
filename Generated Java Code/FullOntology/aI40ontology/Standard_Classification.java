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
 * Source Class: Standard_Classification <br>
 * @version generated on Fri Feb 26 14:35:08 COT 2021 by santiago
 */

public interface Standard_Classification extends WrappedIndividual {

    /* ***************************************************
     * Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#isVirtualizedIn
     */
     
    /**
     * Gets all property values for the isVirtualizedIn property.<p>
     * 
     * @returns a collection of values for the isVirtualizedIn property.
     */
    Collection<? extends Admin_Shell> getIsVirtualizedIn();

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
    void addIsVirtualizedIn(Admin_Shell newIsVirtualizedIn);

    /**
     * Removes a isVirtualizedIn property value.<p>
     * 
     * @param oldIsVirtualizedIn the isVirtualizedIn property value to be removed.
     */
    void removeIsVirtualizedIn(Admin_Shell oldIsVirtualizedIn);


    /* ***************************************************
     * Property https://w3id.org/i40/sto#alignesWith
     */
     
    /**
     * Gets all property values for the alignes_With property.<p>
     * 
     * @returns a collection of values for the alignes_With property.
     */
    Collection<? extends Standard_Classification> getAlignes_With();

    /**
     * Checks if the class has a alignes_With property value.<p>
     * 
     * @return true if there is a alignes_With property value.
     */
    boolean hasAlignes_With();

    /**
     * Adds a alignes_With property value.<p>
     * 
     * @param newAlignes_With the alignes_With property value to be added
     */
    void addAlignes_With(Standard_Classification newAlignes_With);

    /**
     * Removes a alignes_With property value.<p>
     * 
     * @param oldAlignes_With the alignes_With property value to be removed.
     */
    void removeAlignes_With(Standard_Classification oldAlignes_With);


    /* ***************************************************
     * Property https://w3id.org/i40/sto#isDescribedin
     */
     
    /**
     * Gets all property values for the is_Described_in property.<p>
     * 
     * @returns a collection of values for the is_Described_in property.
     */
    Collection<? extends Standarization_Framework> getIs_Described_in();

    /**
     * Checks if the class has a is_Described_in property value.<p>
     * 
     * @return true if there is a is_Described_in property value.
     */
    boolean hasIs_Described_in();

    /**
     * Adds a is_Described_in property value.<p>
     * 
     * @param newIs_Described_in the is_Described_in property value to be added
     */
    void addIs_Described_in(Standarization_Framework newIs_Described_in);

    /**
     * Removes a is_Described_in property value.<p>
     * 
     * @param oldIs_Described_in the is_Described_in property value to be removed.
     */
    void removeIs_Described_in(Standarization_Framework oldIs_Described_in);


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
     * Property https://w3id.org/i40/sto#abbreviation
     */
     
    /**
     * Gets all property values for the has_Abbreviated_Name property.<p>
     * 
     * @returns a collection of values for the has_Abbreviated_Name property.
     */
    Collection<? extends Object> getHas_Abbreviated_Name();

    /**
     * Checks if the class has a has_Abbreviated_Name property value.<p>
     * 
     * @return true if there is a has_Abbreviated_Name property value.
     */
    boolean hasHas_Abbreviated_Name();

    /**
     * Adds a has_Abbreviated_Name property value.<p>
     * 
     * @param newHas_Abbreviated_Name the has_Abbreviated_Name property value to be added
     */
    void addHas_Abbreviated_Name(Object newHas_Abbreviated_Name);

    /**
     * Removes a has_Abbreviated_Name property value.<p>
     * 
     * @param oldHas_Abbreviated_Name the has_Abbreviated_Name property value to be removed.
     */
    void removeHas_Abbreviated_Name(Object oldHas_Abbreviated_Name);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
