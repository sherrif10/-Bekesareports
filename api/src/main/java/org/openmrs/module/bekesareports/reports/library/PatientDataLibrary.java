package org.openmrs.module.bekesareports.reports.library;

import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.definition.library.BaseDefinitionLibrary;
import org.springframework.stereotype.Component;

@Component
public class PatientDataLibrary extends BaseDefinitionLibrary<PatientDataDefinition> {
	
	@Override
	public Class<? super PatientDataDefinition> getDefinitionType() {
		return PatientDataDefinition.class;
	}
	
	@Override
	public String getKeyPrefix() {
		return "bakesa.patientData.mtuha.";
	}
	
	public PersonDataDefinition getName() {
		return new PreferredNameDataDefinition();
	}
	
	public AgeDataDefinition getAge() {
		return new AgeDataDefinition();
	}
	
	public GenderDataDefinition getGender() {
		return new GenderDataDefinition();
	}
	
	public CohortDefinition getAgeCohort(Integer minAge, Integer maxAge) {
		AgeCohortDefinition cd = new AgeCohortDefinition();
		cd.setMinAge(minAge);
		cd.setMaxAge(maxAge);
		return cd;
	}
	
	public GenderCohortDefinition getPatientsByGenderCohort(char g) {
		GenderCohortDefinition gcd = new GenderCohortDefinition();
		if (g == 'M') {
			gcd.setMaleIncluded(true);
		} else if (g == 'F') {
			gcd.setFemaleIncluded(true);
		}
		return gcd;
	}
	
}
