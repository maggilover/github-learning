package com.weather.pactera;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for the weather app which will map all requests.
 * 
 * @author Hima Bindu
 * 
 */
@Controller
public class WeatherController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/CityWeather", method = RequestMethod.GET)
	public String weather( Model model) {
		return "home";
	}
	
	/**
	 * Gets weather info for all cities.Simply selects the home view to render by returning its name.
	 */
	@ResponseBody
	@RequestMapping(value = "/CityWeather/{city:.+}", method = RequestMethod.GET)
	public String weather(@PathVariable("city") String city) {
		String responseText = null;
		if(city != null) {
			GetCityWeatherServiceResponse response = (GetCityWeatherServiceResponse)
					new GetCityWeatherService().service(new GetCityWeatherServiceRequest(city));
			responseText = response.getJsonResponse();
		}
		return responseText;
	}
	
	/**
	 * Gets weather info for all cities.Simply selects the home view to render by returning its name.
	 */
	@ResponseBody
	@RequestMapping(value = "/CityAll", method = RequestMethod.GET)
	public String cities() {

		GetAllCityServiceResponse response = (GetAllCityServiceResponse)new GetAllCityService().service(new ServiceRequest() {
		});
		
		return response.getResponse();
	}

	
}
