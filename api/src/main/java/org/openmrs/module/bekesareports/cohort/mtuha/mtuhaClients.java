package org.openmrs.module.bekesareports.cohort.mtuha;

import org.openmrs.module.bekesareports.cohort.manager.BaseCohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;

import static org.openmrs.module.bekesareports.BekesaReportsConstant.MTUHA_CLIENTS;
import static org.openmrs.module.bekesareports.BekesaReportsConstant.MTUHA_ENCOUNTER_TYPE;

import org.openmrs.api.context.Context;

public class mtuhaClients extends BaseCohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return MTUHA_CLIENTS;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("bekesareports.clients.mtuha.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("bekesareports.clients.mtuha.reportDescription");
	}
	
	@Override
	public CohortDefinition constructCohortDefinition() {
		EncounterCohortDefinition cd = (EncounterCohortDefinition) super.constructCohortDefinition();
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(MTUHA_ENCOUNTER_TYPE));
		return cd;
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
	
	@Override
	public CohortDefinition newInstance() {
		return new EncounterCohortDefinition();
	}
}
