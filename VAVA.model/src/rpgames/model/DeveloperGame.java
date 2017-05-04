package rpgames.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DeveloperGame extends Game {
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserAccount author;

	public UserAccount getAuthor() {
		return author;
	}

	public void setAuthor(UserAccount author) {
		this.author = author;
	}
}
