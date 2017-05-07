package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	
	@FXML
	TextField searchBox;
	@FXML
	TextField studio;
	@FXML
	ComboBox<String> genre;
	@FXML
	ComboBox<Integer> year;
	@FXML
	CheckBox high_ranked;

	
	List<ImageView> screenshots;
	List<Label> labels;
	
	private int counter = 0;
	private Timer timer;
	
	
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
		
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 1995; i <= current_year; i++) {
			year.getItems().add(i);
		}
		
		Image solco = new Image("file:foto.jpg");
		
		imageBig.setImage(solco);
		
		handleLists();
		
		desc.setText("Skyrim ani nemohol vyjs� v pr�hodnej�om �ase. Je studen� november a udalosti nov�ho pr�rastku v s�rii The Elder Scrolls plyn� na drsnom severe. Mo�no v�s chv�ami bude mrazi�, ale ur�ite pre�ijete aj hor�ce momenty. S drakmi toti� nie s� �iadne �arty, ani ke� sa narod�te ako hrdina s dra�ou du�ou. V krajine sa draci neuk�zali u� ve�mi dlho. Nikto neveril, �e e�te nejak� �ij� a niektor� dokonca zapochybovali, �i v�bec jestvovali. Pr�let okr�dlen�ho mon�tra hne� v �vode v�ak v�etk�m otvoril o�i a vzbudil opr�vnen� strach. Ale �o je pre domorodcov pohromou, znamen� pre hlavn�ho hrdinu sp�su. Predsa len je lep�ie �eli� drakovi, ako sa necha� s�a�. �oskoro zist�te, �e protivn�k, ktor�mu ne�akane �el�te u� kr�tko po �tarte, nie je jedin��ik. Cel� krajina je n�hle zamoren� drakmi. Mus�te vyp�tra�, pre�o tieto zabudnut� mon�tr� znovu povstali. Navy�e sa zamot�te do mocensk�ch konfliktov medzi znepriatelen�mi frakciami Skyrimu.");

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
	
	
	timer = new Timer();
	timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			if(counter > 3)
				counter = 0;
			
			changeCover(screenshots.get(counter++));
		  }
		}, 200, 200);
	
	
	

	}
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	public void handleLists() {
		Image foto = new Image("file:foto1.jpg");
		Image solco = new Image("file:foto.jpg");
		
		
		for (ImageView img: screenshots) {
			img.setImage(foto);
		}
		
		image3.setImage(solco);
		
		for (Label lbl: labels) {
			lbl.setText("Skyrim");
		}
	}
	
	
	public void handleImage1Click() {
		timer.cancel();
		imageBig.setImage(image1.getImage());
	}
	
	public void handleImage2Click() {
		timer.cancel();
		imageBig.setImage(image2.getImage());
	}
	
	public void handleImage3Click() {
		timer.cancel();
		imageBig.setImage(image3.getImage());
	}
	
	public void handleImage4Click() {
		timer.cancel();
		imageBig.setImage(image4.getImage());
	}
	
	public void changeCover(ImageView image) {
		imageBig.setImage(image.getImage());
	}
	


}
