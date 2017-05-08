package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import application.Utility;
import fetcher.FetcherBeanRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Game;
import model.Review;
import updater.UpdaterBeanRemote;

public class ShowReviewController implements Initializable {

	@FXML
	Label title;
	@FXML
	Label rank;
	@FXML
	Label pro1;
	@FXML
	Label pro2;
	@FXML
	Label pro3;
	@FXML
	Label pro4;
	@FXML
	Label con1;
	@FXML
	Label con2;
	@FXML
	Label con3;
	@FXML
	Label con4;
	@FXML
	Text text;
	@FXML
	ImageView cover;
	@FXML
	VBox vbox;
	
	private Review displayedReview;
	List<Label> pro_holders;
	List<Label> con_holders;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		pro_holders = new ArrayList<Label>();
		con_holders = new ArrayList<Label>();
		
		pro_holders.add(pro1);
		pro_holders.add(pro2);
		pro_holders.add(pro3);
		pro_holders.add(pro4);
		
		con_holders.add(con1);
		con_holders.add(con2);
		con_holders.add(con3);
		con_holders.add(con4);
				
	}
	
	
	public void setDisplayedReview(Review displayedReview) {
		this.displayedReview = displayedReview;
	}
	
	
	public void populate() throws NamingException {
	/* 	Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");   */  //TODO
		
		title.setText(displayedReview.getTitle());
		rank.setText(((Double)displayedReview.getRank()).toString());
		cover.setImage(Utility.byte2Image(displayedReview.getImage()));
		
		text.setText(displayedReview.getText());
		displayPros();
		displayCons();
		
		vbox.setVisible(true);
		
	}
	
	
	public void displayPros() {
		String pros = displayedReview.getPros();
		String[] pro_s = pros.split("\\|");
		
		System.out.println(pro_s.length);
		
		
		for (int i = 0; i < pro_s.length; i++) {
			pro_holders.get(i).setVisible(true);
			pro_holders.get(i).setText("+ " + pro_s[i]);
		}
		
		for (int i = pro_s.length; i < pro_holders.size(); i++) {
			pro_holders.get(i).setVisible(false);
		}  
	}
	
	
	public void displayCons() {
		String cons = displayedReview.getCons();
		String[] con_s = cons.split("\\|");
		
		for (int i = 0; i < con_s.length; i++) {
			con_holders.get(i).setVisible(true);
			con_holders.get(i).setText("- " + con_s[i]);
		}
		
		for (int i = con_s.length; i < con_holders.size(); i++) {
			con_holders.get(i).setVisible(false);
		}
	}
}
