package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
/**
 * Model class instances of which can be persisted by server.
 * Represents a game which has been added by its author. Is meant for coders
 * who develop their own games at home in their free time and would like to
 * share them and develop further with simple way of feedback.
 */
public class DeveloperGame extends Game implements Serializable {
	@ManyToOne
	private UserAccount author;

	public UserAccount getAuthor() {
		return author;
	}

	public void setAuthor(UserAccount author) {
		this.author = author;
	}
}
