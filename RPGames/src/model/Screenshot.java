package model;

import java.io.Serializable;

public class Screenshot implements Serializable {
	private long screenshotID;
	private Game game;
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
