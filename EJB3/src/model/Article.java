package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
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
public class Article implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long articleID;
	@ManyToOne
	private UserAccount author;
	@ManyToOne
	private Game game;
	@Column(unique=true)
	private String title;
	@Lob
	private String text;
	@Temporal(value = TemporalType.DATE)
	private Date published;
	@Basic
	byte[] image;
	
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}