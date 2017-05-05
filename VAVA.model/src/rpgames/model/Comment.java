package rpgames.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long commentID;
	@ManyToOne
	private UserAccount author;
	@ManyToOne
	private Game game;
	@Lob
	private String text;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date posted;
	
	public long getCommentID() {
		return commentID;
	}
	public void setCommentID(long commentID) {
		this.commentID = commentID;
	}
	public UserAccount getAuthor() {
		return author;
	}
	public void setAuthor(UserAccount author) {
		this.author = author;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getPosted() {
		return posted;
	}
	public void setPosted(Date posted) {
		this.posted = posted;
	}
}