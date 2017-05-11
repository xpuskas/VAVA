package model;

import java.io.Serializable;

/**
 * Wrapper class used for retrieving info about how many games are there which are
 * of specified genre. Important for populating charts in "Home" tab of GUI.
 */
public class GameCountPerGenreWrapper implements Serializable{

	private Genre genre;
	private Long value;
	
	public GameCountPerGenreWrapper(Genre genre, Long value) {
		this.genre = genre;
		this.value = value;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
