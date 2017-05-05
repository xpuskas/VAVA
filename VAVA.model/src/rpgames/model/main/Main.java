package rpgames.model.main;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Month;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import rpg.database.Hibernator;
import rpgames.model.DeveloperGame;
import rpgames.model.Game;
import rpgames.model.OfficialGame;
import rpgames.model.UserAccount;
import rpgames.model.ViewGameByUser;

public abstract class Main {

	public static void main(String[] args) {
		OfficialGame game1 = new OfficialGame();
		game1.setName("Skyrim");
		OfficialGame game2 = new OfficialGame();
		game2.setName("Oblivion");
		OfficialGame game3 = new OfficialGame();
		game2.setName("Morrowind");
		
		UserAccount user1 = new UserAccount();
		user1.setName("John");
		
		ViewGameByUser vgbu1 = new ViewGameByUser();
		vgbu1.setGame(game1);
		vgbu1.setViewer(user1);
		vgbu1.setViewed(new Date());
		
		Date date2 = new Date();
		date2.setYear(2015);
		ViewGameByUser vgbu2 = new ViewGameByUser();
		vgbu2.setGame(game1);
		vgbu2.setViewer(user1);
		vgbu2.setViewed(date2);
		
		Date date3 = new Date();
		date3.setYear(2016);
		ViewGameByUser vgbu3 = new ViewGameByUser();
		vgbu3.setGame(game2);
		vgbu3.setViewer(user1);
		vgbu3.setViewed(date3);
		
		SessionFactory sessionFactory = Hibernator.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(game1);
		session.save(game2);
		session.save(game3);
		session.save(user1);
		session.save(vgbu1);
		session.save(vgbu2);
		session.save(vgbu3);
		
		session.getTransaction().commit();
		session.close();
		
		/*List<Game> viewedGames = user1.fetchLastViewsPaged(10);
		System.out.println(viewedGames.size());
		for(int i = 0; i<viewedGames.size(); i++) {
			System.out.println(viewedGames.get(i).getName());
		}*/
	}
	
	public static byte[] readImage(String name) {
	    BufferedImage image = null;
		
	    try {
		image =  ImageIO.read(new File("foto.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] imageInByte = null;
	    try {
			ImageIO.write( image, "jpg", baos );
		    baos.flush();
		    imageInByte = baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally {
	    	try {
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    return imageInByte;
	}
	//read image, store it to empty game, put it into database, load it from there and write it out
	public static void managePicture(String name) {
		byte[] image = readImage(name);
		
		System.out.println(image.length);
		
		DeveloperGame game = new DeveloperGame();
		game.setImage(image);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(game);
	
		session.getTransaction().commit();
		session.close();
		
		session = null;
		game = null;
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		
		game = session.get(DeveloperGame.class, (long)1);
		session.getTransaction().commit();
		session.close();
		
		System.out.println(game.getImage().length);
		
		OutputStream out = null;

		try {
		    out = new BufferedOutputStream(new FileOutputStream("obrazok.jpg"));
		    out.write(game.getImage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public List<OfficialGame> mostRecentGames(int count) {
		
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<OfficialGame> criteria = builder.createQuery( OfficialGame.class );
		Root<OfficialGame> root = criteria.from(OfficialGame.class );
		criteria.select( root );
		
		criteria.orderBy(builder.desc(root.get("added")));
		
		Query query = entityManager.createQuery( criteria );
		query.setMaxResults(count);
		
		List<OfficialGame> games = query.getResultList();

		return games;
	}
}
