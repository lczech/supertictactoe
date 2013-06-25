package core;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Constants {

	public static BufferedImage imgX,imgO;
	
	public static void init() {
		try {
			imgX = ImageIO.read(new File("/home/m4x/git/supertictactoe/SuperTTT/X.png"));
			imgO = ImageIO.read(new File("/home/m4x/git/supertictactoe/SuperTTT/O.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
