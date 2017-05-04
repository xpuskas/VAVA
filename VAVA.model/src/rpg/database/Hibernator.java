package rpg.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class Hibernator {

	private static SessionFactory sessionFactory = null;
	
	public static void initialize() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	public static SessionFactory getSessionFactory() {
		if(sessionFactory==null || sessionFactory.isClosed()) {
			initialize();
		}
		return sessionFactory;
	}
}
