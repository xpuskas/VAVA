package application;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 1995; i <= current_year; i++) {
			year_game.getItems().add(i);
			year_project.getItems().add(i);
		}
	}

	
	public void submitGame() {
		System.out.println("Added new game");
	}
	
	
	public void submitProject() {
		System.out.println("Added new project");
	}
}
