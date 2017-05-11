package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Model class, which is a superclass to classes {@link model.DeveloperGame}
 * and {@link model.OfficialGame}. Inheritance is important for displaying them
 * in the same tables in graphical interface.
 */
public abstract class Game implements Serializable {
	private long gameID;

	private String name;
	private short releaseYear;
	private String description;
	private Genre genre;
    private byte[] image;
 
	private List<Article> articlesAbout = new ArrayList<Article>();
	
	private List<ViewGameByUser> views = new ArrayList<ViewGameByUser>();
	
	private List<RatingOfGame> ratings = new ArrayList<RatingOfGame>();

	private List<Comment> comments = new ArrayList<Comment>();

	private List<Screenshot> screenshots = new ArrayList<Screenshot>();
	
	public Game() {
		
	}
	
	public Game(String name, short releaseYear, String description, Genre genre, byte[] image) {
		super();
		this.name = name;
		this.releaseYear = releaseYear;
		this.description = description;
		this.genre = genre;
		this.image = image;
	}
	
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