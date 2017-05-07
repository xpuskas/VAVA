package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.swing.ImageIcon;

import application.EJBContext;
import application.Main;
import application.Utility;
import fetcher.FetcherBeanRemote;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import model.Comment;
import model.Game;
import model.RatingOfGame;
import model.Screenshot;
import updater.UpdaterBeanRemote;

public class GameProfileController implements Initializable {

	@FXML
	ImageView cover;
	@FXML
	Label title;
	@FXML
	Label genre;
	@FXML
	Label studio;
	@FXML
	Label year;
	@FXML
	Text desc;
	@FXML
	Label rank_avg;
	@FXML
	Label rank_my;
	@FXML
	Slider rank;
	@FXML
	ImageView u1_photo;
	@FXML
	ImageView u2_photo;
	@FXML
	ImageView u3_photo;
	@FXML
	ImageView u4_photo;
	@FXML
	ImageView u5_photo;
	@FXML
	Label usrname1;
	@FXML
	Label usrname2;
	@FXML
	Label usrname3;
	@FXML
	Label usrname4;
	@FXML
	Label usrname5;
	@FXML
	Label posted_on1;
	@FXML
	Label posted_on2;
	@FXML
	Label posted_on3;
	@FXML
	Label posted_on4;
	@FXML
	Label posted_on5;
	@FXML
	Text t1;
	@FXML
	Text t2;
	@FXML
	Text t3;
	@FXML
	Text t4;
	@FXML
	Text t5;
	@FXML
	ImageView i1;
	@FXML
	ImageView i2;
	@FXML
	ImageView i3;
	@FXML
	ImageView i4;
	@FXML
	ImageView i5;
	@FXML
	TextArea comment;
	@FXML
	ListView<String> articles;

	
	private List<ImageView> user_photos;
	private List<Label> user_dates;
	private List<Label> user_names;
	private List<Text> user_texts;
	private List<ImageView> screenshot_holders;
	private List<Screenshot> screenshots;
	
	private Timer timer = null;
	//TODO Lukas
	private int currentCommentPage;
	private static final int maxCommentsPerPage = 5;

	private Game displayedGame;

	int counter = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		currentCommentPage = 0;
		
		user_photos = new ArrayList<ImageView>();
		user_dates = new ArrayList<Label>();
		user_names = new ArrayList<Label>();
		user_texts = new ArrayList<Text>();
		screenshot_holders = new ArrayList<ImageView>();
		
		user_photos.add(u1_photo);
		user_photos.add(u2_photo);
		user_photos.add(u3_photo);
		user_photos.add(u4_photo);
		user_photos.add(u5_photo);
		
		user_dates.add(posted_on1);
		user_dates.add(posted_on2);
		user_dates.add(posted_on3);
		user_dates.add(posted_on4);
		user_dates.add(posted_on5);
		
		user_names.add(usrname1);
		user_names.add(usrname2);
		user_names.add(usrname3);
		user_names.add(usrname4);
		user_names.add(usrname5);
		
		user_texts.add(t1);
		user_texts.add(t2);
		user_texts.add(t3);
		user_texts.add(t4);
		user_texts.add(t5);
		
		screenshot_holders.add(i1);
		screenshot_holders.add(i2);
		screenshot_holders.add(i3);
		screenshot_holders.add(i4);
		screenshot_holders.add(i5);
		
		Image usr_img = new Image("file:foto1.jpg");
		
		u1_photo.setImage(usr_img);
		u2_photo.setImage(usr_img);
		u3_photo.setImage(usr_img);
		u4_photo.setImage(usr_img);
		u5_photo.setImage(usr_img);
		
		posted_on1.setText("2017-12-24");
		posted_on2.setText("2017-12-24");
		posted_on3.setText("2017-12-24");
		posted_on4.setText("2017-12-24");
		posted_on5.setText("2017-12-24");
		
		usrname1.setText("G4MER");
		usrname2.setText("G4MER");
		usrname3.setText("G4MER");
		usrname4.setText("G4MER");
		usrname5.setText("G4MER");
		
		t1.setText("This is awesome!");
		t2.setText("This is awesome!");
		t3.setText("This is awesome!");
		t4.setText("This is awesome!");
		t5.setText("This is awesome!");
		
