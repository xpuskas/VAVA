package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	@FXML
	PieChart chart;
	@FXML
	PieChart chart_studio;

	
	List<ImageView> screenshots;
	List<Label> labels;
	
	
	private Stage stage;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		screenshots = new ArrayList<ImageView>();
		labels = new ArrayList<Label>();
		
		screenshots.add(image1);
		screenshots.add(image2);
		screenshots.add(image3);
		screenshots.add(image4);
		
		labels.add(label1);
		labels.add(label2);
		labels.add(label3);
		labels.add(label4);
		
		Image solco = new Image("file:foto.jpg");
		
		imageBig.setImage(solco);
		
		handleLists();
		
		desc.setText("Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  Už ma z toho jebne asi...  ");

		labelBig.setText("Oblivion");
		
		   ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("Skyrim", 30),
	                new PieChart.Data("Oblivion", 20),
	                new PieChart.Data("Morrowind", 10),
		   			new PieChart.Data("Portal 2", 25),
	                new PieChart.Data("Ace Combat 7", 15));
		   			
		chart.setData(pieChartData);
		
		ObservableList<PieChart.Data> pieChartData2 =
                FXCollections.observableArrayList(
                new PieChart.Data("Bethesda", 30),
                new PieChart.Data("Valve", 20),
                new PieChart.Data("Namco", 10));
	   			
	chart_studio.setData(pieChartData2);
	

	}
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	public void handleLists() {
		Image foto = new Image("file:foto1.jpg");
		
		
		for (ImageView img: screenshots) {
			img.setImage(foto);
		}
		
		for (Label lbl: labels) {
			lbl.setText("Skyrim");
		}
	}
	


}
