package clientClasses;

import java.io.Serializable;

public class DeveloperGame extends Game implements Serializable {
	private UserAccount author;

	public UserAccount getAuthor() {
		return author;
	}

	public void setAuthor(UserAccount author) {
		this.author = author;
	}
}
