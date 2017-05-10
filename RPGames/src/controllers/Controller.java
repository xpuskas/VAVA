package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import application.Main;
import application.Utility;
import configuration.PropertyManager;
import fetcher.FetcherBeanRemote;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import language.LanguageManager;
import logging.LogManager;
import model.AvgGenreRatingWrapper;
import model.GameCountPerGenreWrapper;
import model.OfficialGame;

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
	ComboBox<String> genre;
	@FXML
	ComboBox<String> year;
	@FXML
	ListView<String> recent;
	@FXML
	TabPane tabs;
	@FXML
	GameProfileController tab_gpController;
	@FXML
	ShowReviewController tab_srvController;
	@FXML
	MyGamesController tab_mgController;
	@FXML
	ShowArticleController tab_sarController;
	@FXML
	ListView<String> search_res;
	@FXML
	Label search_res_label;
	@FXML
	CheckBox radio_game;
	@FXML
	CheckBox radio_project;
	@FXML
	Label recent_games_label;
	@FXML
	Label search_l;
	@FXML
	Label gpbs;
	@FXML
	Label gpbg;
	@FXML
	Button searchbut;
	@FXML
	Tab home;
	@FXML
	Tab gp;
	@FXML
	Tab mg;
	@FXML
	Tab ga;
	@FXML
	Tab gr;
	
	
	
	List<ImageView> screenshots;
	List<Label> labels;
	List<OfficialGame> recents = null;
	
	private int counter = 0;
	private Timer timer;
	private static Controller controller = null;
	private static final String noOption = "N/A";
	private static final Logger LOGGER = LogManager.createLogger( Controller.class.getName());
	
	private Stage stage;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		controller = this;  //TODO
		
		tab_gpController.setReviewController(tab_srvController);  //TODO nested controller reference
		tab_gpController.setArticleController(tab_sarController);
		tab_gpController.setTabs(tabs);
		tab_mgController.setGPController(tab_gpController);
		tab_mgController.setTabs(tabs);
		
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
		
		year.getItems().add(noOption);
		
		for (int i = current_year; i >= Integer.parseInt(PropertyManager.getProps().getProperty("default_year")); i--) {
			year.getItems().add(Integer.toString(i));
		}
		
		search_res_label.setVisible(false);
		search_res.setVisible(false);
		
		FetcherBeanRemote fetcher = EJBControl.getFetcher();

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
		
		try {
			fillCharts();
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
		}

	
	
	timer = new Timer();
	timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			if(counter > 3)
				counter = 0;
			
			try {
				changeCover(recents.get(counter++));
			} catch(ArrayIndexOutOfBoundsException e) {
				LogManager.logException(LOGGER, e, true);
			}
			
		  }
		}, Integer.parseInt(PropertyManager.getProps().getProperty("timer_millis")), Integer.parseInt(PropertyManager.getProps().getProperty("timer_millis")));   
	
	

		List<String> genres = fetcher.getAllGenres();
		genre.getItems().add(noOption);
		genre.getItems().addAll(genres);
		
		refreshLastViewedGames(fetcher);
				
		radio_game.setSelected(true);
		radio_project.setSelected(true);
		
		refreshLanguageTexts();

	}
	//Ked sa zmeni jazyk, tak treba nad kazdym controllerom zavolat takuto metodu - teda nad kazdym, kde je co menit
	public void setLanguage(String language) {
		LanguageManager.setLanguage(language);
		refreshLanguageTexts();
		
		
	}
	
	public void refreshLanguageTexts() {
		recent_games_label.setText(LanguageManager.getProperty("HOME_RECENT_GAMES"));
		search_l.setText(LanguageManager.getProperty("HOME_SEARCH"));
		gpbs.setText(LanguageManager.getProperty("HOME_GPBS"));
		gpbg.setText(LanguageManager.getProperty("HOME_GPBG"));
		search_res_label.setText(LanguageManager.getProperty("HOME_SEARCH_RES_LABEL")); //
		searchBox.setPromptText(LanguageManager.getProperty("HOME_SEARCH_BOX"));
		genre.setPromptText(LanguageManager.getProperty("HOME_GENRE"));
		year.setPromptText(LanguageManager.getProperty("HOME_YEAR"));
		radio_game.setText(LanguageManager.getProperty("HOME_RGAME"));
		radio_project.setText(LanguageManager.getProperty("HOME_RPROJECT"));
		searchbut.setText(LanguageManager.getProperty("HOME_SEARCHBUT"));
		home.setText(LanguageManager.getProperty("HOME_HOME"));
		gp.setText(LanguageManager.getProperty("HOME_GAMEPROFILE"));
		mg.setText(LanguageManager.getProperty("HOME_MYGAMES"));
		ga.setText(LanguageManager.getProperty("HOME_GAMEARTICLE"));
		gr.setText(LanguageManager.getProperty("HOME_GAMEREVIEW"));
	}
	
	public static void refreshLastViewedGames(FetcherBeanRemote fetcher) {
		List<String> lastViewedGames = fetcher.getLastViewedGameNamesByUser(Main.getUserName(), -1);
		controller.recent.getItems().clear();;
		controller.recent.getItems().addAll(lastViewedGames);
	}
	
	
	public void fillCharts() throws NamingException {
		
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		List<AvgGenreRatingWrapper> avgGenreRat = fetcher.getGenreAvgRating();
		List<GameCountPerGenreWrapper> avgStudioRat = fetcher.getGameCountPerGenre();
		
		
		
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		   
		   for(AvgGenreRatingWrapper agrw: avgGenreRat) {
			   pieChartData.add(new PieChart.Data(agrw.getGenreName().getName(), agrw.getValue()));
		   }
		   			
		chart.setData(pieChartData);
		
		   ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();
		   
		   for(GameCountPerGenreWrapper asrw: avgStudioRat) {
			   pieChartData2.add(new PieChart.Data(asrw.getGenre().getName(), asrw.getValue()));
		   }
		
			chart_studio.setData(pieChartData2);  
		
	}
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	public void logOut() {
		stage.close();
	}
	
	
	public void showGameProfile() throws NamingException {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		tab_gpController.populate(fetcher.getGameByName(recent.getSelectionModel().getSelectedItem()));
		tabs.getSelectionModel().select(1);
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
	
	
	public void handleLabel1Click() {
		try {
			tab_gpController.populate(recents.get(0));
			tabs.getSelectionModel().selectNext();
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
		}
	}
	
	public void handleLabel2Click() {
		try {
			tab_gpController.populate(recents.get(1));
			tabs.getSelectionModel().selectNext();
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
		}
	}
	
	public void handleLabel3Click() {
		try {
			tab_gpController.populate(recents.get(2));
			tabs.getSelectionModel().selectNext();
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
		}
	}
	
	public void handleLabel4Click() {
		try {
			tab_gpController.populate(recents.get(3));
			tabs.getSelectionModel().selectNext();
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
		}
	}
	
	
	public void handleLabelBigClick() throws NamingException {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		try {
			tab_gpController.populate(fetcher.getGameByName(labelBig.getText()));
	    	tabs.getSelectionModel().selectNext();
		} catch (NamingException e) {
			LogManager.logException(LOGGER, e, true);
		}
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
			if((noOption).equals(temp)) {
				temp = null;
			}
		} catch(NullPointerException | IndexOutOfBoundsException e) {
			temp = null;
		}
		parameters.add(temp);


		//Year
		try {
			temp = year.getValue();
			if((noOption).equals(temp)) {
				temp = null;
			}
		} catch(NullPointerException | IndexOutOfBoundsException e) {
			temp = null;
		}
		parameters.add(temp);
		temp = null;
		parameters.add(temp);
		
		return parameters;
	}
	
	
	public void search() throws NamingException { 
		
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		search_res.getItems().clear();
		if(radio_game.isSelected() && !radio_project.isSelected()) {
			search_res.getItems().addAll(fetcher.getOfficialGameNamesByFiltration(formatSearchParameters(), Double.parseDouble(PropertyManager.getProps().getProperty("high_rank"))));
		}
		
		else if(!radio_game.isSelected() && radio_project.isSelected()) {
			search_res.getItems().addAll(fetcher.getDeveloperGameNamesByFiltration(formatSearchParameters(), Double.parseDouble(PropertyManager.getProps().getProperty("high_rank"))));
		}
		
		else {
			search_res.getItems().addAll(fetcher.getGameNamesByFiltration(formatSearchParameters(), Double.parseDouble(PropertyManager.getProps().getProperty("high_rank"))));
		}
		
		search_res_label.setVisible(true);
		search_res.setVisible(true);
	}
	
	
	public void openFoundGame() throws NamingException {   //TODO
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		tab_gpController.populate(fetcher.getGameByName(search_res.getSelectionModel().getSelectedItem()));
    	tabs.getSelectionModel().selectNext();
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
