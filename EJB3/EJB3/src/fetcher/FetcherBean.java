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
import model.Game;
import model.Genre;
import model.OfficialGame;
import model.Screenshot;
import model.UserAccount;
import model.UserDetails;
import model.ViewGameByUser;

@Stateless
public class FetcherBean implements FetcherBeanRemote {

	@PersistenceContext(unitName="EjbComponentPU")
	private EntityManager em;
	
	
	@Override
	public List<UserDetails> getName() {
		return em.createQuery("from UserDetails").getResultList();
	}
	
	
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
		criteria.orderBy(builder.desc(root.get("viewed")));
		
		Query query = em.createQuery( criteria );
		query.setMaxResults(limit);
		
		

		List<Game> games = (List<Game>) query.getResultList();
		
		
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
	
	
	
}
