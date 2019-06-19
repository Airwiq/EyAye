package ext3d;


import java.util.ArrayList;
import java.util.List;

import ext3d.graphics.Renderer;
import ext3d.graphics.modles.Entity;
import ext3d.graphics.modles.Model;
import ext3d.graphics.modles.Primitive;
import ext3d.math.vectors.Vector3f;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EntryPoint extends Application {
	public static final int WINDOW_WIDTH = 512;
	public static final int WINDOW_HEIGHT = 512;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Renderer cvs = new Renderer(WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(new Scene(new BorderPane(cvs)));
		primaryStage.show();
		Entity E = new Entity(new Vector3f(0,0,0),0,0,0,1) {
			@Override
			public Model getModel() {
				List<Primitive> p = new ArrayList<>();
				p.add(new Primitive(1, new Vector3f(512+0,0,0),new Vector3f(512+100,0,0),new Vector3f(512+100,0,100),new Vector3f(512+0,0,100)));
				return new Model(p);
			}
		};
		cvs.renderIsometric(E);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
