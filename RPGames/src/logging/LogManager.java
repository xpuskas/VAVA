package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * Abstract class that provides set of static methods used for creating {@link java.util.logging.Logger}
 * object for the requesting class, tying simple file handler to it and logging exceptions.
 */
public abstract class LogManager {
	
	private static FileHandler fh = null;
	
	/**
	 * Creates {@link java.util.logging.Logger} object bound to the class
	 * which requested its creation. Also ties it to the shared 
	 * @param className - name of class which requested to have its logger created
	 * @return {@link java.util.logging.Logger} object bound to specified class
	 */
	public static Logger createLogger(String className) {
		Logger logger = Logger.getLogger(className);
		giveLogHandlerToLogger(logger);
		return logger;
	}
	/**
	 * Ties log handler to logger. All the classes currently share the same file for logging.
	 * @param logger - {@link java.util.logging.Logger} object which needs file handler
	 */
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
	/**
	 * Logs the exception and prints its stack trace to screen using {@link }}.
	 * This should happen in all the catch blocks throughout the project.
	 * @param logger - the {@link java.util.logging.Logger} object to be used for logging
	 * @param e - {@link java.lang.Exception} object to be logged
	 * @param printToScreen - <code>boolean</code> which if is <code>true</code>,
	 * the exception stack trace gets printed on screen
	 */
	public static void logException(Logger logger, Exception e, boolean printToScreen) {
		synchronized(LogManager.class) {
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

}
