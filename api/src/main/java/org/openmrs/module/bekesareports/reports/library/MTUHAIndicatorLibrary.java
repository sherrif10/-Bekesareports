package org.openmrs.module.bekesareports.reports.library;

import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.indicator.BaseIndicator;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MTUHAIndicatorLibrary extends BaseIndicator {
	
	@Autowired
	EncountersForPatientDataLibrary efpdl;
	
	public Mapped<CohortIndicator> getPatientsWithMTUHAIndicator(Integer minAge, Integer maxAge, char gender, char res) {
		CohortIndicator ci = new CohortIndicator();
		ci.setCohortDefinition(efpdl.getPatientsWithMTUHACohort(minAge, maxAge, gender, res));
		ci.setType(CohortIndicator.IndicatorType.COUNT);
		return Mapped.mapStraightThrough(ci);
	}
	
}
