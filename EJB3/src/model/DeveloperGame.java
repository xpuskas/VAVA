package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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
