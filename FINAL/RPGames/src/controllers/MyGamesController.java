package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.naming.NamingException;

import application.EJBControl;
import application.Main;
import configuration.PropertyManager;
import fetcher.FetcherBeanRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import language.LanguageManager;
import logging.LogManager;
import model.DeveloperGame;
import model.Genre;
import model.OfficialGame;
import model.OfficialGameWrapper;
import updater.UpdaterBeanRemote;


/**
 * Controller class for the My Games tab. Ir primarily the user to add new games and projects to
 * the application's database, including parameters like genre, studio, cover image and more. It also
 * displays games and projects, which have been added by the user.  The initialize method prepares lists of various GUI elements for
 * easier management and populates some of the drop down menus.
 *
 */
public class MyGamesController implements Initializable {

	@FXML
	TableView<OfficialGameWrapper> table;
	@FXML
	TableColumn<OfficialGameWrapper, String> t_title;
	@FXML
	TableColumn<OfficialGameWrapper, String> t_genre;  
	@FXML
	TableColumn<OfficialGameWrapper, String> t_studio;
	@FXML
	ListView<String> projects;
	@FXML
	TextField title_game;
	@FXML
	TextField title_project;
	@FXML
	ComboBox<String> genre_game;
	@FXML
	ComboBox<String> genre_project;
	@FXML
	TextField studio;
	@FXML
	ComboBox<Integer> year_game;
	@FXML
	ComboBox<Integer> year_project;
	@FXML
	TextArea desc_game;
	@FXML
	TextArea desc_project;
	
	@FXML
	Label bymeadded_l;
	@FXML
	Label myprojects_l;
	@FXML
	Tab addgame_t;
	@FXML
	Tab addproject_t;
	@FXML
	Label g_title_l;
	@FXML
	Label g_genre_l;
	@FXML
	Label g_studio_l;
	@FXML
	Label g_year_l;
	@FXML
	Label g_desc_l;
	@FXML
	Label g_up_l;
	@FXML
	Button g_up_b;
	@FXML 
	Button g_sub_b;
	@FXML
	Label p_title_l;
	@FXML
	Label p_genre_l;
	@FXML
	Label p_year_l;
	@FXML
	Label p_desc_l;
	@FXML
	Label p_up_l;
	@FXML
	Button p_up_b;
	@FXML 
	Button p_sub_b;
	
	
	
	
	private File file = null;
	private File file_project = null;
	private GameProfileController gp_controller;
	private TabPane tabs;
	
	private int current_year;
	
	private static final Logger LOGGER = LogManager.createLogger(MyGamesController.class.getName());
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
        t_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        t_genre.setCellValueFactory(new PropertyValueFactory<>("genre")); 
        t_studio.setCellValueFactory(new PropertyValueFactory<>("studio"));
		
