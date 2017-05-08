package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import fetcher.FetcherBeanRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Game;
import model.Review;
import updater.UpdaterBeanRemote;
import application.Main;

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
	private Game reviewedGame;
	private File file = null;
	
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
	
	public void setReviewedGame(Game game) {
		this.reviewedGame = game;
	}
	
	
	public void addReview() throws NamingException, IOException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		
		
		Review review = new Review();
		review.setAuthor(fetcher.getUserAccountByName(Main.getUserName()));
		review.setGame(reviewedGame);
		review.setRank(slider.getValue());
		review.setText(text.getText());
		review.setTitle(title.getText());
		review.setPublished(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		
		review.setPros(buildPros());
		review.setCons(buildCons());
		
		try {
			review.setImage(file == null ? reviewedGame.getImage() : Files.readAllBytes(file.toPath()));
		} 
		
		catch (IOException e) {
			e.printStackTrace();
			review.setImage(reviewedGame.getImage());
		}    
		
	//	review.setImage(Files.readAllBytes(file.toPath()));
		
		updater.addReview(review);
		
		stage.close();
	}
	
	
	public String buildPros() {
		StringBuilder sb = new StringBuilder();
		
		for(TextField pro: pros) {
			if(!pro.getText().equals("")) {
				sb.append(pro.getText());
				sb.append("|");   //TODO delimiter pipe
			}
		}
		
		return sb.toString();
		
	}
	
	
	public String buildCons() {
		StringBuilder sb = new StringBuilder();
		
		for(TextField con: cons) {
			if(!con.getText().equals("")) {
				sb.append(con.getText());
				sb.append("|");
			}
		}
		
		return sb.toString();
		
	}
	
	
	public void uploadImage() throws IOException, NamingException {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
		
	/*	if (file == null) {
			System.out.println("No file has been chosen!");  //TODO
			throw new IOException();
		}
		
		else {
			System.out.println(file.getName());
		}    */
	}

}
