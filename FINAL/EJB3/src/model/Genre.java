package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
/**
 * Model class instances of which can be persisted by server.
 * Represents a genre of game. It is meant to be aggregated by {@link model.Game}.
 * The same instance of this class should be used for all the games of this genre.
 */
public class Genre implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int genreID;
	@Column(unique=true)
	private String name;
    @Basic
    private byte[] image;
	
    @ElementCollection
	@OneToMany(mappedBy="genre")
	private List<Game> games = new ArrayList<Game>();

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

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
}
