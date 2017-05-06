package clientClasses;

import java.io.Serializable;
import java.util.Date;

public class OfficialGame extends Game implements Serializable {
	private String studio;
	private UserAccount addedBy;
	private Date added;
	
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