package org.openmrs.module.bekesareports.reports.library;

import org.openmrs.module.reporting.data.patient.definition.EncountersForPatientDataDefinition;
import org.openmrs.module.reporting.definition.library.BaseDefinitionLibrary;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.springframework.beans.factory.annotation.Autowired;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;

public class EncountersForPatientDataLibrary extends BaseDefinitionLibrary<EncountersForPatientDataDefinition> {
	
	@Autowired
	PatientDataLibrary pdl;
	
	@Autowired
	EncounterDataLibrary edl;
	
	@Override
	public Class<? super EncountersForPatientDataDefinition> getDefinitionType() {
		return EncountersForPatientDataDefinition.class;
	}
	
	@Override
	public String getKeyPrefix() {
		return "bakesa.patientEncounterData.mtuha.";
	}
	
	public Mapped<CompositionCohortDefinition> getPatientsWithMTUHACohort(Integer minAge, Integer maxAge, char gender,
	        char res) {
		CompositionCohortDefinition ccd = new CompositionCohortDefinition();
		ccd.addSearch("agedBetween", Mapped.mapStraightThrough(pdl.getAgeCohort(minAge, maxAge)));
		ccd.addSearch("isGender", Mapped.mapStraightThrough(pdl.getPatientsByGenderCohort(gender)));
		ccd.addSearch("hasHtsEncounter", Mapped.mapStraightThrough(edl.getHtsEncountersCohort(res)));
		ccd.setCompositionString("agedBetween and isGender and hasMTUHAEncounter");
		return Mapped.mapStraightThrough(ccd);
	}
	
}
