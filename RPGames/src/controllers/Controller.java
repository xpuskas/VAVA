package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import application.Main;
import application.Utility;
import fetcher.FetcherBeanRemote;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;
import model.OfficialGame;
import updater.UpdaterBeanRemote;

public class Controller implements Initializable {

	@FXML
	ImageView image1;
	@FXML
	ImageView image2;
	@FXML
	ImageView image3;
	@FXML
	ImageView image4;
	@FXML
	ImageView imageBig;
	@FXML
	Text desc;
	@FXML
	Label label1;
	@FXML
	Label label2;
	@FXML
	Label label3;
	@FXML
	Label label4;
	@FXML
	Label labelBig;
	@FXML
	PieChart chart;
	@FXML
	PieChart chart_studio;
	
	@FXML
	TextField searchBox;
	@FXML
	TextField studio;
	@FXML
	ComboBox<String> genre;
	@FXML
	ComboBox<String> year;
	@FXML
	CheckBox high_ranked;
	@FXML
	ListView<String> recent;
	@FXML
	TabPane tabs;
	@FXML
	GameProfileController tab_gpController;
	@FXML
	ShowReviewController tab_srvController;

	
	List<ImageView> screenshots;
	List<Label> labels;
	List<OfficialGame> recents = null;
	
	private int counter = 0;
	private Timer timer;
	private static Controller controller = null;
	
	private Stage stage;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		controller = this;  //TODO
		
		tab_gpController.setController(tab_srvController);  //TODO nested controller reference
		tab_gpController.setTabs(tabs);
		
		screenshots = new ArrayList<ImageView>();
		labels = new ArrayList<Label>();
		
		screenshots.add(image1);
		screenshots.add(image2);
		screenshots.add(image3);
		screenshots.add(image4);
		
		labels.add(label1);
		labels.add(label2);
		labels.add(label3);
		labels.add(label4);
		
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		
		year.getItems().add("Any");
		for (int i = current_year; i >= 1995; i--) {
			year.getItems().add(Integer.toString(i));
		}
		
	//	handleLists();
		
