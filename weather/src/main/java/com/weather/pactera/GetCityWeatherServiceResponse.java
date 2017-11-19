package com.weather.pactera;

/**
 * 
 * JSON Response containing city weather information for GET request 
 * of type CityWeather.
 * 
 * @author Hima Bindu
 *
 */
public class GetCityWeatherServiceResponse implements ServiceResponse{

	private final String jsonResponse;
	
	public GetCityWeatherServiceResponse(String response) {
		this.jsonResponse = response;
	}
	
	public String getJsonResponse() {
		return this.jsonResponse;
	}
}
