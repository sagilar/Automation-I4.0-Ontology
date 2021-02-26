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
 * Source Class: Foaf:Document <br>
 * @version generated on Fri Feb 26 14:35:08 COT 2021 by santiago
 */

public interface Foaf:Document extends WrappedIndividual {

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
     * Property https://w3id.org/i40/sto#hasAvailableLanguage
     */
     
    /**
     * Gets all property values for the hasAvailableLanguage property.<p>
     * 
     * @returns a collection of values for the hasAvailableLanguage property.
     */
    Collection<? extends Dcterms:LinguisticSystem> getHasAvailableLanguage();

    /**
     * Checks if the class has a hasAvailableLanguage property value.<p>
     * 
     * @return true if there is a hasAvailableLanguage property value.
     */
    boolean hasHasAvailableLanguage();

    /**
     * Adds a hasAvailableLanguage property value.<p>
     * 
     * @param newHasAvailableLanguage the hasAvailableLanguage property value to be added
     */
    void addHasAvailableLanguage(Dcterms:LinguisticSystem newHasAvailableLanguage);

    /**
     * Removes a hasAvailableLanguage property value.<p>
     * 
     * @param oldHasAvailableLanguage the hasAvailableLanguage property value to be removed.
     */
    void removeHasAvailableLanguage(Dcterms:LinguisticSystem oldHasAvailableLanguage);


    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasFileSize
     */
     
    /**
     * Gets all property values for the has_File_Size property.<p>
     * 
     * @returns a collection of values for the has_File_Size property.
     */
    Collection<? extends Measure> getHas_File_Size();

    /**
     * Checks if the class has a has_File_Size property value.<p>
     * 
     * @return true if there is a has_File_Size property value.
     */
    boolean hasHas_File_Size();

    /**
     * Adds a has_File_Size property value.<p>
     * 
     * @param newHas_File_Size the has_File_Size property value to be added
     */
    void addHas_File_Size(Measure newHas_File_Size);

    /**
     * Removes a has_File_Size property value.<p>
     * 
     * @param oldHas_File_Size the has_File_Size property value to be removed.
     */
    void removeHas_File_Size(Measure oldHas_File_Size);


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
     * Property https://w3id.org/i40/sto#hasEdition
     */
     
    /**
     * Gets all property values for the has_Edition property.<p>
     * 
     * @returns a collection of values for the has_Edition property.
     */
    Collection<? extends Float> getHas_Edition();

    /**
     * Checks if the class has a has_Edition property value.<p>
     * 
     * @return true if there is a has_Edition property value.
     */
    boolean hasHas_Edition();

    /**
     * Adds a has_Edition property value.<p>
     * 
     * @param newHas_Edition the has_Edition property value to be added
     */
    void addHas_Edition(Float newHas_Edition);

    /**
     * Removes a has_Edition property value.<p>
     * 
     * @param oldHas_Edition the has_Edition property value to be removed.
     */
    void removeHas_Edition(Float oldHas_Edition);



    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasPages
     */
     
    /**
     * Gets all property values for the has_Pages property.<p>
     * 
     * @returns a collection of values for the has_Pages property.
     */
    Collection<? extends Integer> getHas_Pages();

    /**
     * Checks if the class has a has_Pages property value.<p>
     * 
     * @return true if there is a has_Pages property value.
     */
    boolean hasHas_Pages();

    /**
     * Adds a has_Pages property value.<p>
     * 
     * @param newHas_Pages the has_Pages property value to be added
     */
    void addHas_Pages(Integer newHas_Pages);

    /**
     * Removes a has_Pages property value.<p>
     * 
     * @param oldHas_Pages the has_Pages property value to be removed.
     */
    void removeHas_Pages(Integer oldHas_Pages);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
