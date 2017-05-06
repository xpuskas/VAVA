package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddReviewController implements Initializable {

	@FXML
	TextField title;
	
	private Stage stage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(title.getText());
		
	}
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void as() {
		System.out.println("as");
	}
}
