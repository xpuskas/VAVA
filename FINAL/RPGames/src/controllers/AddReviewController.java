package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import application.EJBControl;
import fetcher.FetcherBeanRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import language.LanguageManager;
import logging.LogManager;
import model.Game;
import model.Review;
import updater.UpdaterBeanRemote;
import application.Main;

/**
 * Controller class for adding a new game review. Includes methods for uploading a cover image,
 * setting the game's pros and cons, as well as submitting the article to the server's database and more.
 */
public class AddReviewController implements Initializable {

	@FXML
	TextField title;
	@FXML
	TextField pro1;
	@FXML
	TextField pro2;
	@FXML
	TextField pro3;
	@FXML
	TextField pro4;
	@FXML
	TextField con1;
	@FXML
	TextField con2;
	@FXML
	TextField con3;
	@FXML
	TextField con4;
	@FXML
	Slider slider;
	@FXML
	TextArea text;
	
	@FXML
	Label title_l;
	@FXML
	Label pros_l;
	@FXML
	Label cons_l;
	@FXML
	Label rank_l;
	@FXML
	Button submit_b;
	@FXML
	Button upload_l;
	
	GameProfileController tab_gpController;
	
	private static final Logger LOGGER = LogManager.createLogger( AddReviewController.class.getName());
	private Stage stage;
	private List<TextField> pros;
	private List <TextField> cons;
	private Game reviewedGame;
	private File file = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pros = new ArrayList<TextField>();
		cons = new ArrayList<TextField>();
		
		pros.add(pro1);
		pros.add(pro2);
		pros.add(pro3);
		pros.add(pro4);
		
		cons.add(con1);
		cons.add(con2);
		cons.add(con3);
		cons.add(con4);	
		
		refreshLanguageTexts();
	}
		
	
	/**
	 * Sets reference to GameProfileController
	 * @param gpc - GameProfileController
	 */
	public void setGPC(GameProfileController gpc) {
		this.tab_gpController = gpc;
	}
	
	
	/**
	 * Sets labels of the elements according to chosen language.
	 */
	public void refreshLanguageTexts() {
		title.setPromptText(LanguageManager.getProperty("ADDREVIEW_TITLEPROMPT"));
		title_l.setText(LanguageManager.getProperty("ADDREVIEW_TITLE"));
		pros_l.setText(LanguageManager.getProperty("ADDREVIEW_PROS"));
		cons_l.setText(LanguageManager.getProperty("ADDREVIEW_CONS"));
		upload_l.setText(LanguageManager.getProperty("ADDREVIEW_UPLOADBUT"));
		submit_b.setText(LanguageManager.getProperty("ADDREVIEW_SUBMITBUT"));
		rank_l.setText(LanguageManager.getProperty("ADDREVIEW_RANK"));

	}
	
	/**
	 * Adds a new article to the databse. If no cover image is chosen by the user, 
	 * cover image of the game itself is automatically chosen by default.
	 */
	public void addReview() {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		UpdaterBeanRemote updater = EJBControl.getUpdater();
		
		
		Review review = new Review();
		review.setAuthor(fetcher.getUserAccountByName(Main.getUserName()));
		review.setGame(reviewedGame);
		review.setRank(slider.getValue());
		review.setText(text.getText());
		review.setTitle(title.getText());
		review.setPublished(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		
		review.setPros(buildPros());
		review.setCons(buildCons());
		
		try {
			review.setImage(file == null ? reviewedGame.getImage() : Files.readAllBytes(file.toPath()));
		} 
		
		catch (IOException e) {
			LogManager.logException(LOGGER, e, true);
			review.setImage(reviewedGame.getImage());
		}    
		
		updater.addReview(review);
		tab_gpController.refreshReviewsArticles();
		
		stage.close();
	}
	
	
	public String buildPros() {
		
		StringBuilder sb = new StringBuilder();
		
		for(TextField pro: pros) {
			if(!"".equals(pro.getText())) {
				sb.append(pro.getText());
				sb.append("|");  
			}
		}
		
		return sb.toString();
		
	}
	
	
	/**
	 * Formats the cons of the game using a specified delimiter ('|') and appends it to a string afterwards
	 * @return correctly formatted string, carrying cons of the game
	 */
	public String buildCons() {
		
		StringBuilder sb = new StringBuilder();
		
		for(TextField con: cons) {
			if(!"".equals(con.getText())) {   
				sb.append(con.getText());
				sb.append("|");
			}
		}
		
		return sb.toString();
		
	}
	
	
	/**
	 * Displays dialog for choosing a new image file and uploads it afterwards.
	 */
	public void uploadImage(){
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
	}

	
	/**
	 * Sets reference to current stage.
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	/**
	 * Sets reference to game, for which a review is about to be written.
	 * @param game - reviewed game
	 */
	public void setReviewedGame(Game game) {
		this.reviewedGame = game;
	}
}
