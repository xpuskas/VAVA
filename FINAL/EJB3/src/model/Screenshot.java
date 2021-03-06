package model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
/**
 * Model class instances of which can be persisted by server.
 * Represents an article about a {@link model.Game} object, which is enhanced
 * by explicitly enumerating pros and cons of the game and adding rank
 * which represents author�s overall opinion on the game.
 */
public class Screenshot implements Serializable {
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
