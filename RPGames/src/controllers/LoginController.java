package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import application.LoginManager;
import application.Main;
import fetcher.FetcherBeanRemote;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	
	private Stage stage;
	private File file = null;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Client started.");
		
		birth.setValue(LocalDate.of(1995, 01, 01));
		
		
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
			a.setContentText("You have entered an incorrect username and/or password!. Please try again.");
			a.setHeaderText("");
			a.showAndWait();
			return;
		}

		//TODO Lukas was here
		//userName treba niekde uloûiù, aby sa vedelo, kto je prihl·sen˝
		//Statick· premenn· v Main?
		//Tak som to urobil, ked chceö, to zmeÚ ptm

		if(LoginManager.validate(usrname.getText(), pass.getText().toCharArray(), psswd)) {
			
			Main.setUserName(usrname.getText());
			stage.close();
	
			Main main = new Main();
			main.launchMainApp();	
		}
		
		else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("You have entered an incorrect username and/or password!. Please try again.");
			a.setHeaderText("");
			a.showAndWait();
		}
				
	}
	
	public void registerUser() throws IOException, NamingException {
		
    	if(Calendar.getInstance().get(Calendar.YEAR) - birth.getValue().getYear() < 12) {
    		Alert chyba = new Alert(AlertType.ERROR);
    		chyba.setContentText("You are too young to use this app.\nGo away, kid!");
    		chyba.showAndWait();
    		
    		throw new IOException();
    	}
    	
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		
		if(!pass_reg.getText().equals(pass_reg_2.getText())) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("The passwords you have entered do not match! Please try agagin.");
			a.setHeaderText("");
			a.showAndWait();
			throw new IOException();
		}
		
		if(pass_reg.getText().length() < 5) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("The password is too short! Minimum allowed length is 5 characters.");
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
		a.setContentText("Your account has been created. You can now sign in using the form above.");
		a.setHeaderText("");
		a.setTitle("SUCCESS");
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
