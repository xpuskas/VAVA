package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.PngImage;

import application.Utility;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import language.LanguageManager;
import logging.LogManager;
import model.Article;


/**
 * Controller class for displaying an article about a game. In addition to displaying the article itself, it also
 * allows the user to export the article in the PNG or PDF format.
 *
 */
public class ShowArticleController implements Initializable {

	@FXML
	ImageView cover;
	@FXML
	Label title;
	@FXML
	Text  text;
	@FXML
	VBox vbox;
	@FXML
	Button b1;
	@FXML
	Button b2;
	
	
	private Article displayedArticle;
	private static final Logger LOGGER = LogManager.createLogger(ShowArticleController.class.getName());
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		refreshLanguageTexts();
		vbox.setVisible(false);
	}
	
	
	/**
	 * Sets the reference to the currently displayed article.
	 * @param article - displayed article
	 */
	public void setDispplayedArticle(Article article) {
		this.displayedArticle = article;
	}
	
	
	
	/**
	 * Sets labels of the elements according to chosen language.
	 */
	public void refreshLanguageTexts() {
		b1.setText(LanguageManager.getProperty("SHOWREVIEW_BUTPNG"));
		b2.setText(LanguageManager.getProperty("SHOWREVIEW_BUTPDF"));

	}
	
	
	/**
	 * Populates the tab with the selected article.
	 */
	public void populate() {
		
		title.setText(displayedArticle.getTitle());
		cover.setImage(Utility.byte2Image(displayedArticle.getImage()));
		
		text.setText(displayedArticle.getText());
		
		vbox.setVisible(true);
		
	}
	
	
	/**
	 * Exports the article as a PNG image to a location specified by the user.
	 */
	public void exportPNG(){
		File file;
		FileChooser fc = new FileChooser();
		
		b1.setVisible(false);
		b2.setVisible(false);
		
		WritableImage img = vbox.snapshot(new SnapshotParameters(), null);
		
        ExtensionFilter filter = new FileChooser.ExtensionFilter("PNG image (*.png)", "*.png");
        fc.getExtensionFilters().add(filter);
		
		file = fc.showSaveDialog(null);
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
		} catch (IOException e) {
			LogManager.logException(LOGGER, e, true);

		}
		
		b1.setVisible(true);
		b2.setVisible(true);
		
	}
	
	
	/**
	 * Exports the article as a PDF file to a location specified by the user.
	 */
	public void exportPdf(){
		
		File file = new File("img.png");
		FileChooser fc = new FileChooser();
		
		b1.setVisible(false);
		b2.setVisible(false);
		
		Document pdf  = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		
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
		} 
		
		finally {
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
			
			file.delete();
		}
		b1.setVisible(true);
		b2.setVisible(true);
	}

}
