package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import application.EJBContext;
import application.Main;
import fetcher.FetcherBeanRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import model.DeveloperGame;
import model.Genre;
import model.OfficialGame;
import model.OfficialGameWrapper;
import updater.UpdaterBeanRemote;

public class MyGamesController implements Initializable {

	@FXML
	TableView<OfficialGameWrapper> table;
	@FXML
	TableColumn<OfficialGameWrapper, String> t_title;
	@FXML
	TableColumn<OfficialGameWrapper, String> t_genre;  //hmmmmm TODO
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
	
	private File file = null;
	private File file_project = null;
	
	private int current_year;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
        t_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        t_genre.setCellValueFactory(new PropertyValueFactory<>("genre")); //to komplikujes :D mam to urobit? to by bolo ponizujuce Ok :D
        t_studio.setCellValueFactory(new PropertyValueFactory<>("studio"));
		
		current_year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = current_year; i >= 1995; i--) { //Nechcem oke to skôr zostupne? to je blbe, ze? je, ale obidem to
			year_game.getItems().add(i);
			year_project.getItems().add(i);
		}
		
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
		
		List<String> genres = fetcher.getAllGenres();
		genre_game.getItems().add("Any");
		genre_game.getItems().addAll(genres);
		genre_project.getItems().add("Any");
		genre_project.getItems().addAll(genres);
		

		refreshOfficialGames(fetcher);
		refreshDeveloperGames(fetcher);
	}
	
	
	//Here alright
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
	
	
	private void refreshDeveloperGames(FetcherBeanRemote fetcher) {
		List<String> gamesAuthored = fetcher.getDeveloperGameNamesAddedByUser(Main.getUserName());
		projects.getItems().addAll(gamesAuthored);
	}
	
	
	public void submitGame() throws NamingException, IOException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		
		Genre genre = fetcher.getGenreByName(genre_game.getValue());
		
		OfficialGame game = new OfficialGame();
		game.setName(title_game.getText());
		game.setReleaseYear((short)(year_game.getValue().intValue())); 
		game.setDescription(desc_game.getText());
		game.setGenre(genre);
		game.setStudio(studio.getText());
		game.setAdded(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		game.setImage(file == null ? genre.getImage() : Files.readAllBytes(file.toPath()));
				
		updater.addGame(game);
		
		System.out.println("Added new game");
		
		refreshOfficialGames(fetcher);
	}
	
	public void submitProject() throws NamingException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		
		DeveloperGame game = new DeveloperGame();
		game.setAuthor(fetcher.getUserAccountByName(Main.getUserName()));
		game.setDescription(desc_project.getText());
		game.setGenre(fetcher.getGenreByName(genre_project.getValue()));
		
		try {
			game.setImage(file_project == null ? game.getGenre().getImage() : Files.readAllBytes(file_project.toPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		
		System.out.println("Added developer game");
		
		refreshDeveloperGames(fetcher);
		
	}
	public void uploadProjectImage() throws IOException, NamingException {
		FileChooser fc = new FileChooser();
		file_project = fc.showOpenDialog(null);
		
		/*if (file_project == null) { //TODO
			System.out.println("No file has been chosen!");
			throw new IOException();
		}
		
		else {
			System.out.println(file_project.getName());
		}*/
	}
	
	public void uploadImage() throws IOException, NamingException {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);
		
		/*if (file == null) {
			System.out.println("No file has been chosen!");  //TODO
			throw new IOException();
		}
		
		else {
			System.out.println(file.getName());
		}*/
	}
}
