package ext3d;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.PointLight;
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
	private static final int VOXEL_SIZE = 3*SCALE;

	public int R = 0;
	@Override
	public void start(Stage primaryStage) throws Exception{





		ParallelCamera camera = new ParallelCamera();
		Group lights = new Group();
		PointLight light = new PointLight();
		light.setTranslateY(32*VOXEL_SIZE);
		light.setTranslateZ(-16*VOXEL_SIZE);
		light.setColor(Color.GRAY);
		lights.getChildren().addAll(
			light,
			new AmbientLight(Color.GRAY)
		);
		Group model = new Group();
		Group mLLeg     = readImage(new File("xmodels/basic_expo-0.png"),32);
		Group mRLeg     = readImage(new File("xmodels/basic_expo-1.png"),32);
		Group mRArm = new Group();
			Group mRLArm    = readImage(new File("xmodels/basic_expo-2.png"),32); mRArm.getChildren().add(mRLArm);
			Group mRUArm    = readImage(new File("xmodels/basic_expo-3.png"),32); mRArm.getChildren().add(mRUArm);
		Group mLArm = new Group();
			Group mLLArm    = readImage(new File("xmodels/basic_expo-4.png"),32); mLArm.getChildren().add(mLLArm);
			Group mLUArm    = readImage(new File("xmodels/basic_expo-5.png"),32); mLArm.getChildren().add(mLUArm);
		Group mBody     = readImage(new File("xmodels/basic_expo-6.png"),32);
			mBody.getChildren().addAll(
					mRArm,
					mLArm
			);
		model.getChildren().addAll(
			mLLeg,
			mRLeg,
			mBody
		);
		{
			mRArm.setOnMouseClicked(event -> {
				for(Node n :mRLArm.getChildren()){
					if(n instanceof Box){
						if(((Box) n).getDrawMode() == DrawMode.LINE) {
							((Box) n).setDrawMode(DrawMode.FILL);
						}else{
							((Box) n).setDrawMode(DrawMode.LINE);
						}
					}
				}
			});
		}

		{
			mBody.setTranslateY(VOXEL_SIZE);

			mLArm.setTranslateX(1.5*VOXEL_SIZE);
				mLLArm.setTranslateY(-3*VOXEL_SIZE);
				mLLArm.setRotationAxis(Rotate.X_AXIS);
				mLLArm.setRotate(45);
				mLLArm.setTranslateZ(2.125*VOXEL_SIZE);
			mLArm.setRotationAxis(Rotate.X_AXIS);
			mLArm.setTranslateY(-3*VOXEL_SIZE);
			mLArm.setTranslateZ(2.125*VOXEL_SIZE);
			mLArm.setRotate(45);

			mRArm.setTranslateX(-1.5*VOXEL_SIZE);
				mRLArm.setTranslateY(-3*VOXEL_SIZE);
				mRLArm.setRotationAxis(Rotate.X_AXIS);
				mRLArm.setRotate(45);
				mRLArm.setTranslateZ(2.125*VOXEL_SIZE);
			mRArm.setRotationAxis(Rotate.X_AXIS);
			mRArm.setTranslateY(-3*VOXEL_SIZE);
			mRArm.setTranslateZ(2.125*VOXEL_SIZE);
			mRArm.setRotate(45);
		}


		model.getTransforms().clear();
		model.getTransforms().addAll(
				new Rotate(26.565, Rotate.X_AXIS),
				new Rotate(-90, Rotate.Y_AXIS)
		);
		model.setTranslateX(WIDTH/2);
		model.setTranslateY(HEIGHT/2);

		Scene scene = new Scene(new Group(model,lights), WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);
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
		model.getTransforms().clear();
		model.getTransforms().addAll(
				new Rotate(26.565, Rotate.X_AXIS),
				new Rotate(180, Rotate.Y_AXIS)
		);
		model.setTranslateX(WIDTH/2);
		model.setTranslateY(HEIGHT/2);
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
						Color col = Color.rgb(
								(px >> 16) & 0xFF,
								(px >> 8) & 0xFF,
								(px >> 0) & 0xFF
						);
						PhongMaterial material = new PhongMaterial(col);
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
		//ret.getChildren().add(bottom);
		Box cell = new Box(cellHeight*VOXEL_SIZE,cellHeight*VOXEL_SIZE,cellHeight*VOXEL_SIZE);
		cell.setDrawMode(DrawMode.LINE);
		//cell.setTranslateY(-cellHeight*VOXEL_SIZE/2);
		//cell.setTranslateX(-cellHeight*VOXEL_SIZE/2);
		//cell.setTranslateZ(-cellHeight*VOXEL_SIZE/2);
		//ret.getChildren().add(cell);
		return ret;
	}

}