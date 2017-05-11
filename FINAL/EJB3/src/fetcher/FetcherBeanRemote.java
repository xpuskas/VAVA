package fetcher;

import java.util.List;

import javax.ejb.Remote;

import model.Article;
import model.AvgGenreRatingWrapper;
import model.Comment;
import model.DeveloperGame;
import model.Game;
import model.GameCountPerGenreWrapper;
import model.Genre;
import model.OfficialGame;
import model.RatingOfGame;
import model.Review;
import model.Screenshot;
import model.UserAccount;

@Remote
/**
 * Interface which is implemented by {@link fetcher.FetcherBean}.
 * Its methods retrieve specified data from the database.
 */
public interface FetcherBeanRemote {
	/**
	 * Retrieves a limited amount of games which have been viewed by a specified user.
	 * Returns them in a list sorted by time stamp of the last time the user
	 * viewed the game descending.
	 * @param user - user, for who are the games to be retrieved
	 * @param limit - how many games are to be retrieved.
	 * It is recommended that -1 means unlimited number
	 * @return sorted list of games recently viewed by user
	 */
	List<Game> getLastViewedGamesByUser(UserAccount user, int limit);
	/**
	 * Retrieves names of all game genres present in the database.
	 * It is meant for displayable lists of graphical interface.
	 * @return list of names of all available game genres
	 */
	List<String> getAllGenres();
	/**
	 * Retrieves {@link model.UserAccount} object from the database based on login name.
	 * Important for verifying upon logging in.
	 * @param name - login name of the user
	 * @return {@link model.UserAccount} object or <code>null</code>
	 */
	UserAccount getUserAccountByName(String name);
	/**
	 * Retrieves {@link model.Screenshot} objects from the database which are related to
	 * the specified game. Applies pagination as specified by parameters.
	 * @param game - {@link model.Game} object representing the game screenshots of which
	 * are to be retrieved.
	 * @param pagination - how many screenshots to retrieve
	 * @param page - given the pagination, screenshots from which page are to be retrieved
	 * @return list of paged {@link model.Screenshot} objects
	 */
	List<Screenshot> getPagedScreenshotsForGame(Game game, int pagination, int page);
	/**
	 * Retrieves titles of all articles including reviews in form of articles topic of
	 * which is the specified game. Useful for lists in graphical interface.
	 * @param game - game articles related to which are to be retrieved
	 * @return list of names of articles related to specified game
	 */
	List<String> gameArticles(Game game);
	/**
	 * Retrieves specified amount of most recently added official games represented by
	 * {@link model.OfficialGame} objects from the database and sorts them by the time they were added 
	 * in descending order.
	 * @param count - how many games are to be retrieved
	 * @return sorted list of most recently created {@link model.OfficialGame} objects
	 * of specified size
	 */
	List<OfficialGame> mostRecentGames(int count);
	/**
	 * Retrieves {@link model.Game} object from the database with the specified name.
	 * @param game - name of the game which is to be retrieved
	 * @return {@link model.Game} object or <code>null</code>
	 */
	Game getGameByName(String game);
	/**
	 * Retrieves {@link model.Comment} objects from the database which are related to
	 * the specified game. Applies pagination as specified by parameters.
	 * @param game - {@link model.Game} object representing the game comments of which
	 * are to be retrieved.
	 * @param pagination - how many comments to retrieve
	 * @param page - given the pagination, comments from which page are to be retrieved
	 * @return list of paged {@link model.Comment} objects
	 */
	List<Comment> getPagedCommentsForGame(Game game, int pagination, int page);
	/**
	 * Retrieves {@link model.Genre} object from the database with the specified name.
	 * @param genreName - name of the {@link model.Genre} object to be retrieved
	 * @return {@link model.Genre} object or <code>null</code>
	 */
	Genre getGenreByName(String genreName);
	/**
	 * Retrieves {@link model.OfficialGame} object from the database with the specified name.
	 * @param game - name of the game which is to be retrieved
	 * @return {@link model.OfficialGame} object or <code>null</code>
	 */
	OfficialGame getOfficialGameByName(String name);
	/**
	 * Retrieves average ranking of the specified game by querying the database.
	 * @param game - {@link model.Game} object representing the game average ranking of which
	 * is to be retrieved.
	 * @return primitive <code>double</code> value representing average ranking of specified game
	 * or <code>0</code>.
	 */
	double getAverageRankingOfGame(Game game);
	/**
	 * Retrieves {@link model.RatingOfGame} object holding the information about
	 * the ranking that the specified user submitted to the specified game.
	 * @param gameName - name of the game ranking of which is to be retrieved
	 * @param userName - name of the user who submitted the rating
	 * @return {@link model.RatingOfGame} object or <code>null</code>
	 */
	RatingOfGame getRankingOfGameByUserAndGame(String gameName, String userName);
	/**
	 * Retrieves limited amount of names of the most recently viewed games by the specified user
	 *  sorted in order in which they were last viewed. 
	 * @param userName - name of the user viewed games of who are to be retrieved
	 * @param limit - amount of game names to be retrieved
	 * @return sorted list of names of games of the specified size
	 */
	List<String> getLastViewedGameNamesByUser(String userName, int limit);
	/**
	 * Retrieves all {@link model.OfficialGame} objects from the database which have been added
	 * by the specified user.
	 * @param userName - name of the user games added by whom are to be retrieved
	 * @return list of {@link model.OfficialGame} objects added by the specified user
	 */
	List<OfficialGame> getOfficialGamesAddedByUser(String userName);
	/**
	 * Retrieves all {@link model.DeveloperGame} objects from the database which have been added
	 * by the specified user.
	 * @param userName - name of the user games added by whom are to be retrieved
	 * @return list of {@link model.DeveloperGame} objects added by the specified user
	 */
	List<DeveloperGame> getDeveloperGamesAddedByUser(String userName);
	/**
	 * Retrieves values of <code>name</code> attribute of all {@link model.DeveloperGame}
	 * objects from the database which have been added by the specified user.
	 * @param userName - name of the user names of games added by whom are to be retrieved
	 * @return list of names of all developer games added by the specified user
	 */
	List<String> getDeveloperGameNamesAddedByUser(String userName);
	/**
	 * Retrieves {@link model.Article} object from the database with the specified title.
	 * @param title - title of the article which is to be retrieved
	 * @return {@link model.Article} object or <code>null</code>
	 */
	Article getArticleByTitle(String title);
	/**
	 * Retrieves list of values of <code>name</code> attribute of all {@link model.Article}
	 * objects related to the specified game.
	 * @param game - game names of articles related to which are to be retrieved
	 * @return list of names of articles related to the specified game
	 */
	List<String> articleNamesByGame(Game game);
	/**
	 * Retrieves list of values of <code>name</code> attribute of all {@link model.Review}
	 * objects related to the specified game.
	 * @param game - game names of reviews related to which are to be retrieved
	 * @return list of names of reviews related to the specified game
	 */
	List<String> reviewNamesByGame(Game game);
	/**
	 * Retrieves {@link model.Review} object from the database with the specified title.
	 * @param title - title of the article which is to be retrieved
	 * @return {@link model.Review} object or <code>null</code>
	 */
	Review getReviewByTitle(String title);
	/**
	 * Retrieves list of all {@link model.Article}objects related to the specified game.
	 * @param game - game articles related to which are to be retrieved
	 * @return list of articles related to the specified game
	 */
	List<Article> articlesByGame(Game game);
	/**
	 * Retrieves {@link model.DeveloperGame} object from the database with the specified name.
	 * @param game - name of the game which is to be retrieved
	 * @return {@link model.DeveloperGame} object or <code>null</code>
	 */
	DeveloperGame getDeveloperGameByName(String name);
	/**
	 * Retrieves list of {@link model.AvgGenreRatingWrapper} objects, which means
	 * average ranking of all games for each genre.
	 * @return list of {@link model.AvgGenreRatingWrapper} objects
	 */
	List<AvgGenreRatingWrapper> getGenreAvgRating();
	/**
	 * Retrieves values of <code>name</code> attribute of all {@link model.Game}
	 * objects from the database which which match the given parameters.
	 * @param parameters - list of filters to be applied
	 * @param highRankLimit - specifies minimum value of average rating of game
	 * when requested to retrieve only names of high-ranked games
	 * @return list of names of all games matching the specified pattern
	 */
	List<String> getGameNamesByFiltration(List<String> parameters, double highRankLimit);
	/**
	 * Retrieves values of <code>name</code> attribute of all {@link model.OfficialGame}
	 * objects from the database which which match the given parameters.
	 * @param parameters - list of filters to be applied
	 * @param highRankLimit - specifies minimum value of average rating of game
	 * when requested to retrieve only names of high-ranked games
	 * @return list of names of all games matching the specified pattern
	 */
	List<String> getOfficialGameNamesByFiltration(List<String> parameters, double highRankLimit);
	/**
	 * Retrieves values of <code>name</code> attribute of all {@link model.DeveloperGame}
	 * objects from the database which which match the given parameters.
	 * @param parameters - list of filters to be applied
	 * @param highRankLimit - specifies minimum value of average rating of game
	 * when requested to retrieve only names of high-ranked games
	 * @return list of names of all games matching the specified pattern
	 */
	List<String> getDeveloperGameNamesByFiltration(List<String> parameters, double highRankLimit);
	/**
	 * Retrieves list of {@link model.GameCountPerGenreWrapper} objects, which means
	 * number of all games for each genre.
	 * @return list of {@link model.GameCountPerGenreWrapper} objects
	 */
	List<GameCountPerGenreWrapper> getGameCountPerGenre();
}
