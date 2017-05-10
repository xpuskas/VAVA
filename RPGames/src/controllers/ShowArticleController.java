package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.NamingException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfImage;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.PngImage;

import application.Utility;
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
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import language.LanguageManager;
import model.Article;

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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		refreshLanguageTexts();
		vbox.setVisible(false);
	}
	
	public void setDispplayedArticle(Article article) {
		this.displayedArticle = article;
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
		
		title.setText(displayedArticle.getTitle());
		cover.setImage(Utility.byte2Image(displayedArticle.getImage()));
		
		text.setText(displayedArticle.getText());
		
		vbox.setVisible(true);
		
	}
	
	
	public void exportPNG() throws IOException {
		File file;
		FileChooser fc = new FileChooser();
		
		b1.setVisible(false);
		b2.setVisible(false);
		
		WritableImage img = vbox.snapshot(new SnapshotParameters(), null);
		
        ExtensionFilter filter = new FileChooser.ExtensionFilter("PNG image (*.png)", "*.png");
        fc.getExtensionFilters().add(filter);
		
		file = fc.showSaveDialog(null);
		ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
		
		b1.setVisible(true);
		b2.setVisible(true);
		
	}
	
	
	public void exportPdf() throws IOException, DocumentException {
		
		File file = new File("img.png");
		FileChooser fc = new FileChooser();
		
		b1.setVisible(false);
		b2.setVisible(false);
		
        ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF document (*.pdf)", "*.pdf");
        fc.getExtensionFilters().add(filter);
		
		WritableImage img = vbox.snapshot(new SnapshotParameters(), null);
		ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
		FileInputStream fis = new FileInputStream("img.png");
		
		com.lowagie.text.Image tmp = PngImage.getImage(fis);
		FileOutputStream fos = new FileOutputStream(fc.showSaveDialog(null));
		
		b1.setVisible(true);
		b2.setVisible(true);
		
		Document pdf = new Document(PageSize.B2, 20, 20, 20, 20);
		PdfWriter.getInstance(pdf, fos);
		pdf.open();
		pdf.add(tmp);
		
		pdf.close();
		fos.close();
		fis.close();
		
		b1.setVisible(true);
		b2.setVisible(true);
	}

}