		Context context = null;
		try {
			context = EJBContext.createRemoteEjbContext("localhost", "8080");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FetcherBeanRemote fetcher = null;
		try {
			fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		recents = fetcher.mostRecentGames(4);
		Image img = null;
		
		for (int i = 0; i < recents.size(); i++) {
			img = Utility.byte2Image(recents.get(i).getImage());
			screenshots.get(i).setImage(img);
			labels.get(i).setText(recents.get(i).getName());
			desc.setText(recents.get(i).getDescription());
		}
		
		if(recents.size() > 0) {
			labelBig.setText(recents.get(0).getName());
			desc.setText(recents.get(0).getDescription());
			imageBig.setImage(Utility.byte2Image(recents.get(0).getImage()));
		}
		
		   ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("Skyrim", 30),
	                new PieChart.Data("Oblivion", 20),
	                new PieChart.Data("Morrowind", 10),
		   			new PieChart.Data("Portal 2", 25),
	                new PieChart.Data("Ace Combat 7", 15));
		   			
		chart.setData(pieChartData);
		
		ObservableList<PieChart.Data> pieChartData2 =
                FXCollections.observableArrayList(
                new PieChart.Data("Bethesda", 30),
                new PieChart.Data("Valve", 20),
                new PieChart.Data("Namco", 10));
	   			
	chart_studio.setData(pieChartData2);
	
	
	timer = new Timer();
	timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			if(counter > 3)
				counter = 0;
			
			changeCover(recents.get(counter++));
		  }
		}, 3000, 3000);   
	
	
	recent.getItems().add("BB");
	
		//TODO - Lukas was here
		List<String> genres = fetcher.getAllGenres();
		genre.getItems().add("Any");
		genre.getItems().addAll(genres);
		
		refreshLastViewedGames(fetcher);

	}
	
	public static void refreshLastViewedGames(FetcherBeanRemote fetcher) {
		List<String> lastViewedGames = fetcher.getLastViewedGameNamesByUser(Main.getUserName(), -1);
		controller.recent.getItems().clear();;
		controller.recent.getItems().addAll(lastViewedGames);
	}
	
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	public void handleLists() {
		Image foto = new Image("file:foto1.jpg");
		Image solco = new Image("file:foto.jpg");
		
		
		for (ImageView img: screenshots) {
			img.setImage(foto);
		}
		
		image3.setImage(solco);
		
		for (Label lbl: labels) {
			lbl.setText("Skyrim");
		}
	}
	
	
	public void handleImage1Click() {
		timer.cancel();
		imageBig.setImage(image1.getImage());
		labelBig.setText(recents.get(0).getName());
		desc.setText(recents.get(0).getDescription());
	}
	
	public void handleImage2Click() {
		timer.cancel();
		imageBig.setImage(image2.getImage());
		labelBig.setText(recents.get(1).getName());
		desc.setText(recents.get(1).getDescription());
	}
	
	public void handleImage3Click() {
		timer.cancel();
		imageBig.setImage(image3.getImage());
		labelBig.setText(recents.get(2).getName());
		desc.setText(recents.get(2).getDescription());
	}
	
	public void handleImage4Click() {
		timer.cancel();
		imageBig.setImage(image4.getImage());
		labelBig.setText(recents.get(3).getName());
		desc.setText(recents.get(3).getDescription());
	}
	
	
	public void handleLabel1Click() throws NamingException {
    	tab_gpController.setDisplayedGame(recents.get(0));
		tab_gpController.populate();
    	tabs.getSelectionModel().selectNext();
	}
	
	public void handleLabel2Click() throws NamingException {
    	tab_gpController.setDisplayedGame(recents.get(1));
		tab_gpController.populate();
    	tabs.getSelectionModel().selectNext();
	}
	
	public void handleLabel3Click() throws NamingException {
    	tab_gpController.setDisplayedGame(recents.get(2));
		tab_gpController.populate();
    	tabs.getSelectionModel().selectNext();
	}
	
	public void handleLabel4Click() throws NamingException {
    	tab_gpController.setDisplayedGame(recents.get(3));
		tab_gpController.populate();
    	tabs.getSelectionModel().selectNext();
	}
	
	
	public void handleLabelBigClick() throws NamingException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		
		tab_gpController.setDisplayedGame(fetcher.getGameByName(labelBig.getText()));
		tab_gpController.populate();
    	tabs.getSelectionModel().selectNext();
	}
	//This will prepare parameters for searching filtered games
	private List<String> formatSearchParameters() {
		
		List<String> parameters = new ArrayList<String>(5);
		
		//Name
		String temp = searchBox.getText();
		parameters.add(temp.length() == 0  ? null : temp);
		//Genre
		try {
			temp = genre.getValue();
			if(("Any").equals(temp)) {
				temp = null;
			}
		} catch(NullPointerException | IndexOutOfBoundsException e) {
			temp = null;
		}
		parameters.add(temp);
		//Studio
		temp = studio.getText();
		parameters.add(temp.length() == 0 ? null : temp);
		//Year
		try {
			temp = year.getValue();
			if(("Any").equals(temp)) {
				temp = null;
			}
		} catch(NullPointerException | IndexOutOfBoundsException e) {
			temp = null;
		}
		parameters.add(temp);
		//High ranked - if it is to be applied, we just need to put in any string
		temp = high_ranked.isSelected() ? "" : null;
		parameters.add(temp);
		
		return parameters;
	}
	
	public void changeCover(OfficialGame game) {
		imageBig.setImage(Utility.byte2Image(game.getImage()));
		desc.setText(game.getDescription());
		
        Platform.runLater(new Runnable() { 
            @Override public void run() {
                labelBig.setText(game.getName());
            }
        });
	}




	public static Controller getController() {
		return controller;
	}
	


}
