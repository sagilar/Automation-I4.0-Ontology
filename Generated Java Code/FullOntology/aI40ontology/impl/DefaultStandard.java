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
 * Source Class: DefaultStandard <br>
 * @version generated on Fri Feb 26 14:35:08 COT 2021 by santiago
 */
public class DefaultStandard extends WrappedIndividualImpl implements Standard {

    public DefaultStandard(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://www.ontologydesignpatterns.org/ont/dul/DUL.owl#isComponentOf
     */
     
    public Collection<? extends WrappedIndividual> getIs_Component_Of() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_IS_COMPONENT_OF,
                                               WrappedIndividualImpl.class);
    }

    public boolean hasIs_Component_Of() {
	   return !getIs_Component_Of().isEmpty();
    }

    public void addIs_Component_Of(WrappedIndividual newIs_Component_Of) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_IS_COMPONENT_OF,
                                       newIs_Component_Of);
    }

    public void removeIs_Component_Of(WrappedIndividual oldIs_Component_Of) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_IS_COMPONENT_OF,
                                          oldIs_Component_Of);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasDomain
     */
     
    public Collection<? extends Domain> getHasDomain() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASDOMAIN,
                                               DefaultDomain.class);
    }

    public boolean hasHasDomain() {
	   return !getHasDomain().isEmpty();
    }

    public void addHasDomain(Domain newHasDomain) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASDOMAIN,
                                       newHasDomain);
    }

    public void removeHasDomain(Domain oldHasDomain) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASDOMAIN,
                                          oldHasDomain);
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
     * Object Property https://w3id.org/i40/hasTargetConcern
     */
     
    public Collection<? extends Concern> getHas_Target_Concern() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HAS_TARGET_CONCERN,
                                               DefaultConcern.class);
    }

    public boolean hasHas_Target_Concern() {
	   return !getHas_Target_Concern().isEmpty();
    }

    public void addHas_Target_Concern(Concern newHas_Target_Concern) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HAS_TARGET_CONCERN,
                                       newHas_Target_Concern);
    }

    public void removeHas_Target_Concern(Concern oldHas_Target_Concern) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HAS_TARGET_CONCERN,
                                          oldHas_Target_Concern);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#Standardizes
     */
     
    public Collection<? extends WrappedIndividual> getStandardizes() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_STANDARDIZES,
                                               WrappedIndividualImpl.class);
    }

    public boolean hasStandardizes() {
	   return !getStandardizes().isEmpty();
    }

    public void addStandardizes(WrappedIndividual newStandardizes) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_STANDARDIZES,
                                       newStandardizes);
    }

    public void removeStandardizes(WrappedIndividual oldStandardizes) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_STANDARDIZES,
                                          oldStandardizes);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasAvailableLanguage
     */
     
    public Collection<? extends Dcterms:LinguisticSystem> getHasAvailableLanguage() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASAVAILABLELANGUAGE,
                                               DefaultDcterms:LinguisticSystem.class);
    }

    public boolean hasHasAvailableLanguage() {
	   return !getHasAvailableLanguage().isEmpty();
    }

    public void addHasAvailableLanguage(Dcterms:LinguisticSystem newHasAvailableLanguage) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASAVAILABLELANGUAGE,
                                       newHasAvailableLanguage);
    }

    public void removeHasAvailableLanguage(Dcterms:LinguisticSystem oldHasAvailableLanguage) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASAVAILABLELANGUAGE,
                                          oldHasAvailableLanguage);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasClassification
     */
     
    public Collection<? extends Standard_Classification> getHas_Classification() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HAS_CLASSIFICATION,
                                               DefaultStandard_Classification.class);
    }

    public boolean hasHas_Classification() {
	   return !getHas_Classification().isEmpty();
    }

    public void addHas_Classification(Standard_Classification newHas_Classification) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HAS_CLASSIFICATION,
                                       newHas_Classification);
    }

    public void removeHas_Classification(Standard_Classification oldHas_Classification) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HAS_CLASSIFICATION,
                                          oldHas_Classification);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasDeveloper
     */
     
    public Collection<? extends StandardOrganization> getHas_Developer() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HAS_DEVELOPER,
                                               DefaultStandardOrganization.class);
    }

    public boolean hasHas_Developer() {
	   return !getHas_Developer().isEmpty();
    }

    public void addHas_Developer(StandardOrganization newHas_Developer) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HAS_DEVELOPER,
                                       newHas_Developer);
    }

    public void removeHas_Developer(StandardOrganization oldHas_Developer) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HAS_DEVELOPER,
                                          oldHas_Developer);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasDomain
     */
     
    public Collection<? extends Domain> getHas_Domain() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HAS_DOMAIN,
                                               DefaultDomain.class);
    }

    public boolean hasHas_Domain() {
	   return !getHas_Domain().isEmpty();
    }

    public void addHas_Domain(Domain newHas_Domain) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HAS_DOMAIN,
                                       newHas_Domain);
    }

    public void removeHas_Domain(Domain oldHas_Domain) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HAS_DOMAIN,
                                          oldHas_Domain);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasFileSize
     */
     
    public Collection<? extends Measure> getHas_File_Size() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HAS_FILE_SIZE,
                                               DefaultMeasure.class);
    }

    public boolean hasHas_File_Size() {
	   return !getHas_File_Size().isEmpty();
    }

    public void addHas_File_Size(Measure newHas_File_Size) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HAS_FILE_SIZE,
                                       newHas_File_Size);
    }

    public void removeHas_File_Size(Measure oldHas_File_Size) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HAS_FILE_SIZE,
                                          oldHas_File_Size);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasICS
     */
     
    public Collection<? extends International_Classification_For_Standards> getHas_ICS() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HAS_ICS,
                                               DefaultInternational_Classification_For_Standards.class);
    }

    public boolean hasHas_ICS() {
	   return !getHas_ICS().isEmpty();
    }

    public void addHas_ICS(International_Classification_For_Standards newHas_ICS) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HAS_ICS,
                                       newHas_ICS);
    }

    public void removeHas_ICS(International_Classification_For_Standards oldHas_ICS) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HAS_ICS,
                                          oldHas_ICS);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasMotivation
     */
     
    public Collection<? extends Motivation> getHasMotivation() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASMOTIVATION,
                                               DefaultMotivation.class);
    }

    public boolean hasHasMotivation() {
	   return !getHasMotivation().isEmpty();
    }

    public void addHasMotivation(Motivation newHasMotivation) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASMOTIVATION,
                                       newHasMotivation);
    }

    public void removeHasMotivation(Motivation oldHasMotivation) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASMOTIVATION,
                                          oldHasMotivation);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasPublisher
     */
     
    public Collection<? extends StandardOrganization> getHas_Publisher() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HAS_PUBLISHER,
                                               DefaultStandardOrganization.class);
    }

    public boolean hasHas_Publisher() {
	   return !getHas_Publisher().isEmpty();
    }

    public void addHas_Publisher(StandardOrganization newHas_Publisher) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HAS_PUBLISHER,
                                       newHas_Publisher);
    }

    public void removeHas_Publisher(StandardOrganization oldHas_Publisher) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HAS_PUBLISHER,
                                          oldHas_Publisher);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#hasTechnicalCommittee
     */
     
    public Collection<? extends Technical_Committee> getHas_Technical_Committee() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HAS_TECHNICAL_COMMITTEE,
                                               DefaultTechnical_Committee.class);
    }

    public boolean hasHas_Technical_Committee() {
	   return !getHas_Technical_Committee().isEmpty();
    }

    public void addHas_Technical_Committee(Technical_Committee newHas_Technical_Committee) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HAS_TECHNICAL_COMMITTEE,
                                       newHas_Technical_Committee);
    }

    public void removeHas_Technical_Committee(Technical_Committee oldHas_Technical_Committee) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HAS_TECHNICAL_COMMITTEE,
                                          oldHas_Technical_Committee);
    }


    /* ***************************************************
     * Object Property https://w3id.org/i40/sto#relatedTo
     */
     
    public Collection<? extends Standard> getRelated_To() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_RELATED_TO,
                                               DefaultStandard.class);
    }

    public boolean hasRelated_To() {
	   return !getRelated_To().isEmpty();
    }

    public void addRelated_To(Standard newRelated_To) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_RELATED_TO,
                                       newRelated_To);
    }

    public void removeRelated_To(Standard oldRelated_To) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_RELATED_TO,
                                          oldRelated_To);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#concernsTo
     */
     
    public Collection<? extends String> getConcernsTo() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CONCERNSTO, String.class);
    }

    public boolean hasConcernsTo() {
		return !getConcernsTo().isEmpty();
    }

    public void addConcernsTo(String newConcernsTo) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CONCERNSTO, newConcernsTo);
    }

    public void removeConcernsTo(String oldConcernsTo) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CONCERNSTO, oldConcernsTo);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasDBpediaResource
     */
     
    public Collection<? extends Object> getHasDBpediaResource() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDBPEDIARESOURCE, Object.class);
    }

    public boolean hasHasDBpediaResource() {
		return !getHasDBpediaResource().isEmpty();
    }

    public void addHasDBpediaResource(Object newHasDBpediaResource) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDBPEDIARESOURCE, newHasDBpediaResource);
    }

    public void removeHasDBpediaResource(Object oldHasDBpediaResource) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASDBPEDIARESOURCE, oldHasDBpediaResource);
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
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasEmbeddingCapability
     */
     
    public Collection<? extends String> getHasEmbeddingCapability() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASEMBEDDINGCAPABILITY, String.class);
    }

    public boolean hasHasEmbeddingCapability() {
		return !getHasEmbeddingCapability().isEmpty();
    }

    public void addHasEmbeddingCapability(String newHasEmbeddingCapability) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASEMBEDDINGCAPABILITY, newHasEmbeddingCapability);
    }

    public void removeHasEmbeddingCapability(String oldHasEmbeddingCapability) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASEMBEDDINGCAPABILITY, oldHasEmbeddingCapability);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasOfficialResource
     */
     
    public Collection<? extends Object> getHasOfficialResource() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASOFFICIALRESOURCE, Object.class);
    }

    public boolean hasHasOfficialResource() {
		return !getHasOfficialResource().isEmpty();
    }

    public void addHasOfficialResource(Object newHasOfficialResource) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASOFFICIALRESOURCE, newHasOfficialResource);
    }

    public void removeHasOfficialResource(Object oldHasOfficialResource) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASOFFICIALRESOURCE, oldHasOfficialResource);
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
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#hasSoftwareContent
     */
     
    public Collection<? extends String> getHasSoftwareContent() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASSOFTWARECONTENT, String.class);
    }

    public boolean hasHasSoftwareContent() {
		return !getHasSoftwareContent().isEmpty();
    }

    public void addHasSoftwareContent(String newHasSoftwareContent) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASSOFTWARECONTENT, newHasSoftwareContent);
    }

    public void removeHasSoftwareContent(String oldHasSoftwareContent) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HASSOFTWARECONTENT, oldHasSoftwareContent);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#isContextStandard
     */
     
    public Collection<? extends Boolean> getIsContextStandard() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISCONTEXTSTANDARD, Boolean.class);
    }

    public boolean hasIsContextStandard() {
		return !getIsContextStandard().isEmpty();
    }

    public void addIsContextStandard(Boolean newIsContextStandard) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISCONTEXTSTANDARD, newIsContextStandard);
    }

    public void removeIsContextStandard(Boolean oldIsContextStandard) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_ISCONTEXTSTANDARD, oldIsContextStandard);
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


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#hasEdition
     */
     
    public Collection<? extends Float> getHas_Edition() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_EDITION, Float.class);
    }

    public boolean hasHas_Edition() {
		return !getHas_Edition().isEmpty();
    }

    public void addHas_Edition(Float newHas_Edition) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_EDITION, newHas_Edition);
    }

    public void removeHas_Edition(Float oldHas_Edition) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_EDITION, oldHas_Edition);
    }


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#hasImplementationFormat
     */
     
    public Collection<? extends Object> getHas_Implementation_Format() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_IMPLEMENTATION_FORMAT, Object.class);
    }

    public boolean hasHas_Implementation_Format() {
		return !getHas_Implementation_Format().isEmpty();
    }

    public void addHas_Implementation_Format(Object newHas_Implementation_Format) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_IMPLEMENTATION_FORMAT, newHas_Implementation_Format);
    }

    public void removeHas_Implementation_Format(Object oldHas_Implementation_Format) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_IMPLEMENTATION_FORMAT, oldHas_Implementation_Format);
    }


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#hasOfficialResource
     */
     
    public Collection<? extends URI> getHas_Official_Resource() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_OFFICIAL_RESOURCE, URI.class);
    }

    public boolean hasHas_Official_Resource() {
		return !getHas_Official_Resource().isEmpty();
    }

    public void addHas_Official_Resource(URI newHas_Official_Resource) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_OFFICIAL_RESOURCE, newHas_Official_Resource);
    }

    public void removeHas_Official_Resource(URI oldHas_Official_Resource) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_OFFICIAL_RESOURCE, oldHas_Official_Resource);
    }


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#hasOntology
     */
     
    public Collection<? extends URI> getHas_Ontology() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_ONTOLOGY, URI.class);
    }

    public boolean hasHas_Ontology() {
		return !getHas_Ontology().isEmpty();
    }

    public void addHas_Ontology(URI newHas_Ontology) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_ONTOLOGY, newHas_Ontology);
    }

    public void removeHas_Ontology(URI oldHas_Ontology) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_ONTOLOGY, oldHas_Ontology);
    }


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#hasPages
     */
     
    public Collection<? extends Integer> getHas_Pages() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_PAGES, Integer.class);
    }

    public boolean hasHas_Pages() {
		return !getHas_Pages().isEmpty();
    }

    public void addHas_Pages(Integer newHas_Pages) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_PAGES, newHas_Pages);
    }

    public void removeHas_Pages(Integer oldHas_Pages) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_PAGES, oldHas_Pages);
    }


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#hasPublicationDate
     */
     
    public Collection<? extends Object> getHas_Publication_Date() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_PUBLICATION_DATE, Object.class);
    }

    public boolean hasHas_Publication_Date() {
		return !getHas_Publication_Date().isEmpty();
    }

    public void addHas_Publication_Date(Object newHas_Publication_Date) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_PUBLICATION_DATE, newHas_Publication_Date);
    }

    public void removeHas_Publication_Date(Object oldHas_Publication_Date) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_PUBLICATION_DATE, oldHas_Publication_Date);
    }


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#hasStabilityDate
     */
     
    public Collection<? extends Object> getHas_Stability_Date() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_STABILITY_DATE, Object.class);
    }

    public boolean hasHas_Stability_Date() {
		return !getHas_Stability_Date().isEmpty();
    }

    public void addHas_Stability_Date(Object newHas_Stability_Date) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_STABILITY_DATE, newHas_Stability_Date);
    }

    public void removeHas_Stability_Date(Object oldHas_Stability_Date) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_STABILITY_DATE, oldHas_Stability_Date);
    }


    /* ***************************************************
     * Data Property https://w3id.org/i40/sto#hasStatus
     */
     
    public Collection<? extends Object> getHas_Status() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_STATUS, Object.class);
    }

    public boolean hasHas_Status() {
		return !getHas_Status().isEmpty();
    }

    public void addHas_Status(Object newHas_Status) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_STATUS, newHas_Status);
    }

    public void removeHas_Status(Object oldHas_Status) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HAS_STATUS, oldHas_Status);
    }


}
