package org.openmrs.module.bekesareports.cohort.mtuha;

import org.openmrs.module.bekesareports.cohort.manager.BaseCohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;

import static org.openmrs.module.bekesareports.BekesaReportsConstant.CHOLERA_CLIENTS;
import static org.openmrs.module.bekesareports.BekesaReportsConstant.CHOLERA_ENCOUNTER_TYPE;

import org.openmrs.api.context.Context;

public class ChorelaClients extends BaseCohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return CHOLERA_CLIENTS;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("bekesareports.clients.chorela.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("bekesareports.clients.cholera.reportDescription");
	}
	
	@Override
	public CohortDefinition constructCohortDefinition() {
		EncounterCohortDefinition cd = (EncounterCohortDefinition) super.constructCohortDefinition();
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(CHOLERA_ENCOUNTER_TYPE));
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
