package rpgames.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import rpg.database.Hibernator;

@Entity
public class UserAccount {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long userID;
	private String name;
	private String password;
	
	@ElementCollection
	@OneToMany(mappedBy="author")
	private Collection<DeveloperGame> usersGames = new ArrayList<DeveloperGame>();
	
	@ElementCollection
	@OneToMany(mappedBy="author")
	private Collection<Article> usersArticles = new ArrayList<Article>();
	
	@ElementCollection
	@OneToMany(mappedBy="viewer")
	private Collection<ViewGameByUser> viewedGames = new ArrayList<ViewGameByUser>();
	
	@ElementCollection
	@OneToMany(mappedBy="user")
	private Collection<RatingOfGame> ratingsOfGames = new ArrayList<RatingOfGame>();
	
	@ElementCollection
	@OneToMany(mappedBy="author")
	private Collection<Comment> usersComments = new ArrayList<Comment>();
	
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

	public Collection<DeveloperGame> getUsersGames() {
		return usersGames;
	}

	public void setUsersGames(Collection<DeveloperGame> usersGames) {
		this.usersGames = usersGames;
	}

	public Collection<Article> getUsersArticles() {
		return usersArticles;
	}

	public void setUsersArticles(Collection<Article> usersArticles) {
		this.usersArticles = usersArticles;
	}

	public Collection<ViewGameByUser> getViewedGames() {
		return viewedGames;
	}

	public void setViewedGames(Collection<ViewGameByUser> viewedGames) {
		this.viewedGames = viewedGames;
	}

	public Collection<RatingOfGame> getRatingsOfGames() {
		return ratingsOfGames;
	}

	public void setRatingsOfGames(Collection<RatingOfGame> ratingsOfGames) {
		this.ratingsOfGames = ratingsOfGames;
	}

	public Collection<Comment> getUsersComments() {
		return usersComments;
	}

	public void setUsersComments(Collection<Comment> usersComments) {
		this.usersComments = usersComments;
	}
}