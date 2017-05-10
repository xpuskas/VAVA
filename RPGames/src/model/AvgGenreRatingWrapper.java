package model;

import java.io.Serializable;

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