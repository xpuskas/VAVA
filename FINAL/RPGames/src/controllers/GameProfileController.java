package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.naming.NamingException;

import application.EJBControl;
import application.Main;
import application.Utility;
import configuration.PropertyManager;
import fetcher.FetcherBeanRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import language.LanguageManager;
import logging.LogManager;
import model.Comment;
import model.DeveloperGame;
import model.Game;
import model.RatingOfGame;
import model.Screenshot;
import model.UserAccount;
import model.ViewGameByUser;
import updater.UpdaterBeanRemote;


/**
 * Controller class for the Game Profile tab. It's primary goal is to display extensive information about
 * the currently selected game, including but not limited to it's title, screenshots, description, rank
 * and others. The initialize method prepares lists of various GUI elements for easier management.
 *
 */
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
	@FXML
	ListView<String> reviews;
	@FXML
	Label studio_label;
	
	@FXML
	Label genre_l;
	@FXML
	Label year_l;
	@FXML
	Label desc_l;
	@FXML
	Label avg_l;
	@FXML
	Label my_l;
	@FXML
	Button rank_b;
	@FXML
	Button addrev_b;
	@FXML
	Button addart_b;
	@FXML
	Button upload_b;
	@FXML
	Label comments_l;
	@FXML
	Button post_b;
	@FXML
	Label rev_l;
	@FXML
	Label art_l;
	@FXML
	BorderPane border_p;
	
	private ShowReviewController tab_srvController;
	private ShowArticleController tab_sarController;
	private GameProfileController tab_gpController;

	
	private List<ImageView> user_photos;
	private List<Label> user_dates;
	private List<Label> user_names;
	private List<Text> user_texts;
	private List<ImageView> screenshot_holders;
	private List<Screenshot> screenshots;
	
	private Timer timer = null;
	private int currentCommentPage;
	private int currentScreenshotPage;
	private static final int maxCommentsPerPage = 5;

	private Game displayedGame;
	private TabPane tabs;

	private int counter = 0;
	
	private static final Logger LOGGER = LogManager.createLogger( GameProfileController.class.getName());
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		currentCommentPage = 0;
		currentScreenshotPage = 0;
		
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
		
		
		refreshLanguageTexts();
		border_p.setVisible(false);
		
	}
	
	
	/**
	 * Sets the reference to ShowReviewController.
	 * @param src - ShowReviewController
	 */
	public void setReviewController(ShowReviewController src) {
		this.tab_srvController = src;
	}
	
	
	/**
	 * Sets the reference to ShowArticleController
	 * @param art - ShowArticleCOntroller
	 */
	public void setArticleController(ShowArticleController art) {
		this.tab_sarController = art;
	}
	
	
	/**
	 * Sets the reference to used TabPane
	 * @param tab - TabPane
	 */
	public void setTabs(TabPane tab) {
		this.tabs = tab;
	}
	
	
	/**
	 * Sets labels of the elements according to chosen language.
	 */
	public void refreshLanguageTexts() {
		posted_on1.setText(LanguageManager.getProperty("GAMEPROFILE_POSTEDON1"));
		posted_on2.setText(LanguageManager.getProperty("GAMEPROFILE_POSTEDON2"));
		posted_on3.setText(LanguageManager.getProperty("GAMEPROFILE_POSTEDON3"));
		posted_on4.setText(LanguageManager.getProperty("GAMEPROFILE_POSTEDON4"));
		posted_on5.setText(LanguageManager.getProperty("GAMEPROFILE_POSTEDON5"));
		studio_label.setText(LanguageManager.getProperty("GAMEPROFILE_STUDIO"));
		genre_l.setText(LanguageManager.getProperty("GAMEPROFILE_GENRE"));
		year_l.setText(LanguageManager.getProperty("GAMEPROFILE_YEAR"));
		desc_l.setText(LanguageManager.getProperty("GAMEPROFILE_DESC"));
		avg_l.setText(LanguageManager.getProperty("GAMEPROFILE_AVGRANK"));
		my_l.setText(LanguageManager.getProperty("GAMEPROFILE_MYRANK"));
		rank_b.setText(LanguageManager.getProperty("GAMEPROFILE_RANKBUT"));
		addrev_b.setText(LanguageManager.getProperty("GAMEPROFILE_ADDREVBUT"));
		addart_b.setText(LanguageManager.getProperty("GAMEPROFILE_ADDARTBUT"));
		upload_b.setText(LanguageManager.getProperty("GAMEPROFILE_UPLOADBUT"));
		comments_l.setText(LanguageManager.getProperty("GAMEPROFILE_COMMENTS"));
		post_b.setText(LanguageManager.getProperty("GAMEPROFILE_POSTBUT"));
		rev_l.setText(LanguageManager.getProperty("GAMEPROFILE_REVIEWS"));
		art_l.setText(LanguageManager.getProperty("GAMEPROFILE_ARTICLES"));
		
	}
	
	
	/**
	 * Sets the reference to GameProfileController
	 * @param gpc - GameProfileController
	 */
	public void setGPC(GameProfileController gpc) {
		this.tab_gpController = gpc;
	}
	
	
	/**
	 * Populates the Game Profile tab with info about currently selected game, includin it's title,
	 * description, screenshots, rank and other values.
	 * @param game - displayed game
	 * @throws NamingException - when a problem with displaying the comments occurs.
	 */
	public void populate(Game game) throws NamingException {
		
		this.displayedGame = game;
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		UpdaterBeanRemote updater = EJBControl.getUpdater();
		
		
		title.setText(displayedGame.getName());
		genre.setText(displayedGame.getGenre().getName());
		year.setText(((Short)displayedGame.getReleaseYear()).toString());
		
		if(game instanceof DeveloperGame) {
			studio_label.setText("Author:");
			studio.setText(fetcher.getDeveloperGameByName(displayedGame.getName()).getAuthor().getName());
		}
		
		else {
			studio_label.setText("Studio:");
			studio.setText(fetcher.getOfficialGameByName(displayedGame.getName()).getStudio());
		}
		
		visualiseRatings(((Double)fetcher.getAverageRankingOfGame(displayedGame)), (fetcher.getRankingOfGameByUserAndGame(displayedGame.getName(), Main.getUserName())));
		
		desc.setText(displayedGame.getDescription());
		
		visualiseComments(loadComments());
		visualiseScreenshots(loadScreenshots());
		
		counter = 0;
		if(timer == null) {
			timer = new Timer();
			
			timer.scheduleAtFixedRate(new TimerTask() {
				  @Override
				  public void run() {
					if(counter > 4)
						counter = 0;
					try {
						changeCover(screenshot_holders.get(counter++));
					} catch(ArrayIndexOutOfBoundsException e) {
						LogManager.logException(LOGGER, e, false);
					}
				  }
				}, Integer.parseInt(PropertyManager.getProps().getProperty("timer_millis")), Integer.parseInt(PropertyManager.getProps().getProperty("timer_millis")));   
		}
		
			ViewGameByUser viewRecord = new ViewGameByUser();
			viewRecord.setGame(displayedGame);
			viewRecord.setViewed(new Date());
			viewRecord.setViewer(fetcher.getUserAccountByName(Main.getUserName()));
			updater.addVGBU(viewRecord);
			
			Controller.refreshLastViewedGames(fetcher);
		
	    refreshReviewsArticles();
	    
	    border_p.setVisible(true);
	  
	}
	
	
	/**
	 * Refreshes the lists of reviews and articles for the displayed game.
	 */
	public void refreshReviewsArticles() {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		articles.getItems().clear();
	    articles.getItems().addAll(fetcher.reviewNamesByGame(displayedGame));
	    
	    reviews.getItems().clear();
	    reviews.getItems().addAll(fetcher.articleNamesByGame(displayedGame));
	}
	
	
	/**
	 * Populates the Game Review tab with a selected game review and switches tabs afterwards.
	 * @throws NamingException - when a selected review cannot be found in the database
	 */
	public void showGameReview() throws NamingException {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		tab_srvController.setDisplayedReview(fetcher.getReviewByTitle(articles.getSelectionModel().getSelectedItem()));
		tab_srvController.populate();
		tabs.getSelectionModel().select(3);
	}
	
	
	/**
	 * Populates the Game Article tab with a selected game article and switches tabs afterwards.
	 * @throws NamingException - when a selected article cannot be found in the database
	 */
	public void showGameArticle() throws NamingException {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		tab_sarController.setDispplayedArticle(fetcher.getArticleByTitle(reviews.getSelectionModel().getSelectedItem()));
		tab_sarController.populate();
		tabs.getSelectionModel().select(4);
	}
	
	
	/**
	 * Displays the average and user's personal rating of the game.
	 * @param avg - average rating
	 * @param my - user's rating
	 */
	public void visualiseRatings(Double avg, RatingOfGame my) {
		if(avg < 0) {
			rank_avg.setText("N/A");
		}
		
		else {
			rank_avg.setText(avg.toString());
		}
		
		if(my == null) {
			rank_my.setText("N/A");
		}
		
		else {
			rank_my.setText(((Double)my.getValue()).toString());
			rank.setValue(my.getValue());
		}
		
		
	}
	
	
	/**
	 * Show the dialog for adding a game review.
	 */
	public void addReview(){
	   	Main main = new Main();
    	main.showAddReviewDialog(displayedGame, tab_gpController);
	}
	
	
	/**
	 * Show the dialog for adding a game article.
	 */
	public void addArticle(){
	   	Main main = new Main();
    	main.showAddArticleDialog(displayedGame, tab_gpController);
	}
	
	
	/**
	 * Displays dialog for choosing a new image file and uploads it as a game screenshot afterwards.
	 * @throws NamingException - when a problem with retrieving the screenshots occurs.
	 */
	public void uploadScreenshots() throws NamingException  {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		
		if(file != null) {
			try {
				
			UpdaterBeanRemote updater = EJBControl.getUpdater();
			
			Screenshot screen = new Screenshot();
			screen.setGame(displayedGame);
			screen.setScreenshot(Files.readAllBytes(file.toPath()));

			updater.addScreenshot(screen);
			
			visualiseScreenshots(loadScreenshots());
			
			} catch (IOException e) {
				LogManager.logException(LOGGER, e, true);
			}
		}
	}
	
	
	/**
	 * Ranks the game accoriding to user's selection. In case the user has already ranked the game,
	 * it updates the rank given by hom instead.
	 * @throws NamingException
	 */
	public void rankGame() throws NamingException {
		UpdaterBeanRemote updater = EJBControl.getUpdater();
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		UserAccount user = fetcher.getUserAccountByName(Main.getUserName());
		RatingOfGame orig = fetcher.getRankingOfGameByUserAndGame(displayedGame.getName(), user.getName());
		
		if(orig == null) { 
			RatingOfGame rog = new RatingOfGame();
			rog.setGame(displayedGame);
			rog.setUser(fetcher.getUserAccountByName(Main.getUserName()));
			rog.setValue(rank.getValue());
			
			
			updater.addRatingOfGame(rog);
			System.out.println("Added rating.");
		}
		else { 
			orig.setValue(rank.getValue());
			updater.updateRatingOfGame(orig);
			System.out.println("Updated rating.");
		}

		visualiseRatings(((Double)fetcher.getAverageRankingOfGame(displayedGame)), (fetcher.getRankingOfGameByUserAndGame(displayedGame.getName(), Main.getUserName())));

	}
	
	
	/**
	 * Stops the timer amd displays the cover image of the selected game in the primary placeholder
	 * (in the center of the scene). 
	 */
	public void handleImage1Click() {
		timer.cancel();
		cover.setImage(i1.getImage());
	}
	
	/**
	 * Stops the timer amd displays the cover image of the selected game in the primary placeholder
	 * (in the center of the scene). 
	 */
	public void handleImage2Click() {
		timer.cancel();
		cover.setImage(i2.getImage());
	}
	
	/**
	 * Stops the timer amd displays the cover image of the selected game in the primary placeholder
	 * (in the center of the scene). 
	 */
	public void handleImage3Click() {
		timer.cancel();
		cover.setImage(i3.getImage());
	}
	
	/**
	 * Stops the timer amd displays the cover image of the selected game in the primary placeholder
	 * (in the center of the scene). 
	 */
	public void handleImage4Click() {
		timer.cancel();
		cover.setImage(i4.getImage());
	}
	
	/**
	 * Stops the timer amd displays the cover image of the selected game in the primary placeholder
	 * (in the center of the scene). 
	 */
	public void handleImage5Click() {
		timer.cancel();
		cover.setImage(i5.getImage());
	}
	
	
	/**
	 * Changes the cover of the game in the primary placeholder.
	 * @param image - image to be displayed
	 */
	public void changeCover(ImageView image) {
		cover.setImage(image.getImage());
	}
	
	/**
	 * Switches the comments page to the next one and refreshes the view.
	 * @throws NamingException - when comments cannot be retrieved
	 */
	public void nextPage() throws NamingException {
		++currentCommentPage;
		List<Comment> comments = loadComments();
		
		if(comments.size() == 0) {
			--currentCommentPage;
			return;
		}
		
		visualiseComments(comments);
	}
	
	
	/**
	 * Switches the comments page to the previous one and refreshes the view.
	 * @throws NamingException - when comments cannot be retrieved
	 */
	public void prevPage() throws NamingException {
		
		if(currentCommentPage == 0)
			return;
		
		--currentCommentPage;
		
		List<Comment> comments = loadComments();
		
		visualiseComments(comments);
		
	}
	
	
	/**
	 * Switches the screenshots page to the next one and refreshes the view.
	 * @throws NamingException - when screenshots cannot be retrieved
	 */
	public void nextScreenshots() throws NamingException {
		++currentScreenshotPage;
		List<Screenshot> screens = loadScreenshots();
		
		if(screens.size() == 0) {
			--currentScreenshotPage;
			return;
		}
		
		visualiseScreenshots(screens);
	}
	
	
	/**
	 * Switches the screensots page to the next one and refreshes the view.
	 * @throws NamingException - when screenshots cannot be retrieved
	 */
	public void prevScreenshots() throws NamingException {
		if(currentScreenshotPage == 0)
			return;
		
		--currentScreenshotPage;
		
		List<Screenshot> screens = loadScreenshots();
		
		visualiseScreenshots(screens);
	}
	
	
	/**
	 * Loads the specified comment page from the database.
	 * @return
	 */
	private List<Comment> loadComments() {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		return fetcher.getPagedCommentsForGame(displayedGame, maxCommentsPerPage, currentCommentPage);
	}
	
	
	/**
	 * Loads the specified comment page from the database.
	 * @return
	 */
	private List<Screenshot> loadScreenshots() throws NamingException {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		
		return fetcher.getPagedScreenshotsForGame(displayedGame, maxCommentsPerPage, currentScreenshotPage);
	}
	
	
	/**
	 * Visualizes the comments, taking into account the currently selected page.
	 * @param comments - list of comments
	 */
	private void visualiseComments(List<Comment> comments) {
		//Show and fill the comments that are available
		
		
		for(int i = 0 ; i < comments.size(); i++) {
			user_names.get(i).setVisible(true);
			user_names.get(i).setText(comments.get(i).getAuthor().getName());
			
			user_dates.get(i).setVisible(true);
			user_dates.get(i).setText(comments.get(i).getPosted().toString());
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date now = comments.get(i).getPosted();
		    user_dates.get(i).setText(sdf.format(now));
			
			user_texts.get(i).setVisible(true);
			user_texts.get(i).setText(comments.get(i).getText());
			
			user_photos.get(i).setVisible(true);
			user_photos.get(i).setImage(Utility.byte2Image(comments.get(i).getAuthor().getProfilePic()));
		}
		
		//Hide the remaining comments
		for(int i = comments.size(); i < maxCommentsPerPage; i++) {
			user_names.get(i).setVisible(false);
			user_dates.get(i).setVisible(false);
			user_texts.get(i).setVisible(false);
			user_photos.get(i).setVisible(false);
		}
	}
	
	
	/**
	 * Visualizes the screenshots, taking into account the currently selected page.
	 * @param screens - list of screenshots
	 */
	private void visualiseScreenshots(List<Screenshot> screens) {

		for(int i = 0 ; i < screens.size(); i++) {
			screenshot_holders.get(i).setImage(Utility.byte2Image(screens.get(i).getScreenshot()));
		}
		
		Image def = (Utility.byte2Image(displayedGame.getImage()));  
		for(int i = screens.size(); i < maxCommentsPerPage; i++) {
			screenshot_holders.get(i).setImage(def);  
		}
		
		if(screens.size() > 0) {
			cover.setImage(Utility.byte2Image(screens.get(screens.size() - 1).getScreenshot()));
		}
		
		else {
			cover.setImage(Utility.byte2Image(displayedGame.getImage()));
		}
	}
	
	
	/**
	 * Posts a comment about the currenty displayed game.
	 */
	public void postComment() {
		FetcherBeanRemote fetcher = EJBControl.getFetcher();
		UpdaterBeanRemote updater = EJBControl.getUpdater();
		
		Comment com = new Comment();
		com.setAuthor(fetcher.getUserAccountByName(Main.getUserName()));
		com.setGame(displayedGame);
		com.setPosted(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		com.setText(comment.getText());
		
		updater.addComment(com);
		
		System.out.println("Comment was posted successfully");
		comment.clear();
		
		visualiseComments(loadComments());
		
	}
	
	
	/**
	 * Gets and return the currently displayed game.
	 * @return - displayed game
	 */
	public Game getDisplayedGame() {
		return displayedGame;
	}


	/**
	 * Sets the currently displayed game.
	 * @param displayedGame
	 */
	public void setDisplayedGame(Game displayedGame) {
		this.displayedGame = displayedGame;
	}
	
}
