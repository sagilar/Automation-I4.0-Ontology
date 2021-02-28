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
 * Source Class: DefaultFoaf:Person <br>
 * @version generated on Fri Feb 26 14:35:08 COT 2021 by santiago
 */
public class DefaultFoaf:Person extends WrappedIndividualImpl implements Foaf:Person {

    public DefaultFoaf:Person(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#generates
     */
     
    public Collection<? extends Data> getGenerates() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_GENERATES,
                                               DefaultData.class);
    }

    public boolean hasGenerates() {
	   return !getGenerates().isEmpty();
    }

    public void addGenerates(Data newGenerates) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_GENERATES,
                                       newGenerates);
    }

    public void removeGenerates(Data oldGenerates) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_GENERATES,
                                          oldGenerates);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#interactsWith
     */
     
    public Collection<? extends Actor> getInteractsWith() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_INTERACTSWITH,
                                               DefaultActor.class);
    }

    public boolean hasInteractsWith() {
	   return !getInteractsWith().isEmpty();
    }

    public void addInteractsWith(Actor newInteractsWith) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_INTERACTSWITH,
                                       newInteractsWith);
    }

    public void removeInteractsWith(Actor oldInteractsWith) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_INTERACTSWITH,
                                          oldInteractsWith);
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
     * Object Property http://www.semanticweb.org/santiago/Automation-I4.0-Ontology#receives
     */
     
    public Collection<? extends Data> getReceives() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_RECEIVES,
                                               DefaultData.class);
    }

    public boolean hasReceives() {
	   return !getReceives().isEmpty();
    }

    public void addReceives(Data newReceives) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_RECEIVES,
                                       newReceives);
    }

    public void removeReceives(Data oldReceives) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_RECEIVES,
                                          oldReceives);
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