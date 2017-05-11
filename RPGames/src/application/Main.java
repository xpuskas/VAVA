package application;
	
import java.io.IOException;
import java.util.logging.Logger;

import javax.naming.NamingException;

import controllers.AddArticleController;
import controllers.AddReviewController;
import controllers.Controller;
import controllers.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import language.LanguageManager;
import logging.LogManager;
import model.Game;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



public class Main extends Application {
	
	private static final Logger LOGGER = LogManager.createLogger( Main.class.getName() );
	private static String userName;
	
	@Override
	public void start(Stage primaryStage){
		
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
	Parent root = null;
	
	try {
		root = (Parent) loader.load();
	} catch (IOException e) {
		LogManager.logException(LOGGER, e, true);
	}
	
	LoginController lc = loader.getController();
	lc.setStage(primaryStage);
	
    primaryStage.setTitle("RPGames v0.91 BETA");
    primaryStage.setScene(new Scene(root, 640, 480));
	primaryStage.setResizable(false);  //pevne rozmery
    primaryStage.show();  
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/**
	 * Displaying of the main stage after a successful login.
	 * @return
	 * @throws IOException
	 */
	public void launchMainApp() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/views/Home.fxml"));
    	Pane pane = null;
    	
		try {
			pane = (Pane) loader.load();
		} catch (IOException e) {
			LogManager.logException(LOGGER, e, true);
			throw e;
		}
		
    	Stage dialog = new Stage();
    	Scene scena = new Scene(pane, 1280, 800);
        scena.getStylesheets().add("application/application.css");
    	
    	Controller c = loader.getController();
    	c.setStage(dialog); 
    	
    	dialog.setTitle("RPGames v0.91 BETA");
    	dialog.setScene(scena);
    	dialog.show();
    	
    	LanguageManager.initialize(); 	
    	
    	bindEventHandlerToDialog(dialog);
	}
	/**
	 * Binds event handler to the main stage. Upon closing the application,
	 * there might occur occasional memory leaks, that´s why it is important to use
	 * system call.
	 * 
	 * @param dialog - the main stage
	 * @Author Stack overflow
	 * @Link http://stackoverflow.com/questions/14897194/stop-threads-before-close-my-javafx-program
	 */
	private void bindEventHandlerToDialog(Stage dialog) {
        dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
               Platform.exit();
               System.exit(0);
            }
         });
	}
	
	/**
	 * Displays the dialog for adding a new {@link model.Review}.
	 * @param game - the game which is the topic of the review
	 * @return
	 */
	public void showAddReviewDialog(Game game){
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/views/AddReview.fxml"));
    	Pane pane = null;
    	
		try {
			pane = (Pane) loader.load();
		} catch (IOException e) {
			LogManager.logException(LOGGER, e, true);
		}
		
    	Stage dialog = new Stage();
    	Scene scena = new Scene(pane, 1024, 768);
        scena.getStylesheets().add("application/application.css");
    	
    	AddReviewController arc = loader.getController();
    	arc.setStage(dialog);
    	arc.setReviewedGame(game);
    	
    	dialog.setTitle("Add a review");
    	dialog.setScene(scena);
    	dialog.setResizable(true);
    	dialog.show();
 
	}
	
	/**
	 * Displays the dialog for adding a new article
	 * @param game - the game which is the topic of the article
	 * @return
	 */
	public void showAddArticleDialog(Game game) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/views/AddArticle.fxml"));
    	Pane pane = null;
    	
		try {
			pane = (Pane) loader.load();
		} catch (IOException e) {
			LogManager.logException(LOGGER, e, true);
		}
    	Stage dialog = new Stage();
    	Scene scena = new Scene(pane, 1024, 768);
        scena.getStylesheets().add("application/application.css");
    	
    	AddArticleController aac = loader.getController();
    	aac.setStage(dialog);
    	aac.setArticleedGame(game);
    	
    	dialog.setTitle("Add an article");
    	dialog.setScene(scena);
    	dialog.setResizable(true);
    	dialog.show();
    	
	}

	/**
	 * Returns the name of currently logged in user. Referenced by most controllers.
	 * @return name of the user who is currently logged in
	 */
	public static String getUserName() {
		return userName;
	}
	/**
	 * Stores the name of the user who is currently logged in.
	 * Must be called upon logging in, as this is to be referenced by
	 * any controller that needs to retrieve the user.
	 * @param userName - name of the user who is being logged in
	 * @return
	 */
	public static void setUserName(String userName) {
		Main.userName = userName;
	}
}
