package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
//@NamedNativeQuery(name = "LastViewedGamesOfUser", query = "SELECT DISTINCT Game.* FROM Game JOIN ViewGameByUser ON Game.gameID=ViewGameByUser.game_gameid JOIN UserAccount ON UserAccount.userID=ViewGameByUser.viewer_userid where viewer_userid=?")
public class ViewGameByUser implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long viewID;
	@ManyToOne
	private UserAccount viewer;
	@ManyToOne
	private Game game;
	@Temporal(value = TemporalType.TIMESTAMP)
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