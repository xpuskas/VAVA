package model;

public class OfficialGameWrapper {

	String title;
	String studio;
	String genre;
	
	public OfficialGameWrapper(String title, String studio, String genre) {
		super();
		this.title = title;
		this.studio = studio;
		this.genre = genre;
	}
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getStudio() {
		return studio;
	}


	public void setStudio(String studio) {
		this.studio = studio;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
