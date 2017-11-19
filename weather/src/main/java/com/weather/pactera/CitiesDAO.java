package com.weather.pactera;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * A dao class to Create, Read, Delete cities from a data source
 * file.
 * <p>
 * This class will also load all the cities on startup.
 * 
 * @author Hima Bindu
 *
 */
public class CitiesDAO {

	private static final Logger logger = LoggerFactory.getLogger(CitiesDAO.class);
	
	private final List<String> citiesList = new ArrayList<String>();
	
	public static final String FILE_LOCATION = "cities.db";
	
	private static final CitiesDAO instance = new CitiesDAO();
	
	public static final CitiesDAO getInstance() {
		return instance;
	}
	
	private CitiesDAO() {
		loadOnStartUp();
	}
	
	private void loadOnStartUp() {
		Scanner citiesScanner = null;
		try {
			File citiesFile = new File(FILE_LOCATION);
			if(!citiesFile.exists()) {
				citiesFile.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(citiesFile));
				writer.write("Sydney");
				writer.newLine();
				writer.write("Melbourne");
				writer.newLine();
				writer.write("Wollongong");
				writer.flush();
				writer.close();
			}
			citiesScanner = new Scanner(citiesFile);
			while(citiesScanner.hasNextLine()) {
				create(citiesScanner.nextLine());
			}
		} catch (IOException e) {
			logger.error("Error loading cities from " , e);
		} finally {
			if(citiesScanner != null) citiesScanner.close();
		}
	}
	
	public void create(String city) {
		citiesList.add(city);
	}
	
	public void delete(String city) {
		citiesList.remove(city);
	}
	
	public List<String> read() {
		return this.citiesList;
	}
	
}
