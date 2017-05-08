package main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
	private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );
	
	public static void main(String args[]) {
		
		Locale slovak = new Locale("sk", "SLOVAKIA");
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("main.label", slovak);
		 
	    String name = resourceBundle.getString("NAME");
	    String password=resourceBundle.getString("PASSWORD");
	 
	    System.out.println(name);
	    System.out.println(password);
	    
	    FileHandler fh = null;
	    try {
	    	 fh=new FileHandler("loggerExample.log", true);
	    	 } catch (SecurityException | IOException e) {
	    		 e.printStackTrace();
	    	 }
	    LOGGER.addHandler(fh);
	    fh.setFormatter(new SimpleFormatter());
	    
	    try {
	    	throw new NullPointerException();
	    } catch(Exception e) {
	    	LOGGER.log(Level.SEVERE, e.toString(), e);
	    }
	}
}
