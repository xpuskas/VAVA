package application;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;

import configuration.PropertyManager;
import fetcher.FetcherBeanRemote;
import logging.LogManager;
import updater.UpdaterBeanRemote;


/**
 * Class for creating the persistence context and providing access to the remote EJB beans.
 *
 */
public abstract class EJBControl {
	
private static Context context = null;
	private static final Logger LOGGER = LogManager.createLogger(EJBControl.class.getName());

	
	/**
	 * Initializes the persistence context.
	 */
	public static void initialize(){
		try {
			context = EJBContext.createRemoteEjbContext(PropertyManager.getProps().getProperty("server"), PropertyManager.getProps().getProperty("port"));
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
		}
	}
	
	
	/**
	 * Gets an instance of FetcherBeanRemote. In case it hasn't been initialized yet,
	 * it also initializes it with the persistence context.
	 * @return FetcherBeanRemote instance
	 */
	public static FetcherBeanRemote getFetcher() {
		
		if(context == null) {
			initialize();
		}
		
		try {
			return (FetcherBeanRemote)context.lookup(PropertyManager.getProps().getProperty("ejb_fetcher"));
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
			return null;
		}
	}
	
	
	/**
	 * Gets an instance of UpdaterBeanRemote. In case it hasn't been initialized yet,
	 * it also initializes it with the persistence context.
	 * @return UpdaterBeanRemote instance
	 */
	public static UpdaterBeanRemote getUpdater() {
		
		if(context == null) {
			initialize();
		}
		
		try {
			return (UpdaterBeanRemote)context.lookup(PropertyManager.getProps().getProperty("ejb_updater"));
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
			return null;
		}
	}
}
