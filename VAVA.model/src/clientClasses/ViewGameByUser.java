package clientClasses;

import java.io.Serializable;
import java.util.Date;

public class ViewGameByUser implements Serializable {
	private long viewID;
	private UserAccount viewer;
	private Game game;
	private Date viewed;
	
	public long getViewID() {
		return viewID;
	}
	public void setViewID(long viewID) {
		this.viewID = viewID;
	}
	public UserAccount getViewer() {
		return viewer;
	}
	public void setViewer(UserAccount viewer) {
		this.viewer = viewer;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Date getViewed() {
		return viewed;
	}
	public void setViewed(Date viewed) {
		this.viewed = viewed;
	}
}