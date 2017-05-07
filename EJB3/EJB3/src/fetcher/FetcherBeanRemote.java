package fetcher;

import java.util.List;

import javax.ejb.Remote;

import model.Comment;
import model.Game;
import model.Genre;
import model.OfficialGame;
import model.Screenshot;
import model.UserAccount;
import model.UserDetails;

@Remote
public interface FetcherBeanRemote {

	List<Game> getLastViewedGamesByUser(UserAccount user, int limit);

	List<String> getAllGenres();

	UserAccount getUserAccountByName(String name);

	List<Screenshot> getPagedScreenshotsForGame(Game game, int pagination, int page);

	List<String> gameArticles(Game game);

	List<OfficialGame> mostRecentGames(int count);

	Game getGameByName(String game);

	List<UserDetails> getName();

	List<Comment> getPagedCommentsForGame(Game game, int pagination, int page);

	Genre getGenreByName(String genreName);

}