		handleLists(false);
	}
	
	
	public void populate() throws NamingException {
		
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		
		handleLists(true);
		
		title.setText(displayedGame.getName());
		genre.setText(displayedGame.getGenre().getName());
		year.setText(((Short)displayedGame.getReleaseYear()).toString());
		
		studio.setText("BETHESDA"); //TODOOOOOOOOOOOOOOOOOOOO
		rank_avg.setText("10"); //TODO
		rank_my.setText("9.5");  //TODO
		
	//	Game game = fetcher.getGameByName(displayedGame.getName());   //RATINGY!!!!!!!!!!!!!!!!!
	//	System.out.println(game.getRatings().size());
		
		desc.setText(displayedGame.getDescription());
		
		Image def = new Image("file:foto.jpg");

		screenshots = fetcher.getPagedScreenshotsForGame(displayedGame, 5, 0);  //WARNING
		if(screenshots.size() > 0) {
			cover.setImage(Utility.byte2Image(screenshots.get(screenshots.size() - 1).getScreenshot()));
		}
		
		for(int i = 0; i < screenshots.size(); i++) {
			screenshot_holders.get(i).setImage(Utility.byte2Image(screenshots.get(i).getScreenshot()));
		}
		
		for(int i = screenshots.size(); i < 5; i++) {
			screenshot_holders.get(i).setImage(def);
		}
		
		visualiseComments(loadComments());		
		
		counter = 0;
		if(timer == null) {
			timer = new Timer();
			
			timer.scheduleAtFixedRate(new TimerTask() {
				  @Override
				  public void run() {
					if(counter > 4)
						counter = 0;
					
					changeCover(screenshot_holders.get(counter++));
				  }
				}, 5000, 5000);   
		}
	}
	
	public void handleLists(boolean flag) {
		for (ImageView img: user_photos) {
			img.setVisible(flag);
		}
		
		for (Label posted_on: user_dates) {
			posted_on.setVisible(flag);
		}
		
		for (Label usr: user_names) {
			usr.setVisible(flag);
		}
		
		for (Text txt: user_texts) {
			txt.setVisible(flag);
		}
	}

	
	public void addReview() throws IOException {
	   	Main main = new Main();
    	main.showAddReviewDialog();
	}
	
	
	public void addArticle() throws IOException {
	   	Main main = new Main();
    	main.showAddArticleDialog();
	}
	
	
	public void uploadScreenshots() throws IOException, NamingException {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		
		if (file == null) {
			System.out.println("No file has been chosen!");
			throw new IOException();
		}
		
		else {
			System.out.println(file.getName());
			
			Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
			FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
			UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
			
			Screenshot screen = new Screenshot();
			screen.setGame(displayedGame);
			screen.setScreenshot(Files.readAllBytes(file.toPath()));
			
			updater.addScreenshot(screen);
			
			
		}
	}
	
	
	public void rankGame() throws NamingException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		
		RatingOfGame rog = new RatingOfGame();
		rog.setGame(displayedGame);
		rog.setUser(fetcher.getUserAccountByName(Main.getUserName()));
		rog.setValue(rank.getValue());
		
	}
	
	public void handleImage1Click() {
		timer.cancel();
		cover.setImage(i1.getImage());
	}
	
	public void handleImage2Click() {
		timer.cancel();
		cover.setImage(i2.getImage());
	}
	
	public void handleImage3Click() {
		timer.cancel();
		cover.setImage(i3.getImage());
	}
	
	public void handleImage4Click() {
		timer.cancel();
		cover.setImage(i4.getImage());
	}
	
	public void handleImage5Click() {
		timer.cancel();
		cover.setImage(i5.getImage());
	}
	
	
	public void changeCover(ImageView image) {
		cover.setImage(image.getImage());
	}
	
	//TODO Lukas was here
	public void nextPage() throws NamingException {
		++currentCommentPage;
		List<Comment> comments = loadComments();
		
		if(comments.size() == 0) {
			--currentCommentPage;
			return;
		}
		
		visualiseComments(comments);
	}
	
	
	public void prevPage() throws NamingException {
		
		if(currentCommentPage == 0)
			return;
		
		--currentCommentPage;
		
		List<Comment> comments = loadComments();
		
		visualiseComments(comments);
		
	}
	
	
	//Can be used anywhere
	private List<Comment> loadComments() throws NamingException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		
		return fetcher.getPagedCommentsForGame(displayedGame, maxCommentsPerPage, currentCommentPage);
	}
	
	
	//Can be used anywhere
	private void visualiseComments(List<Comment> comments) {
		//Show and fill the comments that are available
		for(int i = 0 ; i < comments.size(); i++) {
			user_names.get(i).setVisible(true);
			user_names.get(i).setText(comments.get(i).getAuthor().getName());
			
			//TODO sformatovat datum -- DONE
			user_dates.get(i).setVisible(true);
			user_dates.get(i).setText(comments.get(i).getPosted().toString());
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date now = comments.get(i).getPosted();
		    user_dates.get(i).setText(sdf.format(now));
			
			user_texts.get(i).setVisible(true);
			user_texts.get(i).setText(comments.get(i).getText());
			
			//TODO User zatial nema profilovku ako atribut
			user_photos.get(i).setVisible(true);
		}
		
		//Hide the remaining comments
		for(int i = comments.size(); i < maxCommentsPerPage; i++) {
			user_names.get(i).setVisible(false);
			user_dates.get(i).setVisible(false);
			user_texts.get(i).setVisible(false);
			user_photos.get(i).setVisible(false);
		}
	}
	
	public void postComment() throws NamingException {
		Context context = EJBContext.createRemoteEjbContext("localhost", "8080");
		FetcherBeanRemote fetcher = (FetcherBeanRemote)context.lookup("ejb:/EJB3//FetcherBean!fetcher.FetcherBeanRemote");
		UpdaterBeanRemote updater = (UpdaterBeanRemote)context.lookup("ejb:/EJB3//UpdaterBean!updater.UpdaterBeanRemote");
		
		Comment com = new Comment();
		com.setAuthor(fetcher.getUserAccountByName(Main.getUserName()));
		com.setGame(displayedGame);
		com.setPosted(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		com.setText(comment.getText());
		
		updater.addComment(com);
		
		System.out.println("Comment was posted successfully");
		comment.clear();
		
	}
	
	public Game getDisplayedGame() {
		return displayedGame;
	}


	public void setDisplayedGame(Game displayedGame) {
		this.displayedGame = displayedGame;
	}
	
}
