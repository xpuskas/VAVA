package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.NamingException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.PngImage;

import application.EJBContext;
import application.Utility;
import fetcher.FetcherBeanRemote;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import language.LanguageManager;
import logging.LogManager;
import model.Game;
import model.Review;
import updater.UpdaterBeanRemote;

public class ShowReviewController implements Initializable {

	@FXML
	Label title;
	@FXML
	Label rank;
	@FXML
	Label pro1;
	@FXML
	Label pro2;
	@FXML
	Label pro3;
	@FXML
	Label pro4;
	@FXML
	Label con1;
	@FXML
	Label con2;
	@FXML
	Label con3;
	@FXML
	Label con4;
	@FXML
	Text text;
	@FXML
	ImageView cover;
	@FXML
	VBox vbox;
	@FXML
	Button b1;
	@FXML
	Button b2;
	
	
	private Review displayedReview;
	List<Label> pro_holders;
	List<Label> con_holders;
	private static final Logger LOGGER = LogManager.createLogger(ShowReviewController.class.getName());
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		pro_holders = new ArrayList<Label>();
		con_holders = new ArrayList<Label>();
		
		pro_holders.add(pro1);
		pro_holders.add(pro2);
		pro_holders.add(pro3);
		pro_holders.add(pro4);
		
		con_holders.add(con1);
		con_holders.add(con2);
		con_holders.add(con3);
		con_holders.add(con4);
				
		refreshLanguageTexts();
	}
	
	
	public void setDisplayedReview(Review displayedReview) {
		this.displayedReview = displayedReview;
	}
	
	
	//Ked sa zmeni jazyk, tak treba nad kazdym controllerom zavolat takuto metodu - teda nad kazdym, kde je co menit
	public void setLanguage(String language) {
		LanguageManager.setLanguage(language);
		refreshLanguageTexts();
	}
	
	public void refreshLanguageTexts() {
		b1.setText(LanguageManager.getProperty("SHOWREVIEW_BUTPNG"));
		b2.setText(LanguageManager.getProperty("SHOWREVIEW_BUTPDF"));

	}
	
	
	
	public void populate() throws NamingException {
		
		title.setText(displayedReview.getTitle());
		rank.setText(((Double)displayedReview.getRank()).toString());
		cover.setImage(Utility.byte2Image(displayedReview.getImage()));
		
		text.setText(displayedReview.getText());
		displayPros();
		displayCons();
		
		vbox.setVisible(true);
		
	}
	
	
	public void exportPNG(){
		File file;
		FileChooser fc = new FileChooser();
		
		b1.setVisible(false);
		b2.setVisible(false);
		

		try {
			WritableImage img = vbox.snapshot(new SnapshotParameters(), null);
			
	        ExtensionFilter filter = new FileChooser.ExtensionFilter("PNG image (*.png)", "*.png");
	        fc.getExtensionFilters().add(filter);
			
			file = fc.showSaveDialog(null);
			ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
		} catch (IOException e) {
			LogManager.logException(LOGGER, e, true);

		}
		
		b1.setVisible(true);
		b2.setVisible(true);
		
	}
	
	
	public void exportPdf(){
		
		File file = new File("img.png");
		FileChooser fc = new FileChooser();
		
		b1.setVisible(false);
		b2.setVisible(false);
		
		Document pdf = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
	        ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF document (*.pdf)", "*.pdf");
	        fc.getExtensionFilters().add(filter);
			
			WritableImage img = vbox.snapshot(new SnapshotParameters(), null);
			ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
			fis = new FileInputStream("img.png");
			
			com.lowagie.text.Image tmp = PngImage.getImage(fis);
			fos = new FileOutputStream(fc.showSaveDialog(null));
			
			b1.setVisible(true);
			b2.setVisible(true);
			
			pdf = new Document(PageSize.B2, 20, 20, 20, 20);
			PdfWriter.getInstance(pdf, fos);
			pdf.open();
			pdf.add(tmp);
		} catch(Exception e) {
			LogManager.logException(LOGGER, e, true);
		} finally {
			try {
				pdf.close();
			} catch (Exception e) {
				LogManager.logException(LOGGER, e, true);
			}
			try {
				fos.close();
			} catch (IOException e) {
				LogManager.logException(LOGGER, e, true);
			}
			try {
				fis.close();
			} catch (IOException e) {
				LogManager.logException(LOGGER, e, true);
			}
		}
		
		b1.setVisible(true);
		b2.setVisible(true);
	}
	
	
	
	public void displayPros() {
		String pros = displayedReview.getPros();
		String[] pro_s = pros.split("\\|");
		
		System.out.println(pro_s.length);
		
		
		for (int i = 0; i < pro_s.length; i++) {
			pro_holders.get(i).setVisible(true);
			pro_holders.get(i).setText("+ " + pro_s[i]);
		}
		
		for (int i = pro_s.length; i < pro_holders.size(); i++) {
			pro_holders.get(i).setVisible(false);
		}  
	}
	
	
	public void displayCons() {
		String cons = displayedReview.getCons();
		String[] con_s = cons.split("\\|");
		
		for (int i = 0; i < con_s.length; i++) {
			con_holders.get(i).setVisible(true);
			con_holders.get(i).setText("- " + con_s[i]);
		}
		
		for (int i = con_s.length; i < con_holders.size(); i++) {
			con_holders.get(i).setVisible(false);
		}
	}
}
