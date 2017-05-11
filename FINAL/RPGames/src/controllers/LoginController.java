package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.naming.NamingException;

import application.EJBControl;
import application.LoginManager;
import application.Main;
import configuration.PropertyManager;
import fetcher.FetcherBeanRemote;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import language.LanguageManager;
import logging.LogManager;
import model.UserAccount;
import updater.UpdaterBeanRemote;


/**
 * Controller class for the login window. Includes method for signing the user in, creating a new user account
 * and uploading a profile picture.
 *
 */
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
	private static final Logger LOGGER = LogManager.createLogger(LoginController.class.getName());
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("Client started.");
		
		birth.setValue(LocalDate.of(1995, 01, 01));
		LanguageManager.initialize();
		language.getItems().addAll(LanguageManager.getLanguageOptions());	
    	
		/**
         * @author Steve Park
         * URL: http://stackoverflow.com/questions/14522680/javafx-choicebox-events
         */
        
        language.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			
        	setLanguage(language.getSelectionModel().getSelectedItem());  
		} );	
       
		
		refreshLanguageTexts();
	}
	

	/**
	 * Sets the language of the whole application.
	 * @param language - selected language
	 */
	public void setLanguage(String language) {
		LanguageManager.setLanguage(language);
		refreshLanguageTexts();
	}  
	
	
	/**
	 * Sets labels of the elements according to chosen language.
	 */
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
	
	
	/**
	 * Sets the reference to the current stage.
	 * @param stage - current stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	/**
	 * Signs the user into the application. If the provided login credentials are invalid,
	 * it shows an error popup message instead.
	 * @throws NamingException
	 */
	public void signIN() throws NamingException {
		
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		char[] psswd = null;
		
		try {
			psswd = fetcher.getUserAccountByName(usrname.getText()).getPassword().toCharArray();
		}
	
		catch (NullPointerException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(LanguageManager.getProperty("LOGIN_ALERT_INCORRECT"));
			a.setHeaderText("");
			a.showAndWait();
			LogManager.logException(LOGGER, e, true);
			return;
		}

		if(LoginManager.validate(usrname.getText(), pass.getText().toCharArray(), psswd)) {
			
			Main.setUserName(usrname.getText());
			stage.close();
	
			Main main = new Main();
			try {
				main.launchMainApp();
			} catch (IOException e) {
				LogManager.logException(LOGGER, e, true);
			}	
		}
		
		else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(LanguageManager.getProperty("LOGIN_ALERT_INCORRECT"));
			a.setHeaderText("");
			a.showAndWait();
		}
				
	}
	
	
	/**
	 * Creates a new account for the user using the specified values including username,
	 * password, date of birth and user's profile image. In case the
	 * user doesn't specify any profile picture, a default one will be uset instead.
	 * If the user enters chooses a password
	 * that is too short (< 5 characters) or the entered passwords do not match, a popup error message
	 * is displayed.
	 */
	public void registerUser() { 
		
    	if(Calendar.getInstance().get(Calendar.YEAR) - birth.getValue().getYear() < Integer.parseInt(PropertyManager.getProps().getProperty("min_age"))) {
    		Alert chyba = new Alert(AlertType.ERROR);
    		chyba.setContentText(LanguageManager.getProperty("LOGIN_LOW_AGE"));
    		chyba.showAndWait();
    	}
    	
		UpdaterBeanRemote updater = EJBControl.getUpdater();
		
		if(!pass_reg.getText().equals(pass_reg_2.getText())) {

			try {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText(LanguageManager.getProperty("LOGIN_REGISTER_PASSWORD_MISMATCH"));
				a.setHeaderText("");
				a.showAndWait();
				throw new IOException();
			} catch (IOException e) {
				LogManager.logException(LOGGER, e, true);
				return;
			}	
		}
		
		if(pass_reg.getText().length() < 5) {
			try {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText(LanguageManager.getProperty("LOGIN_SHORT_PASSWORD"));
				a.setHeaderText("");
				a.showAndWait();
				throw new IOException();
			} catch (IOException e) {
				LogManager.logException(LOGGER, e, true);
				return;
			}
			

		}
		
		if (file == null) {
			file = new File(PropertyManager.getProps().getProperty("default_pic")); 
		}
		
		UserAccount usr = new UserAccount(usr_reg.getText(), pass_reg.getText(), Date.valueOf(birth.getValue()));
		
		try {
			usr.setProfilePic(Files.readAllBytes(file.toPath()));
			
	    	updater.addUser(usr);
			
			usr_reg.clear();
			pass_reg.clear();
			pass_reg_2.clear();
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText(LanguageManager.getProperty("LOGIN_REPORT_CREATE_SUCCESS"));
			a.setHeaderText("");
			a.setTitle(LanguageManager.getProperty("LOGIN_SUCCESS"));
			a.showAndWait();
			
		} catch (IOException e) {
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText(LanguageManager.getProperty("LOGIN_REPORT_NO_PIC"));
			a.setHeaderText("");
			a.setTitle(LanguageManager.getProperty("LOGIN_REPORT_NO_PIC_HEADER"));
			a.showAndWait();
			LogManager.logException(LOGGER, e, true);
		}
		
	}

	
	/**
	 * Uploads a new profile picture for the user. If the user dismisses the chooser dialog, a default
	 * picture will be selected instead.
	 * @return - profile picture
	 */
	public File uploadProfilePicture(){
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
		
		try {
				if (file == null) {
					file = new File(PropertyManager.getProps().getProperty("default_pic"));  
				}
		} catch (NullPointerException e) {
			LogManager.logException(LOGGER, e, true);
		}
		
		return file;
	}
	
	
}
