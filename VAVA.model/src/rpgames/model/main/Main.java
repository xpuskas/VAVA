package rpgames.model.main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernatePersistenceProvider;


import rpg.database.Hibernator;
import rpgames.model.Article;
import rpgames.model.Comment;
import rpgames.model.DeveloperGame;
import rpgames.model.Game;
import rpgames.model.Genre;
import rpgames.model.OfficialGame;
import rpgames.model.RatingOfGame;
import rpgames.model.Review;
import rpgames.model.Screenshot;
import rpgames.model.UserAccount;
import rpgames.model.ViewGameByUser;

public class Main {

	public static void main(String[] args) {
		byte[] b = {5, 8};
		Image image = new ImageIcon(b).getImage();
		
		/*OfficialGame game1 = new OfficialGame();
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
		session.close();*/
		
		/*List<Game> viewedGames = user1.fetchLastViewsPaged(10);
		System.out.println(viewedGames.size());
		for(int i = 0; i<viewedGames.size(); i++) {
			System.out.println(viewedGames.get(i).getName());
		}*/
		Main main = new Main();
		
		OfficialGame game = new OfficialGame();
		game.setName("Morrowind");
		
		Article article = new Article();
		article.setText("Táto hra je božská");
		article.setGame(game);
		
		Review review = new Review();
		review.setText("Málo avantgardné");
		review.setGame(game);
		
		game.getArticlesAbout().add(article);
		game.getArticlesAbout().add(review);
		
		Comment comment1 = new Comment();
		comment1.setPosted(new Date());
		comment1.setGame(game);
		comment1.setText("Played this for a long time. It is gonna get longer");
		
		Comment comment2 = new Comment();
		comment2.setPosted(new Date());
		comment2.setGame(game);
		comment2.setText("Yo momma so fat dark brotherhood needs two contracts");
		
		game.getComments().add(comment1);
		game.getComments().add(comment2);
		game.setDescription("èo to je");
		
		
		SessionFactory sessionFactory = Hibernator.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(game);
		
		session.getTransaction().commit();
		session.close();
		
		sessionFactory = Hibernator.getSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		//List<String> articles = main.gameArticles(game);
		List<Comment> comments = game.getComments();
		
		session.getTransaction().commit();
		session.close();
		
		for(Comment c : comments)
			System.out.println(c.getText());
		
		//System.out.println(articles.size());
		
	}
	/*
	public static byte[] readImage(Image image) {
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
	}*/
	//read image, store it to empty game, put it into database, load it from there and write it out
	/*public static void managePicture(String name) {
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
	}*/
	public List<OfficialGame> mostRecentGames(int count) {;
		
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<OfficialGame> criteria = builder.createQuery( OfficialGame.class );
		Root<OfficialGame> root = criteria.from(OfficialGame.class );
		criteria.select( root );
		
		criteria.orderBy(builder.desc(root.get("added")));
		
		Query query = entityManager.createQuery( criteria );
		query.setMaxResults(count);
		
		List<OfficialGame> games = (List<OfficialGame>) query.getResultList();

		return games;
	}
	public List<String> articleNamesByGame(Game game) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		
		Root<Article> root = criteria.from(Article.class);
		Join<Article, Game> gameJoin = root.join("game");
		
		criteria.select(root.get("title"));
		criteria.where(builder.equal(gameJoin.get("name"), game.getName()));
		
		List<String> articleNames = (List<String>) entityManager.createQuery( criteria ).getResultList();
		
