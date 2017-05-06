package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
	
	launchMainApp();	
		
	/*FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	Parent root = (Parent)loader.load();
	LoginController lc = loader.getController();
	lc.setStage(primaryStage);
	
    primaryStage.setTitle("RPGames v0.05 PRE-ALPHA");
    primaryStage.setScene(new Scene(root, 640, 480));
	primaryStage.setResizable(false);  //pevne rozmery
    primaryStage.show();*/
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void launchMainApp() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("Home.fxml"));
    	Pane pane = (Pane) loader.load();
    	Stage dialog = new Stage();
    	Scene scena = new Scene(pane, 1280, 800);
        scena.getStylesheets().add("application/application.css");
    	
    	Controller c = loader.getController();
    	c.setStage(dialog);   
    	
    	dialog.setTitle("RPGames v0.05 PRE-ALPHA");
    	dialog.setScene(scena);
    	dialog.show();
    	
	/*	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("RPGames v0.05 PRE-ALPHA");
        primaryStage.setScene(new Scene(root, 640, 480));
    	primaryStage.setResizable(false);  //pevne rozmery
        primaryStage.show();*/
	}
	
	
	public void showAddReviewStage(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("AddReview.fxml"));
    	Pane pane = (Pane) loader.load();
    	Scene scene = new Scene(pane, 1280, 800);
        scene.getStylesheets().add("application/application.css");
    	
    	AddReviewController arc = loader.getController();
    	arc.setStage(stage);   
    	stage.setScene(scene);
	}
}
