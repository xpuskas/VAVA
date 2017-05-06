package clientClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccount implements Serializable {
	private long userID;
	private String name;
	private String password;
	private Date birthDate;

	private List<DeveloperGame> developerGames = new ArrayList<DeveloperGame>();

	private List<Article> usersArticles = new ArrayList<Article>();
	
	private List<ViewGameByUser> viewedGames = new ArrayList<ViewGameByUser>();
	
	private List<RatingOfGame> ratingsOfGames = new ArrayList<RatingOfGame>();
	
	private List<Comment> usersComments = new ArrayList<Comment>();
	
	private List<OfficialGame> addedGames = new ArrayList<OfficialGame>();
	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DeveloperGame> getUsersGames() {
		return developerGames;
	}

	public void setUsersGames(List<DeveloperGame> usersGames) {
		this.developerGames = usersGames;
	}

	public List<Article> getUsersArticles() {
		return usersArticles;
	}

	public void setUsersArticles(List<Article> usersArticles) {
		this.usersArticles = usersArticles;
	}

	public List<ViewGameByUser> getViewedGames() {
		return viewedGames;
	}

	public void setViewedGames(List<ViewGameByUser> viewedGames) {
		this.viewedGames = viewedGames;
	}

	public List<RatingOfGame> getRatingsOfGames() {
		return ratingsOfGames;
	}

	public void setRatingsOfGames(List<RatingOfGame> ratingsOfGames) {
		this.ratingsOfGames = ratingsOfGames;
	}

	public List<Comment> getUsersComments() {
		return usersComments;
	}

	public void setUsersComments(List<Comment> usersComments) {
		this.usersComments = usersComments;
	}

	public List<OfficialGame> getAddedGames() {
		return addedGames;
	}

	public void setAddedGames(List<OfficialGame> addedGames) {
		this.addedGames = addedGames;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}