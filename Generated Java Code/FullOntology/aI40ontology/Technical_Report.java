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
 * Source Class: Technical_Report <br>
 * @version generated on Fri Feb 26 14:35:08 COT 2021 by santiago
 */

public interface Technical_Report extends Publication {

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
     * Property https://w3id.org/i40/sto#hasDeveloper
     */
     
    /**
     * Gets all property values for the has_Developer property.<p>
     * 
     * @returns a collection of values for the has_Developer property.
     */
    Collection<? extends StandardOrganization> getHas_Developer();

    /**
     * Checks if the class has a has_Developer property value.<p>
     * 
     * @return true if there is a has_Developer property value.
     */
    boolean hasHas_Developer();

    /**
     * Adds a has_Developer property value.<p>
     * 
     * @param newHas_Developer the has_Developer property value to be added
     */
    void addHas_Developer(StandardOrganization newHas_Developer);

    /**
     * Removes a has_Developer property value.<p>
     * 
     * @param oldHas_Developer the has_Developer property value to be removed.
     */
    void removeHas_Developer(StandardOrganization oldHas_Developer);


    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasDomain
     */
     
    /**
     * Gets all property values for the has_Domain property.<p>
     * 
     * @returns a collection of values for the has_Domain property.
     */
    Collection<? extends Domain> getHas_Domain();

    /**
     * Checks if the class has a has_Domain property value.<p>
     * 
     * @return true if there is a has_Domain property value.
     */
    boolean hasHas_Domain();

    /**
     * Adds a has_Domain property value.<p>
     * 
     * @param newHas_Domain the has_Domain property value to be added
     */
    void addHas_Domain(Domain newHas_Domain);

    /**
     * Removes a has_Domain property value.<p>
     * 
     * @param oldHas_Domain the has_Domain property value to be removed.
     */
    void removeHas_Domain(Domain oldHas_Domain);


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
     * Property https://w3id.org/i40/sto#hasICS
     */
     
    /**
     * Gets all property values for the has_ICS property.<p>
     * 
     * @returns a collection of values for the has_ICS property.
     */
    Collection<? extends International_Classification_For_Standards> getHas_ICS();

    /**
     * Checks if the class has a has_ICS property value.<p>
     * 
     * @return true if there is a has_ICS property value.
     */
    boolean hasHas_ICS();

    /**
     * Adds a has_ICS property value.<p>
     * 
     * @param newHas_ICS the has_ICS property value to be added
     */
    void addHas_ICS(International_Classification_For_Standards newHas_ICS);

    /**
     * Removes a has_ICS property value.<p>
     * 
     * @param oldHas_ICS the has_ICS property value to be removed.
     */
    void removeHas_ICS(International_Classification_For_Standards oldHas_ICS);


    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasMotivation
     */
     
    /**
     * Gets all property values for the hasMotivation property.<p>
     * 
     * @returns a collection of values for the hasMotivation property.
     */
    Collection<? extends Motivation> getHasMotivation();

    /**
     * Checks if the class has a hasMotivation property value.<p>
     * 
     * @return true if there is a hasMotivation property value.
     */
    boolean hasHasMotivation();

    /**
     * Adds a hasMotivation property value.<p>
     * 
     * @param newHasMotivation the hasMotivation property value to be added
     */
    void addHasMotivation(Motivation newHasMotivation);

    /**
     * Removes a hasMotivation property value.<p>
     * 
     * @param oldHasMotivation the hasMotivation property value to be removed.
     */
    void removeHasMotivation(Motivation oldHasMotivation);


    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasPublisher
     */
     
    /**
     * Gets all property values for the has_Publisher property.<p>
     * 
     * @returns a collection of values for the has_Publisher property.
     */
    Collection<? extends StandardOrganization> getHas_Publisher();

    /**
     * Checks if the class has a has_Publisher property value.<p>
     * 
     * @return true if there is a has_Publisher property value.
     */
    boolean hasHas_Publisher();

    /**
     * Adds a has_Publisher property value.<p>
     * 
     * @param newHas_Publisher the has_Publisher property value to be added
     */
    void addHas_Publisher(StandardOrganization newHas_Publisher);

    /**
     * Removes a has_Publisher property value.<p>
     * 
     * @param oldHas_Publisher the has_Publisher property value to be removed.
     */
    void removeHas_Publisher(StandardOrganization oldHas_Publisher);


    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasTechnicalCommittee
     */
     
    /**
     * Gets all property values for the has_Technical_Committee property.<p>
     * 
     * @returns a collection of values for the has_Technical_Committee property.
     */
    Collection<? extends Technical_Committee> getHas_Technical_Committee();

    /**
     * Checks if the class has a has_Technical_Committee property value.<p>
     * 
     * @return true if there is a has_Technical_Committee property value.
     */
    boolean hasHas_Technical_Committee();

    /**
     * Adds a has_Technical_Committee property value.<p>
     * 
     * @param newHas_Technical_Committee the has_Technical_Committee property value to be added
     */
    void addHas_Technical_Committee(Technical_Committee newHas_Technical_Committee);

    /**
     * Removes a has_Technical_Committee property value.<p>
     * 
     * @param oldHas_Technical_Committee the has_Technical_Committee property value to be removed.
     */
    void removeHas_Technical_Committee(Technical_Committee oldHas_Technical_Committee);


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
     * Property https://w3id.org/i40/sto#hasImplementationFormat
     */
     
