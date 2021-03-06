package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class instances of which can be persisted by server.
 * Represents a genre of game. It is meant to be aggregated by {@link model.Game}.
 * The same instance of this class should be used for all the games of this genre.
 */
public class Genre implements Serializable {
	private int genreID;
	private String name;
    private byte[] image;
	
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
