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
		Entity E = new Entity(new Vector3f(1024,0,512),0,0,0,4f) {
			@Override
			public Model getModel() {
				List<Primitive> p = new ArrayList<>();
				float s = 10f;
				p.add(new Primitive(0xFFFF00FF, new Vector3f(-s,-s,-s),new Vector3f(s,-s,-s),new Vector3f(s,-s,s),new Vector3f(-s,-s,s)));
				p.add(new Primitive(0xFF0000FF, new Vector3f(-s,-s,-s),new Vector3f(-s,s,-s),new Vector3f(-s,s,s),new Vector3f(-s,-s,s)));

				p.add(new Primitive(0x00FF00FF, new Vector3f(-s,-s,-s),new Vector3f(s,-s,-s),new Vector3f(s,s,-s),new Vector3f(-s,s,-s)));
				p.add(new Primitive(0xFF00FFFF, new Vector3f(-s,-s,s),new Vector3f(s,-s,s),new Vector3f(s,s,s),new Vector3f(-s,s,s)));
				p.add(new Primitive(0x00FFFFFF, new Vector3f(s,-s,-s),new Vector3f(s,s,-s),new Vector3f(s,s,s),new Vector3f(s,-s,s)));
				p.add(new Primitive(0x0000FFFF, new Vector3f(-s,s,-s),new Vector3f(s,s,-s),new Vector3f(s,s,s),new Vector3f(-s,s,s)));
				//p.add(new Primitive(1, new Vector3f(s,-s,-s),new Vector3f(s,s,-s),new Vector3f(s,s,s),new Vector3f(s,-s,s)));

				return new Model(p);
			}
		};
		cvs.render(E);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
