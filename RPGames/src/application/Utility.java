package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import logging.LogManager;

public class Utility {
	
	private static final Logger LOGGER = LogManager.createLogger( Utility.class.getName() );
	
	public static Image byte2Image(byte[] bytes) {
		
		BufferedImage bim = null;
		InputStream in = new ByteArrayInputStream(bytes);
		
		try {
			bim = ImageIO.read(in);
		} catch (IOException e) {
			LogManager.logException(LOGGER, e, true);
		}
		
        try {
			in.close();
		} catch (IOException e) {
			LogManager.logException(LOGGER, e, true);
		}
		
        Image coverim = SwingFXUtils.toFXImage(bim, null);
        
        return coverim;
	}
	
}
