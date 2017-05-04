package rpgames.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
@Entity
public class Review extends Article{
	@Lob
	private String pros;
	@Lob
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