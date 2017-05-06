package application;

import java.net.URL;
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
	ComboBox year_game;
	@FXML
	ComboBox year_project;
	@FXML
	TextArea desc_game;
	@FXML
	TextArea desc_project;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	
	public void submitGame() {
		System.out.println("Added new game");
	}
	
	
	public void submitProject() {
		System.out.println("Added new project");
	}
}
