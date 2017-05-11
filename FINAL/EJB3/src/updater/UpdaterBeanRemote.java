package updater;

import javax.ejb.Remote;

import model.Article;
import model.Comment;
import model.DeveloperGame;
import model.Genre;
import model.OfficialGame;
import model.RatingOfGame;
import model.Review;
import model.Screenshot;
import model.UserAccount;
import model.ViewGameByUser;

@Remote
public interface UpdaterBeanRemote {
	/**
	 * Persists specified {@link model.ViewGameByUser} object.
	 * @param vgbu - object to be persisted
	 */
	void addVGBU(ViewGameByUser vgbu);
	/**
	 * Persists specified {@link model.Genre} object.
	 * @param genre - object to be persisted
	 */
	void addGenre(Genre genre);
	/**
	 * Persists specified {@link model.OfficialGame} object.
	 * @param game - object to be persisted
	 */
	void addGame(OfficialGame game);
	/**
	 * Persists specified {@link model.UserAccount} object.
	 * @param user - object to be persisted
	 */
	void addUser(UserAccount user);
	/**
	 * Persists specified {@link model.Screenshot} object.
	 * @param screen - object to be persisted
	 */
	void addScreenshot(Screenshot screen);
	/**
	 * Persists specified {@link model.Comment} object.
	 * @param comment - object to be persisted
	 */
	void addComment(Comment comment);
	/**
	 * Persists specified {@link model.RatingOfGame} object.
	 * @param rog - object to be persisted
	 */
	void addRatingOfGame(RatingOfGame rog);
	/**
	 * Updates specified {@link model.RatingOfGame} object.
	 * @param rog - object to be updated
	 * @return - updated {@link model.RatingOfGame} object
	 */
	RatingOfGame updateRatingOfGame(RatingOfGame rog);
	/**
	 * Persists specified {@link model.DeveloperGame} object.
	 * @param game - object to be persisted
	 */
	void addDeveloperGame(DeveloperGame game);
	/**
	 * Persists specified {@link model.Review} object.
	 * @param review - object to be persisted
	 */
	void addReview(Review review);
	/**
	 * Persists specified {@link model.Article} object.
	 * @param article - object to be persisted
	 */
	void addArticle(Article article);
}
