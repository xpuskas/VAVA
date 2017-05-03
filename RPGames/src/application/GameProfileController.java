package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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
	ImageView u6_photo;
	@FXML
	ImageView u7_photo;
	@FXML
	ImageView u8_photo;
	@FXML
	ImageView u9_photo;
	@FXML
	ImageView u10_photo;
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
	Label usrname6;
	@FXML
	Label usrname7;
	@FXML
	Label usrname8;
	@FXML
	Label usrname9;
	@FXML
	Label usrname10;
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
	Label posted_on6;
	@FXML
	Label posted_on7;
	@FXML
	Label posted_on8;
	@FXML
	Label posted_on9;
	@FXML
	Label posted_on10;
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
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
		i1.setImage(cover_img);
		i2.setImage(cover_img);
		i3.setImage(cover_img);
		i4.setImage(cover_img);
		i5.setImage(cover_img);
		
		
	}

	
	
}
