package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import application.EJBControl;
import application.Main;
import fetcher.FetcherBeanRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import language.LanguageManager;
import logging.LogManager;
import model.Article;
import model.Game;
import updater.UpdaterBeanRemote;

/**
 * Controller class for adding a new article about a game. Includes methods for uploading a cover image, submitting
 * the article to the server's database and more.
 *
 */
public class AddArticleController implements Initializable {

	@FXML
	TextField title;
	@FXML
	TextArea text;
	@FXML
	Label title_l;
	@FXML
	Button upload_b;
	@FXML
	Button submit_b;
	
	GameProfileController tab_gpController;
	
	private static final Logger LOGGER = LogManager.createLogger( AddArticleController.class.getName() );
	private Stage stage;
	private File file = null;
	private Game articleedGame;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshLanguageTexts();
	}
	
	
	/**
	 * Sets reference to current stage.
	 * @param stage - main windows stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	/**
	 * Sets reference to GameProfileController. 
	 * @param gpc - GameProfileController
	 */
	public void setGPC(GameProfileController gpc) {
		this.tab_gpController = gpc;
	}
	
	/**
	 * Sets reference to game, for which an article is about to be written.
	 * @param game Game
	 */
	public void setArticleedGame(Game game) {
		this.articleedGame = game;
	}
	
	
	
	 /**
	  * Sets language of the application and refreshes the view.
	  * @param language
	  */
	public void setLanguage(String language) {
		LanguageManager.setLanguage(language);
		refreshLanguageTexts();
	}
	
	
	/**
	 * Sets labels of the elements according to chosen language.
	 */
	public void refreshLanguageTexts() {
		title_l.setText(LanguageManager.getProperty("ADDARTICLE_TITLE"));
		upload_b.setText(LanguageManager.getProperty("ADDARTICLE_UPLOAD"));
		submit_b.setText(LanguageManager.getProperty("ADDARTICLE_SUBMIT"));

	}

	/**
	 * Adds a new article to the databse. If no cover image is chosen by the user, 
	 * cover image of the game itself is automatically chosen by default.
	 */
	public void addArticle() {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		UpdaterBeanRemote updater = EJBControl.getUpdater();
		
		
		Article article = new Article();
		article.setAuthor(fetcher.getUserAccountByName(Main.getUserName()));
		article.setGame(articleedGame);
		article.setText(text.getText());
		article.setTitle(title.getText());
		article.setPublished(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		
		try {
			article.setImage(file == null ? articleedGame.getImage() : Files.readAllBytes(file.toPath()));
		} 
		
		catch (IOException e) {
			LogManager.logException(LOGGER, e, true);
			article.setImage(articleedGame.getImage());
		}    
		
		updater.addArticle(article);
		tab_gpController.refreshReviewsArticles();
		
		stage.close();
	}
	
	
	/**
	 * Displays dialog for choosing a new image file and uploads it afterwards.
	 */
	public void uploadCover() {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
		
	}
}
