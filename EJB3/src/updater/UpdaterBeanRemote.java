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

	void addVGBU(ViewGameByUser vgbu);

	void addGenre(Genre genre);

	void addGame(OfficialGame game);

	void addUser(UserAccount user);

	void addScreenshot(Screenshot screen);

	void addComment(Comment comment);

	void addRatingOfGame(RatingOfGame rog);

	RatingOfGame updateRatingOfGame(RatingOfGame rog);

	void addDeveloperGame(DeveloperGame game);

	void addReview(Review review);

	void addArticle(Article article);

}
