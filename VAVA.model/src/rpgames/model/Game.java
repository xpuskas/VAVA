package rpgames.model;


import java.util.ArrayList;
import java.util.Collection;
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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Game {
	@Id	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long gameID;
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
	private Collection<Article> articlesAbout = new ArrayList<Article>();
	
	@ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private Collection<ViewGameByUser> views = new ArrayList<ViewGameByUser>();
	
	@ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private Collection<RatingOfGame> ratings = new ArrayList<RatingOfGame>();
	
	@ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private Collection<Comment> comments = new ArrayList<Comment>();
	
	@ElementCollection
	@OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval=true)
	private Collection<Screenshot> screenshots = new ArrayList<Screenshot>();
	
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

	public Collection<Article> getArticlesAbout() {
		return articlesAbout;
	}

	public void setArticlesAbout(Collection<Article> articlesAbout) {
		this.articlesAbout = articlesAbout;
	}

	public Collection<ViewGameByUser> getViews() {
		return views;
	}

	public void setViews(Collection<ViewGameByUser> views) {
		this.views = views;
	}

	public Collection<RatingOfGame> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<RatingOfGame> ratings) {
		this.ratings = ratings;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	public Collection<Screenshot> getScreenshots() {
		return screenshots;
	}

	public void setScreenshots(Collection<Screenshot> screenshots) {
		this.screenshots = screenshots;
	}
}