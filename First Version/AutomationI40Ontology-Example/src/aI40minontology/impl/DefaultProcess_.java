package aI40minontology.impl;

import aI40minontology.*;


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
 * Source Class: DefaultProcess_ <br>
 * @version generated on Sun Feb 10 20:58:15 COT 2019 by Santiago
 */
public class DefaultProcess_ extends WrappedIndividualImpl implements Process_ {

    public DefaultProcess_(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://www.semanticweb.org/Automation-I4.0-Ontology#hasAssociatedModel
     */
     
    public Collection<? extends DiscreteDynamicsModel> getHasAssociatedModel() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASASSOCIATEDMODEL,
                                               DefaultDiscreteDynamicsModel.class);
    }

    public boolean hasHasAssociatedModel() {
	   return !getHasAssociatedModel().isEmpty();
    }

    public void addHasAssociatedModel(DiscreteDynamicsModel newHasAssociatedModel) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASASSOCIATEDMODEL,
                                       newHasAssociatedModel);
    }

    public void removeHasAssociatedModel(DiscreteDynamicsModel oldHasAssociatedModel) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASASSOCIATEDMODEL,
                                          oldHasAssociatedModel);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/Automation-I4.0-Ontology#isPerformedBy
     */
     
    public Collection<? extends Service> getIsPerformedBy() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISPERFORMEDBY,
                                               DefaultService.class);
    }

    public boolean hasIsPerformedBy() {
	   return !getIsPerformedBy().isEmpty();
    }

    public void addIsPerformedBy(Service newIsPerformedBy) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISPERFORMEDBY,
                                       newIsPerformedBy);
    }

    public void removeIsPerformedBy(Service oldIsPerformedBy) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISPERFORMEDBY,
                                          oldIsPerformedBy);
    }


}