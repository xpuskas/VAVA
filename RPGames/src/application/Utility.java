package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Utility {

	public static Image byte2Image(byte[] bytes) {
		
		BufferedImage bim = null;
		InputStream in = new ByteArrayInputStream(bytes);
		try {
			bim = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        Image coverim = SwingFXUtils.toFXImage(bim, null);
        
        return coverim;
	}
	
}
