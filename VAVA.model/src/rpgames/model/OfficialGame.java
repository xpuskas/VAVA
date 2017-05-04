package rpgames.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
//@NamedQuery(name = "OfficialGame.lastViewed", query = "select OfficialGame from UserAccount join ViewGameByUser on UserAccount.userID=ViewGameByUser.author join OfficialGame on OfficialGame.gameID=ViewGameByUser.game where UserAccount.userID=:userID ORDER BY ViewGameByUser.viewed DESC")
@NamedQuery(name = "OfficialGame.lastViewed", query = "from UserAccount join ViewGameByUser on UserAccount.userID=ViewGameByUser.author")
public class OfficialGame extends Game{
	private String studio;

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}
}