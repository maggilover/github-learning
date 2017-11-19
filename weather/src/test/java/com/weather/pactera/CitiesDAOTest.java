package com.weather.pactera;

import static org.junit.Assert.*;

import org.junit.Test;

import com.weather.pactera.CitiesDAO;

public class CitiesDAOTest {

	@Test
	public void testLoadOnStartup() {
		CitiesDAO citiesDAO = CitiesDAO.getInstance();
		assertTrue(citiesDAO.read().size() > 0);
	}

}
