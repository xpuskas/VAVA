package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
/**
 * Model class instances of which can be persisted by server.
 * Represents a game which can add any user. It is meant to be any existing
 * game released by a game studio.
 */
public class OfficialGame extends Game implements Serializable {
	private String studio;
	@ManyToOne
	private UserAccount addedBy;
	@Temporal(value = TemporalType.TIMESTAMP)
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