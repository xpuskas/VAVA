package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Inheritance(strategy=InheritanceType.JOINED)
/**
 * Model class, which is a superclass to classes {@link model.DeveloperGame}
 * and {@link model.OfficialGame}. Inheritance is important for displaying them
 * in the same tables in graphical interface.
 */
public abstract class Game implements Serializable {
	@Id	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long gameID;
	@Column(unique=true)
	private String name;
	private short releaseYear;
	@Lob
	private String description;
	@ManyToOne
	private Genre genre;
    @Basic
    private byte[] image;
 
    @ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Article> articlesAbout = new ArrayList<Article>();
	
	@ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<ViewGameByUser> views = new ArrayList<ViewGameByUser>();
	
	@ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<RatingOfGame> ratings = new ArrayList<RatingOfGame>();
	
	@ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments = new ArrayList<Comment>();
	
	@ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Screenshot> screenshots = new ArrayList<Screenshot>();
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getGameID() {
		return gameID;
	}

	public void setGameID(long gameID) {
		this.gameID = gameID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Article> getArticlesAbout() {
		return articlesAbout;
	}

	public void setArticlesAbout(List<Article> articlesAbout) {
		this.articlesAbout = articlesAbout;
	}

	public List<ViewGameByUser> getViews() {
		return views;
	}

	public void setViews(List<ViewGameByUser> views) {
		this.views = views;
	}

	public List<RatingOfGame> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingOfGame> ratings) {
		this.ratings = ratings;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Screenshot> getScreenshots() {
		return screenshots;
	}

	public void setScreenshots(List<Screenshot> screenshots) {
		this.screenshots = screenshots;
	}
}