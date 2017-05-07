package test;

import java.util.List;

import javax.ejb.Remote;

import model.Comment;
import model.Game;
import model.Genre;
import model.OfficialGame;
import model.Screenshot;
import model.UserAccount;
import model.UserDetails;
import model.ViewGameByUser;



/**
 * Remote rozhranie TestBean-y pre pristup z klienta/klientov
 * @author Jaroslav Jakubik
 */
@Remote
public interface GameBeanRemote {

	
	List<UserDetails> getNameh();
	
	String vratMeno(int id);
	
	String addGame(OfficialGame game);
	
	Game getGameByName(String game);

	List<OfficialGame> mostRecentGames(int count);

	List<String> gameArticles(Game game);

	List<Comment> getPagedCommentsForGame(Game game, int pagination, int page);

	List<Screenshot> getPagedScreenshotsForGame(Game game, int pagination, int page);

	UserAccount getUserAccountByName(String name);

	void addUser(UserAccount user);

	List<String> getAllGenres();

	void addGenre(Genre genre);

	List<Game> getLastViewedGamesByUser(UserAccount user, int limit);

	void addVGBU(ViewGameByUser vgbu);

	List<UserDetails> getName();
	
}
