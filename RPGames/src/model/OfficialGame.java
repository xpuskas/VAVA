package model;

import java.io.Serializable;
import java.util.Date;

public class OfficialGame extends Game implements Serializable {
	
	private String studio;
	private UserAccount addedBy;
	private Date added;
	
	public OfficialGame() {
		
	}
	
	public OfficialGame(String name, short releaseYear, String description, Genre genre, String studio, UserAccount addedBy, Date added, byte[] image) {
		super(name, releaseYear, description, genre, image);
		this.studio = studio;
		this.added = added;
		this.addedBy = addedBy;
	}
	
	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public UserAccount getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(UserAccount addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}
}