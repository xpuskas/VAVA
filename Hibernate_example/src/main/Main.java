package main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import rpg.database.Hibernator;
import rpg.model.UserDetails;

public abstract class Main {
	public static void main(String[] args) {
		Hibernator.initialize();
		
		create();
		update();
		delete();
		query();
		
		Hibernator.getSessionFactory().close();
	}
	public static void create() {
		UserDetails user = new UserDetails("Janko");
		UserDetails user1 = new UserDetails("Ferko");
		UserDetails user2 = new UserDetails("Valentino Vraniè");
		
			SessionFactory sessionFactory = Hibernator.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(user);
			session.save(user1);
			session.save(user2);
			
			session.getTransaction().commit();
			session.close();
	}
	public static void update() {
		SessionFactory sessionFactory = Hibernator.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		UserDetails user = session.get(UserDetails.class, 1);
		user.setName("Janko Vraniè");
		
		session.getTransaction().commit();
		session.close();
	}
	public static void delete() {
		SessionFactory sessionFactory = Hibernator.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		UserDetails user = session.get(UserDetails.class, 2);
		session.delete(user);
		
		session.getTransaction().commit();
		session.close();
	}
	public static void query() {
		
		
		SessionFactory sessionFactory = Hibernator.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Example example = Example.create(new UserDetails("%Vraniè")).enableLike();
		
		Criteria criteria = session.createCriteria(UserDetails.class);
		criteria.add(example);
		
		List<UserDetails> result = (List<UserDetails>) criteria.list();
		session.getTransaction().commit();
		session.close();
		
		System.out.println("Query returned " + result.size() + "records. They are : ");
		for(UserDetails ud : result)
			System.out.println(ud.getID() + " : " + ud.getName());
	}
}