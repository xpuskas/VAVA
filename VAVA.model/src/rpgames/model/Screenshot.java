package rpgames.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Screenshot {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long screenshotID;
	@ManyToOne
	private Game game;
    @Basic
    private byte[] screenshot;
    
	public long getScreenshotID() {
		return screenshotID;
	}
	public void setScreenshotID(long screenshotID) {
		this.screenshotID = screenshotID;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public byte[] getScreenshot() {
		return screenshot;
	}
	public void setScreenshot(byte[] screenshot) {
		this.screenshot = screenshot;
	}
}
