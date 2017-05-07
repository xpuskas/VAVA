package application;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

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
	ListView<String> articles;

	
	private List<ImageView> user_photos;
	private List<Label> user_dates;
	private List<Label> user_names;
	private List<Text> user_texts;
	private List<ImageView> screenshots;
	
	private Timer timer;
	
	int counter = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		user_photos = new ArrayList<ImageView>();
		user_dates = new ArrayList<Label>();
		user_names = new ArrayList<Label>();
		user_texts = new ArrayList<Text>();
		screenshots = new ArrayList<ImageView>();
		
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
		
		screenshots.add(i1);
		screenshots.add(i2);
		screenshots.add(i3);
		screenshots.add(i4);
		screenshots.add(i5);
		
		Image cover_img = new Image("file:foto.jpg");
		Image usr_img = new Image("file:foto1.jpg");
		
		cover.setImage(cover_img);
		title.setText("The Elder Scrolls V: Skyrim");
		genre.setText("RPG");
		year.setText("2011");
		studio.setText("Bethesda");
		desc.setText("The best game in the world. The best game in the world.\nThe best game in the world. The best game in the world.\nThe best game in the world. The best game in the world.\nThe best game in the world. The best game in the world.\nThe best game in the world. The best game in the world.\n");
		
		rank_avg.setText("9.8");
		
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
		
		
		i1.setImage(cover_img);
		i2.setImage(cover_img);
		i3.setImage(cover_img);
		i4.setImage(usr_img);
		i5.setImage(cover_img);
		
		handleLists(true);
		
		timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				if(counter > 4)
					counter = 0;
				
				changeCover(screenshots.get(counter++));
			  }
			}, 2*100, 2*100);
		

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
	
	
	public void uploadScreenshots() throws IOException {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		
		if (file == null) {
			System.out.println("No file has been chosen!");
			throw new IOException();
		}
		
		else {
			System.out.println(file.getName());
		}
		
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
	
	public void nextPage() {
		
	}
	
	
	public void prevPage() {
		
		
	}
	
	
}
