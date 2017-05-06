package clientClasses;

import java.io.Serializable;

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