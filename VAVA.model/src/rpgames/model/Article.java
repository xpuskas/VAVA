package rpgames.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Article {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long articleID;
	@ManyToOne
	private UserAccount author;
	@ManyToOne
	private Game game;
	@Lob
	private String text;
	@Temporal(value = TemporalType.DATE)
	private Date published;
	
	public long getArticleID() {
		return articleID;
	}
	public void setArticleID(long articleID) {
		this.articleID = articleID;
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
	public Date getPublished() {
		return published;
	}
	public void setPublished(Date published) {
		this.published = published;
	}
}