package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddArticleController implements Initializable {

	@FXML
	TextField title;
	@FXML
	TextArea text;
	
	private Stage stage;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	
	public void uploadCover() throws IOException {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		
		if (file == null) {
			System.out.println("No file has been chosen!");
			throw new IOException();
		}
		
		else {
			System.out.println(file.getName());
		}
		
	}
}
