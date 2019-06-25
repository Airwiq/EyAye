package ext3d;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class JFX3d extends Application {
	private static final int SCALE = 4;
	private static final int WIDTH = 128*SCALE;
	private static final int HEIGHT = 128*SCALE;
	private static final int VOXEL_SIZE = 6*SCALE;
	@Override
	public void start(Stage primaryStage) throws Exception{

		Group model = readImage(new File("basic_chr.png"),32);

		Box bottom = new Box(32*VOXEL_SIZE,1,32*VOXEL_SIZE);
		bottom.setTranslateY(32*VOXEL_SIZE);
		bottom.setTranslateX(32*VOXEL_SIZE/2);
		bottom.setTranslateZ(32*VOXEL_SIZE/2);
		bottom.setMaterial(new PhongMaterial(Color.BEIGE));
		model.getChildren().add(bottom);

		Box cell = new Box(32*VOXEL_SIZE,32*VOXEL_SIZE,32*VOXEL_SIZE);
		cell.setDrawMode(DrawMode.LINE);
		cell.setTranslateY(32*VOXEL_SIZE/2);
		cell.setTranslateX(32*VOXEL_SIZE/2);
		cell.setTranslateZ(32*VOXEL_SIZE/2);
		model.getChildren().add(cell);
		PerspectiveCamera camera = new PerspectiveCamera();


		Scene scene = new Scene(model, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);

		scene.setFill(Color.TRANSPARENT);
		scene.setCamera(camera);
		camera.translateYProperty().set(camera.getTranslateY()-(VOXEL_SIZE*32));
		camera.translateZProperty().set(camera.getTranslateZ()-(VOXEL_SIZE*32*2));
		camera.setRotationAxis(Rotate.X_AXIS);
		camera.setRotate(-30);
		//camera.setVerticalFieldOfView(true);
		camera.setFieldOfView(30);
		model.translateXProperty().set(WIDTH / 2 - (32*VOXEL_SIZE/2));
		model.translateYProperty().set(HEIGHT / 2 - (32*VOXEL_SIZE/2));
		model.translateZProperty().set(-(32*VOXEL_SIZE/2));


		primaryStage.setTitle("JFX3d");
		primaryStage.setScene(scene);

		File dir = new File("xport");
		dir.mkdir();
		for(int i = 0; i < 360; i+=45){
			model.setRotationAxis(Rotate.Y_AXIS);
			model.setRotate(i);
			WritableImage img = scene.snapshot(null);
			BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
			ImageIO.write(bImage,"png",new File(dir,"out_"+i+".png"));
		}
		model.setRotationAxis(Rotate.Y_AXIS);
		model.setRotate(225);
		primaryStage.show();



	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Group readImage(File fl, int cellHeight) throws Exception{
		Group ret = new Group();
		BufferedImage img = ImageIO.read(fl);
		for(int y = 0; y < cellHeight; y++){
			for(int x = 0; x < img.getWidth(); x++){
				for(int z = 0; z < img.getWidth(); z++){
					int px = (img.getRGB(x, z + (y*cellHeight)));
					if(new java.awt.Color(px, true).getAlpha() > 0) {
						PhongMaterial material = new PhongMaterial(Color.rgb(
							(px >> 16) & 0xFF,
							(px >> 8) & 0xFF,
							(px >> 0) & 0xFF
						));

						Box c = new Box(VOXEL_SIZE, VOXEL_SIZE, VOXEL_SIZE);
						c.setCullFace(CullFace.BACK);
						c.setDrawMode(DrawMode.FILL);
						c.setMaterial(material);
						c.setTranslateX(x * VOXEL_SIZE);
						c.setTranslateY(y * VOXEL_SIZE);
						c.setTranslateZ(z * VOXEL_SIZE);
						ret.getChildren().add(c);
					}
				}
			}
		}
		return ret;
	}

}