    /**
     * Gets all property values for the has_Implementation_Format property.<p>
     * 
     * @returns a collection of values for the has_Implementation_Format property.
     */
    Collection<? extends Object> getHas_Implementation_Format();

    /**
     * Checks if the class has a has_Implementation_Format property value.<p>
     * 
     * @return true if there is a has_Implementation_Format property value.
     */
    boolean hasHas_Implementation_Format();

    /**
     * Adds a has_Implementation_Format property value.<p>
     * 
     * @param newHas_Implementation_Format the has_Implementation_Format property value to be added
     */
    void addHas_Implementation_Format(Object newHas_Implementation_Format);

    /**
     * Removes a has_Implementation_Format property value.<p>
     * 
     * @param oldHas_Implementation_Format the has_Implementation_Format property value to be removed.
     */
    void removeHas_Implementation_Format(Object oldHas_Implementation_Format);



    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasOfficialResource
     */
     
    /**
     * Gets all property values for the has_Official_Resource property.<p>
     * 
     * @returns a collection of values for the has_Official_Resource property.
     */
    Collection<? extends URI> getHas_Official_Resource();

    /**
     * Checks if the class has a has_Official_Resource property value.<p>
     * 
     * @return true if there is a has_Official_Resource property value.
     */
    boolean hasHas_Official_Resource();

    /**
     * Adds a has_Official_Resource property value.<p>
     * 
     * @param newHas_Official_Resource the has_Official_Resource property value to be added
     */
    void addHas_Official_Resource(URI newHas_Official_Resource);

    /**
     * Removes a has_Official_Resource property value.<p>
     * 
     * @param oldHas_Official_Resource the has_Official_Resource property value to be removed.
     */
    void removeHas_Official_Resource(URI oldHas_Official_Resource);



    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasOntology
     */
     
    /**
     * Gets all property values for the has_Ontology property.<p>
     * 
     * @returns a collection of values for the has_Ontology property.
     */
    Collection<? extends URI> getHas_Ontology();

    /**
     * Checks if the class has a has_Ontology property value.<p>
     * 
     * @return true if there is a has_Ontology property value.
     */
    boolean hasHas_Ontology();

    /**
     * Adds a has_Ontology property value.<p>
     * 
     * @param newHas_Ontology the has_Ontology property value to be added
     */
    void addHas_Ontology(URI newHas_Ontology);

    /**
     * Removes a has_Ontology property value.<p>
     * 
     * @param oldHas_Ontology the has_Ontology property value to be removed.
     */
    void removeHas_Ontology(URI oldHas_Ontology);



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
     * Property https://w3id.org/i40/sto#hasPublicationDate
     */
     
    /**
     * Gets all property values for the has_Publication_Date property.<p>
     * 
     * @returns a collection of values for the has_Publication_Date property.
     */
    Collection<? extends Object> getHas_Publication_Date();

    /**
     * Checks if the class has a has_Publication_Date property value.<p>
     * 
     * @return true if there is a has_Publication_Date property value.
     */
    boolean hasHas_Publication_Date();

    /**
     * Adds a has_Publication_Date property value.<p>
     * 
     * @param newHas_Publication_Date the has_Publication_Date property value to be added
     */
    void addHas_Publication_Date(Object newHas_Publication_Date);

    /**
     * Removes a has_Publication_Date property value.<p>
     * 
     * @param oldHas_Publication_Date the has_Publication_Date property value to be removed.
     */
    void removeHas_Publication_Date(Object oldHas_Publication_Date);



    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasStabilityDate
     */
     
    /**
     * Gets all property values for the has_Stability_Date property.<p>
     * 
     * @returns a collection of values for the has_Stability_Date property.
     */
    Collection<? extends Object> getHas_Stability_Date();

    /**
     * Checks if the class has a has_Stability_Date property value.<p>
     * 
     * @return true if there is a has_Stability_Date property value.
     */
    boolean hasHas_Stability_Date();

    /**
     * Adds a has_Stability_Date property value.<p>
     * 
     * @param newHas_Stability_Date the has_Stability_Date property value to be added
     */
    void addHas_Stability_Date(Object newHas_Stability_Date);

    /**
     * Removes a has_Stability_Date property value.<p>
     * 
     * @param oldHas_Stability_Date the has_Stability_Date property value to be removed.
     */
    void removeHas_Stability_Date(Object oldHas_Stability_Date);



    /* ***************************************************
     * Property https://w3id.org/i40/sto#hasStatus
     */
     
    /**
     * Gets all property values for the has_Status property.<p>
     * 
     * @returns a collection of values for the has_Status property.
     */
    Collection<? extends Object> getHas_Status();

    /**
     * Checks if the class has a has_Status property value.<p>
     * 
     * @return true if there is a has_Status property value.
     */
    boolean hasHas_Status();

    /**
     * Adds a has_Status property value.<p>
     * 
     * @param newHas_Status the has_Status property value to be added
     */
    void addHas_Status(Object newHas_Status);

    /**
     * Removes a has_Status property value.<p>
     * 
     * @param oldHas_Status the has_Status property value to be removed.
     */
    void removeHas_Status(Object oldHas_Status);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
