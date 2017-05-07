package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	TextField usrname;
	@FXML
	PasswordField pass;
	@FXML
	TextField usr_reg;
	@FXML
	PasswordField pass_reg;
	@FXML
	PasswordField pass_reg_2;
	@FXML
	DatePicker birth;
	
	private Stage stage;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Client started.");
		
		birth.setValue(LocalDate.of(1995, 01, 01));
		
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void signIN() throws IOException {
		System.out.println("Successfully logged in.");

		if(LoginManager.validate(usrname.getText(), pass.getText().toCharArray())) {
			stage.close();
	
			Main main = new Main();
			main.launchMainApp();	
		}
		
		else {
			System.out.println("Try() again!!!");
		}
				
	}
	
	public void registerUser() throws IOException {
		
    	if(Calendar.getInstance().get(Calendar.YEAR) - birth.getValue().getYear() < 12) {
    		Alert chyba = new Alert(AlertType.ERROR);
    		chyba.setContentText("You are too young to use this app.\nGo away, kid!");
    		chyba.showAndWait();
    		
    		throw new IOException();
    	}  
    	
		System.out.println("User successfully registered");
	}

	
	
}
