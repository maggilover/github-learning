package com.weather.pactera;

/**
 * 
 * A service request to get city weather.
 * 
 * @author Hima Bindu
 *
 */
public class GetCityWeatherServiceRequest implements ServiceRequest {

	private final String city;
	
	public GetCityWeatherServiceRequest(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
	}
}
