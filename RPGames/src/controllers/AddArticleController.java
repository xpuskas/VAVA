package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
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
import model.Article;
import model.Game;
import model.Review;
import updater.UpdaterBeanRemote;

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
	
	private Stage stage;
	private File file = null;
	private Game articleedGame;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshLanguageTexts();
		
	}
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	public void setArticleedGame(Game game) {
		this.articleedGame = game;
	}
	
	
	//Ked sa zmeni jazyk, tak treba nad kazdym controllerom zavolat takuto metodu - teda nad kazdym, kde je co menit
	public void setLanguage(String language) {
		LanguageManager.setLanguage(language);
		refreshLanguageTexts();
	}
	
	public void refreshLanguageTexts() {
		title_l.setText(LanguageManager.getProperty("ADDARTICLE_TITLE"));
		upload_b.setText(LanguageManager.getProperty("ADDARTICLE_UPLOAD"));
		submit_b.setText(LanguageManager.getProperty("ADDARTICLE_SUBMIT"));

	}

	
	public void addArticle() throws NamingException, IOException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		
		
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
			e.printStackTrace();
			article.setImage(articleedGame.getImage());
		}    
		
		updater.addArticle(article);
		
		stage.close();
	}
	
	
	
	public void uploadCover() throws IOException {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
		
	/*	if (file == null) {
			System.out.println("No file has been chosen!");  //TODO
			throw new IOException();
		}
		
		else {
			System.out.println(file.getName());
		}    */
		
	}
}
