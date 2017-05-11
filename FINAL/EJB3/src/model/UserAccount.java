package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
/**
 * Model class instances of which can be persisted by server.
 * An instance of this class represent one account of a user, which can be accessed
 * via its login name and its password.
 * Profile picture is optional, unless user specifies it, he is assigned a default picture.
 */
public class UserAccount implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long userID;
	@Column(unique=true)
	private String name;
	private String password;
	@Temporal(value = TemporalType.DATE)
	private Date birthDate;
    @Basic
    private byte[] profilePic;

	@ElementCollection
	@OneToMany(mappedBy="author")
	private List<DeveloperGame> developerGames = new ArrayList<DeveloperGame>();
	
	@ElementCollection
	@OneToMany(mappedBy="author")
	private List<Article> usersArticles = new ArrayList<Article>();
	
	@ElementCollection
	@OneToMany(mappedBy="viewer")
	private List<ViewGameByUser> viewedGames = new ArrayList<ViewGameByUser>();
	
	@ElementCollection
	@OneToMany(mappedBy="user")
	private List<RatingOfGame> ratingsOfGames = new ArrayList<RatingOfGame>();
	
	@ElementCollection
	@OneToMany(mappedBy="author")
	private List<Comment> usersComments = new ArrayList<Comment>();
	
	@ElementCollection
	@OneToMany(mappedBy="addedBy")
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

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
}