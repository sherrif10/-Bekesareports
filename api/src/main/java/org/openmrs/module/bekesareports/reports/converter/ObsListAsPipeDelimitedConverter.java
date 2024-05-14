package org.openmrs.module.bekesareports.reports.converter;

import java.util.stream.Collectors;

import org.openmrs.Obs;
import org.openmrs.module.reporting.data.converter.DataConverter;

import java.util.List;

public class ObsListAsPipeDelimitedConverter implements DataConverter {
	
	public ObsListAsPipeDelimitedConverter() {
	}
	
	@SuppressWarnings("unchecked")
    @Override
    public Object convert(Object o) {
        List<Obs> obs = (List<Obs>) o;
        if (obs != null) {
            return obs.stream().map(ob -> ob.getValueCoded().getName().getName())
                    .collect(Collectors.joining(" | "));
        }
        return null;
    }
	
	@Override
	public Class<?> getInputDataType() {
		return List.class;
	}
	
	@Override
	public Class<?> getDataType() {
		return String.class;
	}
	
}
