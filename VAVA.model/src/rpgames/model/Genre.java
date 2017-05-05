package rpgames.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genre {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int genreID;
	private String name;
    @Basic
    private byte[] image;
	
    @ElementCollection
	@OneToMany(mappedBy="genre")
	private Collection<Game> games = new ArrayList<Game>();

	public int getGenreID() {
		return genreID;
	}

	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Collection<Game> getGames() {
		return games;
	}

	public void setGames(Collection<Game> games) {
		this.games = games;
	}
}
