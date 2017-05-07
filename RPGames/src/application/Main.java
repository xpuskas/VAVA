package application;
	
import java.io.IOException;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
	
//	launchMainApp();	
		
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	Parent root = (Parent)loader.load();
	LoginController lc = loader.getController();
	lc.setStage(primaryStage);
	
    primaryStage.setTitle("RPGames v0.21 ALPHA");
    primaryStage.setScene(new Scene(root, 640, 480));
	primaryStage.setResizable(false);  //pevne rozmery
    primaryStage.show();
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
    	
    	dialog.setTitle("RPGames v0.21 ALPHA");
    	dialog.setScene(scena);
    	dialog.show();
    	
	/*	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("RPGames v0.05 PRE-ALPHA");
        primaryStage.setScene(new Scene(root, 640, 480));
    	primaryStage.setResizable(false);  //pevne rozmery
        primaryStage.show();*/
    	
    	
    	/*
    	 * Stack overflow
    	 * http://stackoverflow.com/questions/14897194/stop-threads-before-close-my-javafx-program
    	 */
        dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
               Platform.exit();
               System.exit(0);
            }
         });
	}
	
	
	public void showAddReviewDialog() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("AddReview.fxml"));
    	Pane pane = (Pane) loader.load();
    	Stage dialog = new Stage();
    	Scene scena = new Scene(pane, 1024, 768);
        scena.getStylesheets().add("application/application.css");
    	
    	AddReviewController arc = loader.getController();
    	arc.setStage(dialog);
    	
    	dialog.setTitle("Add a review");
    	dialog.setScene(scena);
    	dialog.setResizable(true);
    	dialog.show();
    	
	}
	
	
	public void showAddArticleDialog() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("AddArticle.fxml"));
    	Pane pane = (Pane) loader.load();
    	Stage dialog = new Stage();
    	Scene scena = new Scene(pane, 1024, 768);
        scena.getStylesheets().add("application/application.css");
    	
    	AddArticleController aac = loader.getController();
    	aac.setStage(dialog);
    	
    	dialog.setTitle("Add an article");
    	dialog.setScene(scena);
    	dialog.setResizable(true);
    	dialog.show();
    	
	}
}
