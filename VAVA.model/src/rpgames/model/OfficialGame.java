package rpgames.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
public class OfficialGame extends Game{
	private String studio;

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}
}