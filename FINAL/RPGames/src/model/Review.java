package model;

import java.io.Serializable;

/**
 * Model class instances of which can be persisted by server.
 * Represents an article about a {@link model.Game} object, which is enhanced
 * by explicitly enumerating pros and cons of the game and adding rank
 * which represents author´s overall opinion on the game.
 */
public class Review extends Article implements Serializable {
	private String pros;
	private String cons;
	private double rank;
	
	public String getPros() {
		return pros;
	}
	public void setPros(String pros) {
		this.pros = pros;
	}
	public String getCons() {
		return cons;
	}
	public void setCons(String cons) {
		this.cons = cons;
	}
	public double getRank() {
		return rank;
	}
	public void setRank(double rank) {
		this.rank = rank;
	}
}