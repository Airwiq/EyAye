package ext3d;


import ext3d.entities.Person;
import ext3d.entities.models.person.BasicModel;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class PersonText extends Application{
	private static final int SCALE = 4;
	private static final int WIDTH = 128*SCALE;
	private static final int HEIGHT = 128*SCALE;
	private static final int VOXEL_SIZE = 4*SCALE;

	private int R = 0;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
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
		Person model = new Person(new BasicModel(),VOXEL_SIZE);

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
					break;
				case S:
					model.getTransforms().clear();
					model.getTransforms().addAll(
							new Rotate(26.565, Rotate.X_AXIS),
							new Rotate(45*--R, Rotate.Y_AXIS)
					);
					model.setTranslateX(WIDTH/2);
					model.setTranslateY(HEIGHT/2);
			}
		});
		primaryStage.setTitle("JFX3d");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
