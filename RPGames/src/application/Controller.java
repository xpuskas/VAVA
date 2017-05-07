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
		
		desc.setText("Skyrim ani nemohol vyjs v príhodnejšom èase. Je studený november a udalosti nového prírastku v sérii The Elder Scrolls plynú na drsnom severe. Možno vás chví¾ami bude mrazi, ale urèite prežijete aj horúce momenty. S drakmi totiž nie sú žiadne žarty, ani keï sa narodíte ako hrdina s draèou dušou. V krajine sa draci neukázali už ve¾mi dlho. Nikto neveril, že ešte nejakí žijú a niektorí dokonca zapochybovali, èi vôbec jestvovali. Prílet okrídleného monštra hneï v úvode však všetkým otvoril oèi a vzbudil oprávnený strach. Ale èo je pre domorodcov pohromou, znamená pre hlavného hrdinu spásu. Predsa len je lepšie èeli drakovi, ako sa necha sa. Èoskoro zistíte, že protivník, ktorému neèakane èelíte už krátko po štarte, nie je jedináèik. Celá krajina je náhle zamorená drakmi. Musíte vypátra, preèo tieto zabudnuté monštrá znovu povstali. Navyše sa zamotáte do mocenských konfliktov medzi znepriatelenými frakciami Skyrimu.");

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
