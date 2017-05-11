package model;

import java.io.Serializable;
import java.util.Date;
/**
 * Model class instances of which can be persisted by server.
 * Represents an article about a {@link model.Game} object.
 */
public class Article implements Serializable {
	private long articleID;
	private UserAccount author;
	private Game game;
	private String title;
	private String text;
	private Date published;
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