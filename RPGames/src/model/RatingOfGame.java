package model;

import java.io.Serializable;

public class RatingOfGame implements Serializable {
	private long ratingID;
	private UserAccount user;
	private Game game;
	private double value;
	
	public long getRatingID() {
		return ratingID;
	}
	public void setRatingID(long ratingID) {
		this.ratingID = ratingID;
	}
	public UserAccount getUser() {
		return user;
	}
	public void setUser(UserAccount user) {
		this.user = user;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
