package org.openmrs.module.bekesareports.cohort.util;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.api.CohortTypeService;

import static org.openmrs.module.bekesareports.cohort.util.CohortConstants.*;

public class MtuhaStaticCohortsUtil {
	
	public static void setupCohortType() {
		CohortType hts = new CohortType();
		hts.setUuid(MTUHA_COHORT_TYPE_UUID);
		hts.setName(MTUHA_COHORT_TYPE_NAME);
		hts.setDescription(MTUHA_COHORT_TYPE_DESCRIPTION);
		CohortTypeService cohortTypeService = Context.getService(CohortTypeService.class);
		
		CohortType existing = cohortTypeService.getByUuid(MTUHA_COHORT_TYPE_UUID);
		if (existing != null) {
			hts.setId(existing.getId());
			try {
				Context.evictFromSession(existing);
			}
			catch (IllegalArgumentException e) {
				
			}
		}
		cohortTypeService.saveCohortType(hts);
	}
	
	public static void setupHtsCohorts() {
		setupCohortType();
		setupHtsCohort(MTUHA_PRE_TEST_COHORT_UUID, MTUHA_PRE_TEST_COHORT_NAME);
		setupHtsCohort(MTUHA_WAITING_FOR_TEST_COHORT_UUID, MTUHA_WAITING_FOR_TEST_COHORT_NAME);
		setupHtsCohort(MTUHA_POST_TEST_COHORT_UUID, MTUHA_POST_TEST_COHORT_NAME);
	}
	
	public static void setupHtsCohort(String uuid, String name) {
		CohortM cm = new CohortM();
		cm.setUuid(uuid);
		cm.setName(name);
		CohortService cohortService = Context.getService(CohortService.class);
		CohortTypeService cohortTypeService = Context.getService(CohortTypeService.class);
		cm.setCohortType(cohortTypeService.getByUuid(MTUHA_COHORT_TYPE_UUID));
		
		CohortM existing = cohortService.getCohortByUuid(uuid);
		if (existing != null) {
			cm.setId(existing.getId());
			try {
				Context.evictFromSession(existing);
			}
			catch (IllegalArgumentException e) {
				
			}
		}
		cohortService.saveCohort(cm);
	}
}
