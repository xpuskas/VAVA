package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image solco = new Image("file:foto1.jpg");
		Image foto = new Image("file:foto1.jpg");
		
		image1.setImage(foto);
		image2.setImage(foto);
		image3.setImage(foto);
		image4.setImage(foto);
		
		imageBig.setImage(solco);
		
		desc.setText("Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  ");

		label1.setText("Skyrim");
		label2.setText("Skyrim");
		label3.setText("Skyrim");
		label4.setText("Skyrim");
		labelBig.setText("Oblivion");
	}

}
