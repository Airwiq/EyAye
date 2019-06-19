package ext3d.graphics;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ext3d.graphics.modles.Entity;
import ext3d.graphics.modles.OldyPrimitive;
import ext3d.graphics.modles.Renderable;
import ext3d.graphics.modles.Vertex;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer extends Canvas{
	//gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position.x,position.y,position.z, 1.0);

	public Renderer(double width, double height) {
		super(width, height);
	}
	public void render(Entity... entities){
		render(Arrays.asList(entities));
	}
	public void render(List<Entity> entities){
		GraphicsContext gc = getGraphicsContext2D();
		List<Renderable> renderables = new ArrayList<>();
		for(Entity entity : entities){
			renderables.add(entity.getIsometricRenderable());
		}
		for(Renderable rn: renderables){
			for(OldyPrimitive pr : rn.getPrimitives()) {
				System.out.println(pr.getPoligons());
				Color c = Color.rgb(
						pr.getColor()>>16 & 0xFF,
						pr.getColor()>>8 & 0xFF,
						pr.getColor() & 0xFF,
						1f
				);
				//gc.setStroke(c);
				gc.setFill(c);
				gc.fillPolygon(pr.getXPoints(),pr.getYPoints(),pr.getPoligons().size());
				for(Vertex px : pr.getPoligons()){

				}
				//gc.strokePolygon(pr.getXPoints(),pr.getYPoints(),pr.getPoligons().size());
			}
		}
	}
}
