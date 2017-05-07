package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import fetcher.FetcherBeanRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import model.OfficialGame;
import updater.UpdaterBeanRemote;

public class MyGamesController implements Initializable {

	@FXML
	TableView table;
	@FXML
	ListView projects;
	@FXML
	TextField title_game;
	@FXML
	TextField title_project;
	@FXML
	ComboBox genre_game;
	@FXML
	ComboBox genre_project;
	@FXML
	TextField studio;
	@FXML
	ComboBox<Integer> year_game;
	@FXML
	ComboBox<Integer> year_project;
	@FXML
	TextArea desc_game;
	@FXML
	TextArea desc_project;
	
	private File file = null;;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 1995; i <= current_year; i++) {
			year_game.getItems().add(i);
			year_project.getItems().add(i);
		}
		
		
	}

	
	public void submitGame() throws NamingException, IOException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		
		if (file == null) {
			System.out.println("No file has been chosen!");
			throw new IOException();
		}
		
		System.out.println(desc_game.getText());
		OfficialGame game = new OfficialGame(title_game.getText(), (short)(year_game.getValue().intValue()), desc_game.getText(), fetcher.getGenreByName("RPG"), studio.getText(), fetcher.getUserAccountByName("xtrex"), new java.sql.Date(Calendar.getInstance().getTime().getTime()), Files.readAllBytes(file.toPath()));
		
		updater.addGame(game);
		
		System.out.println("Added new game");
	}
	
	
	public void submitProject() {
		System.out.println("Added new project");
	}
	
	
	public void uploadImage() throws IOException, NamingException {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
		
		if (file == null) {
			System.out.println("No file has been chosen!");
			throw new IOException();
		}
		
		else {
			System.out.println(file.getName());
		}
	}
}
