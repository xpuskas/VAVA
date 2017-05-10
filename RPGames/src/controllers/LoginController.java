package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import application.LoginManager;
import application.Main;
import fetcher.FetcherBeanRemote;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import language.LanguageManager;
import model.Genre;
import model.Screenshot;
import model.UserAccount;
import updater.UpdaterBeanRemote;

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
	@FXML
	Label username;
	@FXML
	Label password;
	@FXML
	Label new_username;
	@FXML
	Label new_password;
	@FXML
	Label new_account_encourage;
	@FXML
	Label birth_day;
	@FXML
	Button sign_in;
	@FXML
	Button register;
	@FXML
	Button upload_profile_pic;
	@FXML
	ComboBox<String> language;
	
	private Stage stage;
	private File file = null;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("Client started.");
		
		birth.setValue(LocalDate.of(1995, 01, 01));
// TODO		language.getItems().addAll(LanguageManager.getLanguageOptions());
		language.getItems().add("slovak_s");
		language.getItems().add("english_s");
		
    	/**
         * @author Steve Park
         * URL: http://stackoverflow.com/questions/14522680/javafx-choicebox-events
         */
        
        language.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			
        	setLanguage(language.getSelectionModel().getSelectedItem());  
		} );	
       
		
		refreshLanguageTexts();
	}
	//TODO Toto spustiù po vybranÌ jazyka
	//Na tie jazyky bude dropdown. Moûnosti doÚ dostaneö LanguageManager.getLanguageOptions()
	//Kam d·ö listener na nastavenie, to je mi aj jedno, ale nech zavol· t˙to metodu(pod tymto riadkom)
	public void setLanguage(String language) {
		LanguageManager.setLanguage(language);
		refreshLanguageTexts();
	}
	
	public void refreshLanguageTexts() {
		
		username.setText(LanguageManager.getProperty("LOGIN_LOGIN"));
		password.setText(LanguageManager.getProperty("LOGIN_PASSWORD"));
		new_username.setText(LanguageManager.getProperty("LOGIN_NEW_LOGIN"));
		new_password.setText(LanguageManager.getProperty("LOGIN_NEW_PASSWORD"));
		new_account_encourage.setText(LanguageManager.getProperty("LOGIN_CREATE_ACCOUNT"));
		birth_day.setText(LanguageManager.getProperty("LOGIN_BIRTHDAY"));
		sign_in.setText(LanguageManager.getProperty("LOGIN_SIGN_IN"));
		register.setText(LanguageManager.getProperty("LOGIN_REGISTER"));
		upload_profile_pic.setText(LanguageManager.getProperty("LOGIN_PROFILE_PIC"));
		
		usrname.setPromptText(LanguageManager.getProperty("LOGIN_ENTER_LOGIN"));
		pass.setPromptText(LanguageManager.getProperty("LOGIN_ENTER_PASSWORD"));
		usr_reg.setPromptText(LanguageManager.getProperty("LOGIN_CHOOSE_LOGIN"));
		pass_reg.setPromptText(LanguageManager.getProperty("LOGIN_CHOOSE_PASSWORD"));
		pass_reg_2.setPromptText(LanguageManager.getProperty("LOGIN_RETYPE_PASSWORD"));
		birth.setPromptText(LanguageManager.getProperty("LOGIN_ENTER_BIRTH"));
		language.setPromptText(LanguageManager.getProperty("LOGIN_LANGMENU"));
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void signIN() throws IOException, NamingException {
		
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		char[] psswd = null;
		
		try {
		psswd = fetcher.getUserAccountByName(usrname.getText()).getPassword().toCharArray();
		}
	
		catch (NullPointerException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(LanguageManager.getProperty("LOGIN_ALERT_INCORRECT"));
			a.setHeaderText("");
			a.showAndWait();
			return;
		}

		if(LoginManager.validate(usrname.getText(), pass.getText().toCharArray(), psswd)) {
			
			Main.setUserName(usrname.getText());
			stage.close();
	
			Main main = new Main();
			main.launchMainApp();	
		}
		
		else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(LanguageManager.getProperty("LOGIN_ALERT_INCORRECT"));
			a.setHeaderText("");
			a.showAndWait();
		}
				
	}
	
	public void registerUser() throws IOException, NamingException {
		
    	if(Calendar.getInstance().get(Calendar.YEAR) - birth.getValue().getYear() < 12) {
    		Alert chyba = new Alert(AlertType.ERROR);
    		chyba.setContentText(LanguageManager.getProperty("LOGIN_LOW_AGE"));
    		chyba.showAndWait();
    		
    		throw new IOException();
    	}
    	
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		
		if(!pass_reg.getText().equals(pass_reg_2.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(LanguageManager.getProperty("LOGIN_REGISTER_PASSWORD_MISMATCH"));
			a.setHeaderText("");
			a.showAndWait();
			throw new IOException();
		}
		
		if(pass_reg.getText().length() < 5) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(LanguageManager.getProperty("LOGIN_SHORT_PASSWORD"));
			a.setHeaderText("");
			a.showAndWait();
			throw new IOException();
		}
		
		if (file == null) {
			System.out.println("No file has been chosen!");
			file = new File("foto.jpg");   //TODO
		}
		
		UserAccount usr = new UserAccount(usr_reg.getText(), pass_reg.getText(), Date.valueOf(birth.getValue()));
		usr.setProfilePic(Files.readAllBytes(file.toPath()));
		
    	updater.addUser(usr);
    	
		System.out.println("User has been successfully registered");
		
		usr_reg.clear();
		pass_reg.clear();
		pass_reg_2.clear();
		
		Alert a = new Alert(AlertType.INFORMATION);
		a.setContentText(LanguageManager.getProperty("LOGIN_REPORT_CREATE_SUCCESS"));
		a.setHeaderText("");
		a.setTitle(LanguageManager.getProperty("LOGIN_SUCCESS"));
		a.showAndWait();
	}

	
	public File uploadProfilePicture() throws IOException, NamingException {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
		
		if (file == null) {
			System.out.println("No file has been chosen!");
			file = new File("foto.jpg");   //TODO
		}
		
		else {
			System.out.println(file.getName());
		}
		
		return file;
	}
	
	
}
