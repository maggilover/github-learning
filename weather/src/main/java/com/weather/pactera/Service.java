package com.weather.pactera;

/**
 * 
 * A service interface. Implementating classes should perform
 * some sort of service.
 * 
 * @see ServiceRequest
 * @see ServiceResponse
 * 
 * @author Hima Bindu
 *
 */
public interface Service {

	
	public ServiceResponse service(ServiceRequest request);
}
