package ext3d;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.stage.Stage;

public class SimpleSlice  extends Application {
	public static final int WINDOW_WIDTH = 512;
	public static final int WINDOW_HEIGHT = 512;

	@Override
	public void start(Stage primaryStage) throws Exception {
		readImage(new File("basic_chr.png"),32);
	}
	public static void main(String[] args) {
		launch(args);
	}

	public static List<?> readImage(File fl, int cellHeight) throws Exception{
		BufferedImage img = ImageIO.read(fl);
		for(int y = 0; y < cellHeight; y++){
			for(int x = 0; x < img.getWidth(); x++){
				for(int z = 0; z < img.getWidth(); z++){
					int px = (img.getRGB(x, z + (y*cellHeight)));
					if(new Color(px, true).getAlpha() > 0) {

					}
				}
			}
		}
		return null;
	}


}