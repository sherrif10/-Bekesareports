package org.openmrs.module.bekesareports.reports.converter;

import org.openmrs.module.reporting.data.converter.ExistenceConverter;
import org.springframework.stereotype.Component;

@Component
public class BekesaDataConverter {
	
	public ExistenceConverter getObsValueCodedExistsConverter() {
		return new ExistenceConverter("Yes", null);
	}
	
	public BekesaDataConverter getObsValuesCodedPipeDelimitedConverter() {
		return new BekesaDataConverter();
	}
}
