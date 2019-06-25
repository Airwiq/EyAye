package ext3d;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class JFX3d_Perfect extends Application {
	private static final int SCALE = 5;
	private static final int WIDTH = 128*SCALE;
	private static final int HEIGHT = 128*SCALE;
	private static final int VOXEL_SIZE = 2*SCALE;

	public int R = 0;
	@Override
	public void start(Stage primaryStage) throws Exception{

		Group model = readImage(new File("basic_chr.png"),32);




		ParallelCamera camera = new ParallelCamera();
		Group X = new Group();




		model.getTransforms().clear();
		model.getTransforms().addAll(
				new Rotate(26.565, Rotate.X_AXIS),
				new Rotate(45, Rotate.Y_AXIS),
				new Rotate(45, Rotate.Y_AXIS),
				new Rotate(45, Rotate.Y_AXIS)
		);
		model.setTranslateX(WIDTH/2);
		model.setTranslateY(HEIGHT/2);
		X.getChildren().addAll(model);
		Scene scene = new Scene(X, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.TRANSPARENT);
		scene.setCamera(camera);
		//model.setRotationAxis(Rotate.Y_AXIS);
		//model.setRotate(225);
		//model.setRotationAxis(Rotate.X_AXIS);
		//model.setRotate(26.565);

		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
			switch (event.getCode()) {
				case W:
					model.getTransforms().clear();
					model.getTransforms().addAll(
							new Rotate(26.565, Rotate.X_AXIS),
							new Rotate(45*++R, Rotate.Y_AXIS)
					);
					model.setTranslateX(WIDTH/2);
					model.setTranslateY(HEIGHT/2);
			}
		});
		primaryStage.setTitle("JFX3d");
		primaryStage.setScene(scene);

		File dir = new File("xport");
		dir.mkdir();
		for(int i = 0; i < 360; i+=45){
			model.getTransforms().clear();
			model.getTransforms().addAll(
					new Rotate(26.565, Rotate.X_AXIS),
					new Rotate(i, Rotate.Y_AXIS)
			);
			model.setTranslateX(WIDTH/2);
			model.setTranslateY(HEIGHT/2);
			WritableImage img = scene.snapshot(null);
			BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
			ImageIO.write(bImage,"png",new File(dir,"out_"+i+".png"));
		}
		//model.setRotationAxis(Rotate.Y_AXIS);
		//model.setRotate(225);
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
						c.setTranslateX((x * VOXEL_SIZE)-(VOXEL_SIZE*img.getWidth()/2));
						c.setTranslateY((y * VOXEL_SIZE)-(VOXEL_SIZE*cellHeight/2));
						c.setTranslateZ((z * VOXEL_SIZE)-(VOXEL_SIZE*img.getWidth()/2));
						ret.getChildren().add(c);
					}
				}
			}
		}
		Box bottom = new Box(cellHeight*VOXEL_SIZE,1,cellHeight*VOXEL_SIZE);
		bottom.setTranslateY(cellHeight*VOXEL_SIZE/2);
		//bottom.setTranslateX(-cellHeight*VOXEL_SIZE/2);
		//bottom.setTranslateZ(-cellHeight*VOXEL_SIZE/2);
		bottom.setMaterial(new PhongMaterial(Color.BEIGE));
		ret.getChildren().add(bottom);
		Box cell = new Box(cellHeight*VOXEL_SIZE,cellHeight*VOXEL_SIZE,cellHeight*VOXEL_SIZE);
		cell.setDrawMode(DrawMode.LINE);
		//cell.setTranslateY(-cellHeight*VOXEL_SIZE/2);
		//cell.setTranslateX(-cellHeight*VOXEL_SIZE/2);
		//cell.setTranslateZ(-cellHeight*VOXEL_SIZE/2);
		//ret.getChildren().add(cell);
		return ret;
	}

}