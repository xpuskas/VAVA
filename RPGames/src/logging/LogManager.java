package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import configuration.PropertyManager;

public abstract class LogManager {
	
	private static FileHandler fh = null;
	
	public static Logger createLogger(String className) {
		Logger logger = Logger.getLogger(className);
		giveLogHandlerToLogger(logger);
		return logger;
	}
	
	public static void giveLogHandlerToLogger(Logger logger) {
	   
		if(fh != null) {
			logger.addHandler(fh);
			return;
		}
		
	    try {
	    	
	    	fh = new FileHandler("ExceptionLogs.log", true);
	 	    logger.addHandler(fh);
		    fh.setFormatter(new SimpleFormatter());
		    
	    } catch (SecurityException | IOException e) {
	    		 e.printStackTrace();
	    }
	}
	
	public static void logException(Logger logger, Exception e, boolean printToScreen) {
		
		if(printToScreen) {
			e.printStackTrace();
		}
		
		try {
			logger.log(Level.SEVERE, e.toString(), e);
		} catch (NullPointerException e1) {	
			e1.printStackTrace();
		}
	}

}
