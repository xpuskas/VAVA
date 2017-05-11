package model;

import java.io.Serializable;
/**
 * Wrapper class used for retrieving info about average ranking of games
 * of specified genre. Important for populating charts in "Home" tab of GUI.
 */
public class AvgGenreRatingWrapper implements Serializable{
	private Genre genre;
	private double value;
	
	public AvgGenreRatingWrapper(Genre genre, double value) {
		this.genre = genre;
		this.value = value;
	}

	public Genre getGenreName() {
		return genre;
	}

	public void setGenreName(Genre genre) {
		this.genre = genre;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	} 
}