package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import logging.LogManager;

public class PropertyManager {

	private static Properties props = null;
	private static final Logger LOGGER = LogManager.createLogger(PropertyManager.class.getName());
	
	public static Properties getProps() {
		if(props == null) {
			props = new Properties();
			
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream("config.properties");
			} 
			catch (FileNotFoundException e) {
				LogManager.logException(LOGGER, e, true);
			}
			try {
				props.load(fis);
			} 
			catch (IOException e) {
				LogManager.logException(LOGGER, e, true);
			}  
			
		}
	
	return props;
	
	}
}
