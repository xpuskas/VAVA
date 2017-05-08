package model;

import java.io.Serializable;

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
