package ext3d;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ext3d.graphics.Renderer;
import ext3d.graphics.modles.Entity;
import ext3d.graphics.modles.Model;
import ext3d.graphics.modles.OldyPrimitive;
import ext3d.graphics.modles.Vertex;
import ext3d.math.vectors.Vector3f;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SimpleSlice  extends Application {
	public static final int WINDOW_WIDTH = 512;
	public static final int WINDOW_HEIGHT = 512;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Renderer cvs = new Renderer(WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(new Scene(new BorderPane(cvs)));
		primaryStage.show();


		cvs.render(readImage(new File("basic_chr.png"),32));

	}
	public static void main(String[] args) {
		launch(args);
	}

	public static List<Entity> readImage(File fl, int cellHeight) throws Exception{
		float voxelSize = 20f;
		float xOffset = 512;
		float yOffset = -512;
		List<Entity> ret = new ArrayList<>();
		VoxEl voxEl = new VoxEl(new Vertex(xOffset,yOffset,0),1);
		ret.add(voxEl);
		BufferedImage img = ImageIO.read(fl);
		for(int y = 0; y < cellHeight; y++){
			for(int x = 0; x < img.getWidth(); x++){
				for(int z = 0; z < img.getWidth(); z++){

					int px = (img.getRGB(x, z + (y*cellHeight)));
					if(new Color(px, true).getAlpha() > 0) {
						Vertex pos = new Vertex(x * voxelSize, (y) * voxelSize, z * voxelSize);

						voxEl.getModel().getPrimitives().addAll(buildVoxel(px,voxelSize,pos));
					}
				}
			}
		}
		return ret;
	}

	public static List<OldyPrimitive> buildVoxel(int color, float size, Vertex pos){
		List<OldyPrimitive> p = new ArrayList<>();
		float s = 0.5f*size;
		p.add(new OldyPrimitive(color, add(pos,new Vertex(-s, -s, -s)),  add(pos,new Vertex(s, -s, -s)),  add(pos,new Vertex(s, -s, s)),   add(pos,new Vertex(-s, -s, s))));
		p.add(new OldyPrimitive(color, add(pos,new Vertex(-s, -s, -s)),  add(pos,new Vertex(-s, s, -s)),  add(pos,new Vertex(-s, s, s)),   add(pos,new Vertex(-s, -s, s))));
		p.add(new OldyPrimitive(color, add(pos,new Vertex(-s, -s, -s)),  add(pos,new Vertex(s, -s, -s)),  add(pos,new Vertex(s, s, -s)),   add(pos,new Vertex(-s, s, -s))));
		p.add(new OldyPrimitive(color, add(pos,new Vertex(-s, -s, s)),   add(pos,new Vertex(s, -s, s)),   add(pos,new Vertex(s, s, s)),    add(pos,new Vertex(-s, s, s))));
		p.add(new OldyPrimitive(color, add(pos,new Vertex(s, -s, -s)),   add(pos,new Vertex(s, s, -s)),   add(pos,new Vertex(s, s, s)),    add(pos,new Vertex(s, -s, s))));
		p.add(new OldyPrimitive(color, add(pos,new Vertex(-s, s, -s)),   add(pos,new Vertex(s, s, -s)),   add(pos,new Vertex(s, s, s)),    add(pos,new Vertex(-s, s, s))));
		return p;
	}
	public static Vertex add(Vertex a, Vertex b){
		return new Vertex(Vector3f.add(a,b,null));
	}
	public static class VoxEl extends Entity{
		protected int color = 0xFF808080;
		protected Model m;
		public VoxEl(Vector3f position, float scale) {
			super(position, 0,0,0, scale);
			this.color = color;
		}
		public VoxEl(Vector3f position, float rotX, float rotY, float rotZ, float scale) {
			super(position, rotX, rotY, rotZ, scale);
		}

		@Override
		public Model getModel() {
			List<OldyPrimitive> p = new ArrayList<>();
			//float s = 0.5f;
			//p.add(new OldyPrimitive(color, new Vertex(-s, -s, -s), new Vertex(s, -s, -s), new Vertex(s, -s, s), new Vertex(-s, -s, s)));
			//p.add(new OldyPrimitive(color, new Vertex(-s, -s, -s), new Vertex(-s, s, -s), new Vertex(-s, s, s), new Vertex(-s, -s, s)));
			//p.add(new OldyPrimitive(color, new Vertex(-s, -s, -s), new Vertex(s, -s, -s), new Vertex(s, s, -s), new Vertex(-s, s, -s)));
			//p.add(new OldyPrimitive(color, new Vertex(-s, -s, s), new Vertex(s, -s, s), new Vertex(s, s, s), new Vertex(-s, s, s)));
			//p.add(new OldyPrimitive(color, new Vertex(s, -s, -s), new Vertex(s, s, -s), new Vertex(s, s, s), new Vertex(s, -s, s)));
			//p.add(new OldyPrimitive(color, new Vertex(-s, s, -s), new Vertex(s, s, -s), new Vertex(s, s, s), new Vertex(-s, s, s)));
			//p.add(new Primitive(1, new Vector3f(s,-s,-s),new Vector3f(s,s,-s),new Vector3f(s,s,s),new Vector3f(s,-s,s)));
			if(this.m == null){
				m = new Model(p);
			}
			return m;
		}
	}

}