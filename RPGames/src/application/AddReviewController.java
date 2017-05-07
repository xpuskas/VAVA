package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	
	private Stage stage;
	private List<TextField> pros;
	private List <TextField> cons;
	
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
