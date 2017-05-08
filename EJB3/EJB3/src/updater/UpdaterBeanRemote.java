package updater;

import javax.ejb.Remote;

import model.Comment;
import model.Genre;
import model.OfficialGame;
import model.RatingOfGame;
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

}
