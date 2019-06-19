package ext3d.graphics;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ext3d.graphics.modles.Entity;
import ext3d.graphics.modles.Primitive;
import ext3d.graphics.modles.Renderable;
import ext3d.math.vectors.Matrix4f;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer extends Canvas{
	//gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position.x,position.y,position.z, 1.0);

	public Renderer(double width, double height) {
		super(width, height);
	}
	public void render(Matrix4f view, Matrix4f projection, Entity... entities){
		render(view,projection, Arrays.asList(entities));
	}
	public void render(Matrix4f view, Matrix4f projection, List<Entity> entities){
		List<Renderable> renderables = new ArrayList<>();
		for(Entity entity : entities){
			renderables.add(entity.getRenderable(view,projection));
		}
		GraphicsContext gc = getGraphicsContext2D();
		for(Renderable rn: renderables){
			for(Primitive pr : rn.getPrimitives()) {
				gc.setFill(Color.GREEN);
				gc.fillPolygon(pr.getXPoints(),pr.getYPoints(),pr.getPoligons().size());
			}
		}
	}
	public void renderIsometric(Entity... entities){
		renderIsometric(Arrays.asList(entities));
	}
	public void renderIsometric(List<Entity> entities){
		GraphicsContext gc = getGraphicsContext2D();
		List<Renderable> renderables = new ArrayList<>();
		for(Entity entity : entities){
			renderables.add(entity.getIsometricRenderable());
		}
		for(Renderable rn: renderables){
			for(Primitive pr : rn.getPrimitives()) {
				System.out.println(pr.getPoligons());
				gc.setFill(Color.GREEN);
				gc.fillPolygon(pr.getXPoints(),pr.getYPoints(),pr.getPoligons().size());
				gc.strokePolygon(pr.getXPoints(),pr.getYPoints(),pr.getPoligons().size());
			}
		}
	}
}
