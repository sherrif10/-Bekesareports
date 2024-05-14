package org.openmrs.module.bekesareports.reports.library;

import org.openmrs.Concept;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.module.reporting.cohort.definition.EncounterWithCodedObsCohortDefinition;
import org.openmrs.module.reporting.data.encounter.definition.EncounterDataDefinition;
import org.openmrs.module.reporting.data.encounter.definition.ObsForEncounterDataDefinition;
import org.openmrs.module.reporting.definition.library.BaseDefinitionLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collections;
import static org.openmrs.module.bekesareports.BekesaReportsConstant.*;

@Component
public class EncounterDataLibrary extends BaseDefinitionLibrary<EncounterDataDefinition> {
	
	@Autowired
	ConceptService conceptService;
	
	@Autowired
	EncounterService encounterService;
	
	@Override
	public Class<? super EncounterDataDefinition> getDefinitionType() {
		return EncounterDataDefinition.class;
	}
	
	@Override
	public String getKeyPrefix() {
		return "ohri.encounterData.hts.";
	}
	
	public ObsForEncounterDataDefinition getObsValue(String conceptUuid) {
		ObsForEncounterDataDefinition ofedd = new ObsForEncounterDataDefinition();
		ofedd.setQuestion(conceptService.getConceptByUuid(conceptUuid));
		ofedd.setSingleObs(true);
		return ofedd;
	}
	
	public ObsForEncounterDataDefinition getObsValue(String conceptUuid, String answerUuid) {
		ObsForEncounterDataDefinition ofedd = new ObsForEncounterDataDefinition();
		ofedd.setQuestion(conceptService.getConceptByUuid(conceptUuid));
		Concept answer = conceptService.getConceptByUuid(answerUuid);
		ofedd.setAnswers(Collections.singletonList(answer));
		ofedd.setSingleObs(true);
		return ofedd;
	}
	
	public ObsForEncounterDataDefinition getObsValues(String conceptUuid) {
		ObsForEncounterDataDefinition ofedd = new ObsForEncounterDataDefinition();
		ofedd.setQuestion(conceptService.getConceptByUuid(conceptUuid));
		ofedd.setSingleObs(false);
		return ofedd;
	}
	
	public EncounterWithCodedObsCohortDefinition getHtsEncountersCohort(char c) {
		EncounterWithCodedObsCohortDefinition ecd = new EncounterWithCodedObsCohortDefinition();
		ecd.addEncounterType(encounterService.getEncounterTypeByUuid(MTUHA_ENCOUNTER_TYPE));
		ecd.addEncounterType(encounterService.getEncounterTypeByUuid(MTUHA_RETROSPECTIVE_ENCOUNTER_TYPE));
		ecd.setConcept(conceptService.getConceptByUuid(FINAL_HIV_RESULT));
		if (c == '+') {
			ecd.setIncludeCodedValues(Collections.singletonList(conceptService.getConceptByUuid(FEMALE)));
		} else if (c == '-') {
			ecd.setIncludeCodedValues(Collections.singletonList(conceptService.getConceptByUuid(MALE)));
		}
		return ecd;
	}
	
}
