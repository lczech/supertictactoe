package core;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Constants {

	public static BufferedImage imgX;
	
	public static void init() {
		try {
			imgX = ImageIO.read(new File("/home/m4x/git/supertictactoe/SuperTTT/X.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
