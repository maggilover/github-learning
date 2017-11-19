package com.weather.pactera;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetAllCityService implements Service {

	private static final Logger logger = LoggerFactory.getLogger(GetAllCityService.class);
	
	@Override
	public ServiceResponse service(ServiceRequest request) {

		List<String> citiesList = CitiesDAO.getInstance().read();
		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			return new GetAllCityServiceResponse("{\"citiesList\":" + jsonMapper.
					writeValueAsString(citiesList) + "}");
		} catch (JsonGenerationException e) {
			logger.error("Error Getting All Cities, Type JSON Generation", e);
		} catch (JsonMappingException e) {
			logger.error("Error Getting All Cities, Type JSON Mapping", e);
		} catch (IOException e) {
			logger.error("Error Getting All Cities, Type IO", e);
		} 
		return new GetAllCityServiceResponse("");
	}
}