		current_year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = current_year; i >= Integer.parseInt(PropertyManager.getProps().getProperty("default_year")); i--) {
			year_game.getItems().add(i);
			year_project.getItems().add(i);
		}
		

		FetcherBeanRemote fetcher = EJBControl.getFetcher();

		
		List<String> genres = fetcher.getAllGenres();
		genre_game.getItems().add("Any");
		genre_game.getItems().addAll(genres);
		genre_project.getItems().add("Any");
		genre_project.getItems().addAll(genres);
		

		refreshOfficialGames(fetcher);
		refreshDeveloperGames(fetcher);
		
		refreshLanguageTexts();
	}
	
	
	
	/**
	 * Sets labels of the elements according to chosen language.
	 */
	public void refreshLanguageTexts() {
		bymeadded_l.setText(LanguageManager.getProperty("MYGAMES_BYMEADDED"));
		myprojects_l.setText(LanguageManager.getProperty("MYGAMES_MYPROJECTS"));
		addgame_t.setText(LanguageManager.getProperty("MYGAMES_ADDGAME"));
		addproject_t.setText(LanguageManager.getProperty("MYGAMES_ADDPROJECT"));
		g_title_l.setText(LanguageManager.getProperty("MYGAMES_GAMETITLE"));
		g_genre_l.setText(LanguageManager.getProperty("MYGAMES_GAMEGENRE"));
		g_studio_l.setText(LanguageManager.getProperty("MYGAMES_GAMESTUDIO"));
		g_year_l.setText(LanguageManager.getProperty("MYGAMES_GAMEYEAR"));
		g_desc_l.setText(LanguageManager.getProperty("MYGAMES_GAMEDESC"));
		g_up_l.setText(LanguageManager.getProperty("MYGAMES_GAMEUPLOAD"));
		g_up_b.setText(LanguageManager.getProperty("MYGAMES_GAMECHOOSE"));
		g_sub_b.setText(LanguageManager.getProperty("MYGAMES_GAMESUBMIT"));
		p_title_l.setText(LanguageManager.getProperty("MYGAMES_GAMETITLE"));
		p_genre_l.setText(LanguageManager.getProperty("MYGAMES_GAMEGENRE"));
		p_year_l.setText(LanguageManager.getProperty("MYGAMES_GAMEYEAR"));
		p_desc_l.setText(LanguageManager.getProperty("MYGAMES_GAMEDESC"));
		p_up_l.setText(LanguageManager.getProperty("MYGAMES_GAMEUPLOAD"));
		p_up_b.setText(LanguageManager.getProperty("MYGAMES_GAMECHOOSE"));
		p_sub_b.setText(LanguageManager.getProperty("MYGAMES_GAMESUBMIT"));
		title_game.setPromptText(LanguageManager.getProperty("MYGAMES_GAMETITLEPROMPT"));
		studio.setPromptText(LanguageManager.getProperty("MYGAMES_GAMESTUDIOPROMPT"));
		title_project.setPromptText(LanguageManager.getProperty("MYGAMES_PROJECTTITLEPROMPT"));
		genre_game.setPromptText(LanguageManager.getProperty("MYGAMES_GAMEGENREPROMPT"));
		genre_project.setPromptText(LanguageManager.getProperty("MYGAMES_GAMEGENREPROMPT"));
		t_title.setText(LanguageManager.getProperty("MYGAMES_GAMETITLE"));
		t_genre.setText(LanguageManager.getProperty("MYGAMES_GAMEGENRE"));
		t_studio.setText(LanguageManager.getProperty("MYGAMES_GAMESTUDIO"));

	}
	
	
	/**
	 * Refreshes the list of official games, which have been added by the user and updates the
	 * view accrordingly.
	 * @param fetcher - instance of FetcherBeanRemote
	 */
	private void refreshOfficialGames(FetcherBeanRemote fetcher) {
		List<OfficialGame> gamesAdded = fetcher.getOfficialGamesAddedByUser(Main.getUserName());
		
		ObservableList<OfficialGameWrapper> table_list = FXCollections.observableArrayList();
		OfficialGameWrapper ogw = null;
		
		for(OfficialGame off: gamesAdded) {
			ogw = new OfficialGameWrapper(off.getName(), off.getStudio(), off.getGenre().getName());
			table_list.add(ogw);
		}
		
		table.setItems(table_list);
	}
	
	
	/**
	 * Sets the reference to GameProfileController
	 * @param gp - GameProfileController
	 */
	public void setGPController(GameProfileController gp) {
		this.gp_controller = gp;
	}
	
	
	/**
	 * Sets the reference to used TabPane.
	 * @param tab - TabPane
	 */
	public void setTabs(TabPane tab) {
		this.tabs = tab;
	}
	
	
	/**
	 * Refreshes the list of user's projects and updates the view accordingly.
	 * @param fetcher
	 */
	private void refreshDeveloperGames(FetcherBeanRemote fetcher) {
		List<String> gamesAuthored = fetcher.getDeveloperGameNamesAddedByUser(Main.getUserName());
		projects.getItems().addAll(gamesAuthored);
	}
	
	
	/**
	 * Populates Game Profile tab with info about the selected project and switches tabs afterwards.
	 */
	public void showProjectProfile()  {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		try {
			gp_controller.populate(fetcher.getGameByName(projects.getSelectionModel().getSelectedItem()));
			tabs.getSelectionModel().select(1);
		} catch(Exception e) {
			LogManager.logException(LOGGER, e, true);
		}
	}
	
	
	/**
	 * Add a new game into the database, with parameters including but not limited to title, description,
	 * cover image and others.
	 * @throws IOException - if the specified file cannot be read
	 */
	public void submitGame() throws IOException {

		UpdaterBeanRemote updater = EJBControl.getUpdater();
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		Genre genre = fetcher.getGenreByName(genre_game.getValue());
		
		OfficialGame game = new OfficialGame();
		game.setName(title_game.getText());
		game.setReleaseYear((short)(year_game.getValue().intValue())); 
		game.setDescription(desc_game.getText());
		game.setGenre(genre);
		game.setStudio(studio.getText());
		game.setAdded(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		game.setImage(file == null ? genre.getImage() : Files.readAllBytes(file.toPath()));
		game.setAddedBy(fetcher.getUserAccountByName((Main.getUserName())));
				
		updater.addGame(game);
		
		refreshOfficialGames(fetcher);
	}
	
	
	/**
	 * Add a new project into the database, with parameters including but not limited to title, description,
	 * cover image and others.
	 * @throws IOException - if the specified file cannot be read
	 */
	public void submitProject() {
		UpdaterBeanRemote updater = EJBControl.getUpdater();
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		DeveloperGame game = new DeveloperGame();
		game.setAuthor(fetcher.getUserAccountByName(Main.getUserName()));
		game.setDescription(desc_project.getText());
		game.setGenre(fetcher.getGenreByName(genre_project.getValue()));
		
		try {
			game.setImage(file_project == null ? game.getGenre().getImage() : Files.readAllBytes(file_project.toPath()));
		} catch (IOException e) {

			e.printStackTrace();
			game.setImage(game.getGenre().getImage());
		}
		
		game.setName(title_project.getText());
		
		try {
			game.setReleaseYear((short)(int)year_project.getValue());
		} catch(NullPointerException e) {
			game.setReleaseYear((short)current_year);
		}
		
		updater.addDeveloperGame(game);
		
		refreshDeveloperGames(fetcher);
		
	}
	
	
	/**
	 * Populates Game Profile tab with info about the selected game and switches tabs afterwards.
	 * @throws NamingException
	 */
	public void showGameProfile() throws NamingException {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		try {
			gp_controller.populate(fetcher.getGameByName(table.getSelectionModel().getSelectedItem().getTitle()));
			tabs.getSelectionModel().select(1);
		} catch(Exception e) {
			LogManager.logException(LOGGER, e, true);
		}
	}  
	
	
	/**
	 * Displays dialog for choosing a new image file and uploads it afterwards.
	 */
	public void uploadProjectImage() {
		FileChooser fc = new FileChooser();
		file_project = fc.showOpenDialog(null);
	}
	
	
	/**
	 * Displays dialog for choosing a new image file and uploads it afterwards.
	 */
	public void uploadImage() throws IOException, NamingException {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
	}
}
