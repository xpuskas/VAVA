package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RatingOfGame implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long ratingID;
	@ManyToOne
	private UserAccount user;
	@ManyToOne
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
