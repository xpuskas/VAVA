package fetcher;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import model.Article;
import model.Comment;
import model.DeveloperGame;
import model.Game;
import model.Genre;
import model.OfficialGame;
import model.RatingOfGame;
import model.Review;
import model.Screenshot;
import model.UserAccount;
import model.ViewGameByUser;

@Stateless
public class FetcherBean implements FetcherBeanRemote {

	@PersistenceContext(unitName="EjbComponentPU")
	private EntityManager em;
	
	

	
	
	@Override
	public Game getGameByName(String game) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Game> criteria = builder.createQuery( Game.class );
		Root<Game> root = criteria.from( Game.class );
		criteria.select( root );
		criteria.where( builder.equal( root.get( "name" ), game ) );

		 List<Game> games = em.createQuery( criteria ).getResultList();
	
	//	 ObservableList<Game> list = FXCollections.observableArrayList(ganes);
		
		return games.size()>0 ? games.get( 0 ) : null;
	}
	
	
	@Override
	public OfficialGame getOfficialGameByName(String name) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<OfficialGame> criteria = builder.createQuery( OfficialGame.class );
		
		Root<OfficialGame> root = criteria.from(OfficialGame.class);
		criteria.where(builder.equal(builder.upper(root.get("name")), name.toUpperCase()));
		
		List<OfficialGame> games = (List<OfficialGame>) em.createQuery( criteria ).getResultList();
		
		return games.size() > 0 ? games.get(0) : null;
	}
	
	
	@Override
	public List<OfficialGame> mostRecentGames(int count) {
		
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<OfficialGame> criteria = builder.createQuery( OfficialGame.class );
		Root<OfficialGame> root = criteria.from(OfficialGame.class );
		criteria.select( root );
		
		criteria.orderBy(builder.desc(root.get("added")));
		
		Query query = em.createQuery( criteria );
		query.setMaxResults(count);
		
		List<OfficialGame> games = query.getResultList();

		return games;
	}
	
	
	@Override
	 public List<String> gameArticles(Game game) {
		  List<Article> articles = (List<Article>)game.getArticlesAbout();
		  List<String> names = new ArrayList<String>(articles.size());
		  
		  for(int i = 0; i<articles.size(); i++) {
		   names.add(articles.get(i).getTitle());
		  }
		  
		  return names;
		 }
	
	
	@Override
	public List<Comment> getPagedCommentsForGame(Game game, int pagination, int page) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Comment> criteria = builder.createQuery( Comment.class );
		
		Root<Comment> root = criteria.from(Comment.class);
		Join<Comment, Game> commentJoin = root.join("game");
		
		criteria.select(root);
		criteria.orderBy(builder.desc(root.get("posted")));
		criteria.where(builder.equal(commentJoin.get("name"), game.getName()));
		
		Query query = em.createQuery( criteria );
		query.setMaxResults(pagination);
		query.setFirstResult(pagination * page);
		
		List<Comment> comments = (List<Comment>) query.getResultList();
		
		return comments;
	}
	
	
	@Override
	public List<Screenshot> getPagedScreenshotsForGame(Game game, int pagination, int page) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Screenshot> criteria = builder.createQuery( Screenshot.class );
		
		Root<Screenshot> root = criteria.from(Screenshot.class);
		Join<Screenshot, Game> commentJoin = root.join("game");
		
		criteria.select(root);
		criteria.orderBy(builder.desc(root.get("screenshotID")));
		criteria.where(builder.equal(commentJoin.get("name"), game.getName()));
		
		Query query = em.createQuery( criteria );
		query.setMaxResults(pagination);
		query.setFirstResult(pagination * page);

		List<Screenshot> screenshots = (List<Screenshot>) query.getResultList();
		
		return screenshots;
	}
	
	
	@Override
	public UserAccount getUserAccountByName(String name) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<UserAccount> criteria = builder.createQuery( UserAccount.class );
		
		Root<UserAccount> root = criteria.from(UserAccount.class);
		criteria.where(builder.equal(root.get("name"), name));
		
		List<UserAccount> users = (List<UserAccount>) em.createQuery( criteria ).getResultList();
		
		return users.size() > 0 ? users.get(0) : null;
	}
	
	
	@Override
	public List<String> getAllGenres() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		
		Root<Genre> root = criteria.from(Genre.class);
		criteria.select(root.get("name"));
		
		List<String> result = (List<String>) em.createQuery( criteria ).getResultList();
		
		return result;
	}
	
	
	@Override
	public List<Game> getLastViewedGamesByUser(UserAccount user, int limit) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Game> criteria = builder.createQuery( Game.class );
		
		Root<ViewGameByUser> root = criteria.from(ViewGameByUser.class);
		Join<ViewGameByUser, UserAccount> userJoin = root.join("viewer");
		Join<ViewGameByUser, Game> gameJoin = root.join("game");
		
		criteria.select(gameJoin);
		criteria.where(builder.equal(userJoin.get("name"), user.getName()));
		criteria.orderBy(builder.desc(builder.max(root.get("viewed"))));
		criteria.groupBy(gameJoin.get("name"));
		
		Query query = em.createQuery( criteria );
		
		if(limit > 0) {
			query.setMaxResults(limit);
		}

		List<Game> games = (List<Game>) query.getResultList();
		
		
		return games;
	}
	
	@Override
	public List<String> getLastViewedGameNamesByUser(String userName, int limit) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		
		Root<ViewGameByUser> root = criteria.from(ViewGameByUser.class);
		Join<ViewGameByUser, UserAccount> userJoin = root.join("viewer");
		Join<ViewGameByUser, Game> gameJoin = root.join("game");
		
		
		criteria.select(gameJoin.get("name"));
		criteria.where(builder.equal(userJoin.get("name"), userName));
		criteria.orderBy(builder.desc(builder.max(root.get("viewed"))));
		criteria.groupBy(gameJoin.get("name"));
	
		
		Query query = em.createQuery( criteria );
		
		if(limit > 0) {
			query.setMaxResults(limit);
		}
		

		List<String> games = (List<String>) query.getResultList();
		
		
		return games;
	}
	
	
	@Override
	public Genre getGenreByName(String genreName) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Genre> criteria = builder.createQuery( Genre.class );
		
		Root<Genre> root = criteria.from(Genre.class);
		criteria.where(builder.equal(builder.upper(root.get("name")), genreName.toUpperCase()));
		
		List<Genre> genres = (List<Genre>) em.createQuery( criteria ).getResultList();
		
		return genres.size() > 0 ? genres.get(0) : null;
	}
	
	
	@Override
	public double getAverageRankingOfGame(Game game) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Double> criteria = builder.createQuery( Double.class );
		
		Root<RatingOfGame> root = criteria.from(RatingOfGame.class);
		Join<RatingOfGame, Game> gameJoin = root.join("game");
		
		criteria.select(builder.avg(root.get("value")));
		criteria.where(builder.equal(builder.upper(gameJoin.get("name")), game.getName().toUpperCase()));
		criteria.groupBy(gameJoin.get("gameID"));
		
		List<Double> avg = (List<Double>) em.createQuery( criteria ).getResultList();
		
		return avg.size() > 0 ? avg.get(0) : -1;
	}
	
	
	@Override
	public RatingOfGame getRankingOfGameByUserAndGame(String gameName, String userName) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<RatingOfGame> criteria = builder.createQuery( RatingOfGame.class );
		
		Root<RatingOfGame> root = criteria.from(RatingOfGame.class);
		Join<RatingOfGame, Game> gameJoin = root.join("game");
		Join<RatingOfGame, UserAccount> userJoin = root.join("user");
		
		criteria.select(root);
		criteria.where(builder.and(builder.equal(builder.upper(gameJoin.get("name")), gameName.toUpperCase()), builder.equal(builder.upper(userJoin.get("name")), userName.toUpperCase())));
		
		List<RatingOfGame> avg = (List<RatingOfGame>) em.createQuery( criteria ).getResultList();
		
		return avg.size() > 0 ? avg.get(0) : null;
	}
	@Override
	public List<OfficialGame> getOfficialGamesAddedByUser(String userName) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<OfficialGame> criteria = builder.createQuery( OfficialGame.class );
		
		Root<OfficialGame> root = criteria.from(OfficialGame.class);
		Join<OfficialGame, UserAccount> joinUser = root.join("addedBy");
		
		criteria.select(root);
		criteria.where(builder.equal(joinUser.get("name"), userName));
		
		List<OfficialGame> games= (List<OfficialGame>) em.createQuery( criteria ).getResultList();
		
		return games;
	}
	@Override
	public List<DeveloperGame> getDeveloperGamesAddedByUser(String userName) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<DeveloperGame> criteria = builder.createQuery( DeveloperGame.class );
		
		Root<DeveloperGame> root = criteria.from(DeveloperGame.class);
		Join<DeveloperGame, UserAccount> joinUser = root.join("author");
		
		criteria.select(root);
		criteria.where(builder.equal(joinUser.get("name"), userName));
		
		List<DeveloperGame> games= (List<DeveloperGame>) em.createQuery( criteria ).getResultList();
		
		return games;
	}
	
	
	@Override
	public List<String> getDeveloperGameNamesAddedByUser(String userName) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		
		Root<DeveloperGame> root = criteria.from(DeveloperGame.class);
		Join<DeveloperGame, UserAccount> joinUser = root.join("author");
		
		criteria.select(root.get("name"));
		criteria.where(builder.equal(joinUser.get("name"), userName));
		
		List<String> games= (List<String>) em.createQuery( criteria ).getResultList();
		
		return games;
	}
	
	
	@Override
	public List<String> articleNamesByGame(Game game) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		
		Root<Article> root = criteria.from(Article.class);
		Join<Article, Game> gameJoin = root.join("game");
		
		criteria.select(root.get("title"));
		criteria.where(builder.equal(gameJoin.get("name"), game.getName()));
		
		List<String> articleNames = (List<String>) em.createQuery( criteria ).getResultList();
		
		return articleNames;
	}
	
	
	@Override
	public List<String> reviewNamesByGame(Game game) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		
		Root<Review> root = criteria.from(Review.class);
		Join<Review, Game> gameJoin = root.join("game");
		
		criteria.select(root.get("title"));
		criteria.where(builder.equal(gameJoin.get("name"), game.getName()));
		
		List<String> articleNames = (List<String>) em.createQuery( criteria ).getResultList();
		
		return articleNames;
	}
	
	
	@Override
	public Article getArticleByTitle(String title) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Article> criteria = builder.createQuery( Article.class );
		
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		
		criteria.where(builder.equal(root.get("title"), title));
		
		List<Article> articles = (List<Article>) em.createQuery( criteria ).getResultList();
		
		return articles.size() > 0 ? articles.get(0) : null;	
	}
	
	
	@Override
	public Review getReviewByTitle(String title) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Review> criteria = builder.createQuery( Review.class );
		
		Root<Review> root = criteria.from(Review.class);
		criteria.select(root);
		
		criteria.where(builder.equal(root.get("title"), title));
		
		List<Review> articles = (List<Review>) em.createQuery( criteria ).getResultList();
		
		return articles.size() > 0 ? articles.get(0) : null;	
	}
	
}
