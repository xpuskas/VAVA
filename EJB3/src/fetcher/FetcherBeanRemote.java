package fetcher;

import java.util.List;

import javax.ejb.Remote;

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

@Remote
public interface FetcherBeanRemote {

	List<Game> getLastViewedGamesByUser(UserAccount user, int limit);

	List<String> getAllGenres();

	UserAccount getUserAccountByName(String name);

	List<Screenshot> getPagedScreenshotsForGame(Game game, int pagination, int page);

	List<String> gameArticles(Game game);

	List<OfficialGame> mostRecentGames(int count);

	Game getGameByName(String game);


	List<Comment> getPagedCommentsForGame(Game game, int pagination, int page);

	Genre getGenreByName(String genreName);

	OfficialGame getOfficialGameByName(String name);

	double getAverageRankingOfGame(Game game);

	RatingOfGame getRankingOfGameByUserAndGame(String gameName, String userName);

	List<String> getLastViewedGameNamesByUser(String userName, int limit);

	List<OfficialGame> getOfficialGamesAddedByUser(String userName);

	List<DeveloperGame> getDeveloperGamesAddedByUser(String userName);

	List<String> getDeveloperGameNamesAddedByUser(String userName);

	Article getArticleByTitle(String title);

	List<String> articleNamesByGame(Game game);

	List<String> reviewNamesByGame(Game game);

	Review getReviewByTitle(String title);

}