		return articleNames;
	}
	public Article getArticleByTitle(String title) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Article> criteria = builder.createQuery( Article.class );
		
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		
		criteria.where(builder.equal(root.get("title"), title));
		
		List<Article> articles = (List<Article>) entityManager.createQuery( criteria ).getResultList();
		
		return articles.size() > 0 ? articles.get(0) : null;	
	}
	//Pages are to be indexed 0,1,2,....
	public List<Comment> getPagedCommentsForGame(Game game, int pagination, int page) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Comment> criteria = builder.createQuery( Comment.class );
		
		Root<Comment> root = criteria.from(Comment.class);
		Join<Comment, Game> commentJoin = root.join("game");
		
		criteria.select(root);
		criteria.orderBy(builder.desc(root.get("posted")));
		criteria.where(builder.equal(commentJoin.get("name"), game.getName()));
		
		Query query = entityManager.createQuery( criteria );
		query.setMaxResults(pagination);
		query.setFirstResult(pagination * page);
		
		List<Comment> comments = (List<Comment>) query.getResultList();
		
		return comments;
	}
	//Pages are to be indexed 0,1,2,....
	public List<Screenshot> getPagedScreenshotsForGame(Game game, int pagination, int page) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Screenshot> criteria = builder.createQuery( Screenshot.class );
		
		Root<Screenshot> root = criteria.from(Screenshot.class);
		Join<Screenshot, Game> commentJoin = root.join("game");
		
		criteria.select(root);
		criteria.orderBy(builder.desc(root.get("screenshotID")));
		criteria.where(builder.equal(commentJoin.get("name"), game.getName()));
		
		Query query = entityManager.createQuery( criteria );
		query.setMaxResults(pagination);
		query.setFirstResult(pagination * page);
		
		List<Screenshot> screenshots = (List<Screenshot>) query.getResultList();
		
		return screenshots;
	}
	public List<OfficialGame> getOfficialGamesAddedByUser(UserAccount user) {
		return user.getAddedGames();
	}
	public List<DeveloperGame> getDeveloperGamesAddedByUser(UserAccount user) {
		return user.getUsersGames();
	}
	public UserAccount getUserAccountByName(String name) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<UserAccount> criteria = builder.createQuery( UserAccount.class );
		
		Root<UserAccount> root = criteria.from(UserAccount.class);
		criteria.where(builder.equal(root.get("name"), name));
		
		List<UserAccount> users = (List<UserAccount>) entityManager.createQuery( criteria ).getResultList();
		
		return users.size() > 0 ? users.get(0) : null;
	}
	public OfficialGame getOfficialGameByName(String name) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<OfficialGame> criteria = builder.createQuery( OfficialGame.class );
		
		Root<OfficialGame> root = criteria.from(OfficialGame.class);
		criteria.where(builder.equal(builder.upper(root.get("name")), name.toUpperCase()));
		
		List<OfficialGame> games = (List<OfficialGame>) entityManager.createQuery( criteria ).getResultList();
		
		return games.size() > 0 ? games.get(0) : null;
	}
	public DeveloperGame getDeveloperGameByName(String name) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<DeveloperGame> criteria = builder.createQuery( DeveloperGame.class );
		
		Root<DeveloperGame> root = criteria.from(DeveloperGame.class);
		criteria.where(builder.equal(builder.upper(root.get("name")), name.toUpperCase()));
		
		List<DeveloperGame> games = (List<DeveloperGame>) entityManager.createQuery( criteria ).getResultList();
		
		return games.size() > 0 ? games.get(0) : null;
	}
	public List<String> getAllGenres() {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		
		Root<Genre> root = criteria.from(Genre.class);
		criteria.select(root.get("name"));
		
		List<String> result = (List<String>) entityManager.createQuery( criteria ).getResultList();
		
		return result;
	}
	public List<String> getLastViewedGamesByUser(UserAccount user, int limit) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Game> criteria = builder.createQuery( Game.class );
		
		Root<ViewGameByUser> root = criteria.from(ViewGameByUser.class);
		Join<ViewGameByUser, UserAccount> userJoin = root.join("viewer");
		Join<ViewGameByUser, Game> gameJoin = root.join("game");
		
		criteria.select(gameJoin.get("name"));
		criteria.distinct(true);
		criteria.where(builder.equal(userJoin.get("userID"), user.getUserID()));
		criteria.orderBy(builder.desc(root.get("viewed")));
		
		Query query = entityManager.createQuery( criteria );
		query.setMaxResults(limit);

		List<String> games = (List<String>) query.getResultList();
		
		return games;
	}
	public List<String> getGameNamesByFiltration(List<String> parameters, double highRankLimit) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		
		Root<Game> root = criteria.from(Game.class);
		
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//Name of the game
		if(parameters.get(0)!=null) {
			//Expression<Boolean> name = builder.and(builder.equal(root.get("name"), parameters.get(0)));
			conditions.add(builder.equal(builder.upper(root.get("name")), parameters.get(0).toUpperCase()));
		}
		//Genre
		if(parameters.get(1)!=null) {
			Join<Game, Genre> genreJoin = root.join("genre");
			conditions.add(builder.equal(builder.upper(genreJoin.get("name")), parameters.get(0).toUpperCase()));
		}
		//Studio
		if(parameters.get(2)!=null) {
			conditions.add(builder.equal(builder.upper(root.get("studio")), parameters.get(2).toUpperCase()));
		}
		//Year of release
		if(parameters.get(3)!=null) {
			conditions.add(builder.equal(root.get("releaseYear"), parameters.get(3)));
		}
		//High ranked
		if(parameters.get(4)!=null) {
			Join<RatingOfGame, Game> ratingJoin = root.join("game");
			criteria.groupBy(root.get("name"));
			criteria.having(builder.greaterThanOrEqualTo(builder.avg(ratingJoin.get("value")), highRankLimit));
		}
		
		if(conditions.size()>0) {
			criteria.where(builder.and(conditions.toArray(new Predicate[conditions.size()])));
		}
		
		List<String> names = (List<String>) entityManager.createQuery(criteria).getResultList();
		
		
		return names;
	}
	public Genre getGenreByName(String genreName) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Genre> criteria = builder.createQuery( Genre.class );
		
		Root<Genre> root = criteria.from(Genre.class);
		criteria.where(builder.equal(builder.upper(root.get("name")), genreName.toUpperCase()));
		
		List<Genre> genres = (List<Genre>) entityManager.createQuery( criteria ).getResultList();
		
		return genres.size() > 0 ? genres.get(0) : null;
	}
	public double getAverageRankingOfGame(Game game) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Double> criteria = builder.createQuery( Double.class );
		
		Root<RatingOfGame> root = criteria.from(RatingOfGame.class);
		Join<RatingOfGame, Game> gameJoin = root.join("game");
		
		criteria.select(builder.avg(root.get("value")));
		criteria.where(builder.equal(builder.upper(gameJoin.get("name")), game.getName().toUpperCase()));
		criteria.groupBy(gameJoin.get("gameID"));
		
		List<Double> avg = (List<Double>) entityManager.createQuery( criteria ).getResultList();
		
		return avg.size() > 0 ? avg.get(0) : null;
	}
	public RatingOfGame getRankingOfGameByUserAndGame(String gameName, String userName) {
		EntityManager entityManager = null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<RatingOfGame> criteria = builder.createQuery( RatingOfGame.class );
		
		Root<RatingOfGame> root = criteria.from(RatingOfGame.class);
		Join<RatingOfGame, Game> gameJoin = root.join("game");
		Join<RatingOfGame, UserAccount> userJoin = root.join("user");
		
		criteria.select(root);
		criteria.where(builder.and(builder.equal(builder.upper(gameJoin.get("name")), gameName.toUpperCase()), builder.equal(builder.upper(userJoin.get("name")), userName.toUpperCase())));
		
		List<RatingOfGame> avg = (List<RatingOfGame>) entityManager.createQuery( criteria ).getResultList();
		
		return avg.size() > 0 ? avg.get(0) : null;
	}
}
