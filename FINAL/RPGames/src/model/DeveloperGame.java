package model;

import java.io.Serializable;
/**
 * Model class instances of which can be persisted by server.
 * Represents a game which has been added by its author. Is meant for coders
 * who develop their own games at home in their free time and would like to
 * share them and develop further with simple way of feedback.
 */
public class DeveloperGame extends Game implements Serializable {
	
	private UserAccount author;
	
	public DeveloperGame() {
		
	}
	
	public DeveloperGame(String name, short releaseYear, String description, Genre genre, UserAccount author, byte[] image) {
		super(name, releaseYear, description, genre, image);
		this.author = author;
	}

	public UserAccount getAuthor() {
		return author;
	}

	public void setAuthor(UserAccount author) {
		this.author = author;
	}
}
