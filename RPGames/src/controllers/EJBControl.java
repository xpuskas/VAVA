package controllers;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import configuration.PropertyManager;
import fetcher.FetcherBeanRemote;
import logging.LogManager;
import updater.UpdaterBeanRemote;

public abstract class EJBControl {
	
private static Context context = null;
	private static final Logger LOGGER = LogManager.createLogger(EJBControl.class.getName());

	public static void initialize(){
		try {
			context = EJBContext.createRemoteEjbContext(PropertyManager.getProps().getProperty("server"), PropertyManager.getProps().getProperty("port"));
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
		}
	}
	
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
