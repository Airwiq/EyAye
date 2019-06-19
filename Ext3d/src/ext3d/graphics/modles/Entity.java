package ext3d.graphics.modles;


import java.util.ArrayList;
import java.util.List;

import ext3d.math.Maths;
import ext3d.math.vectors.Matrix4f;
import ext3d.math.vectors.Vector3f;
import ext3d.math.vectors.Vector4f;

import static ext3d.math.vectors.Matrix4f.mul;

public abstract class Entity {

	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;

	public Entity( Vector3f position, float rotX, float rotY, float rotZ,
	               float scale) {
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}

	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public abstract Model getModel();

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Renderable getRenderable(Matrix4f view, Matrix4f projection){
		List<Primitive> primitives = new ArrayList<>();
		for(Primitive p : getModel().getPrimitives()){
			List<Vector3f> polygons = new ArrayList<>();
			for(Vector3f polygon: p.getPoligons()) {
				Matrix4f m = Matrix4f.mul(
					mul(view,projection,null),
					Maths.createTransformationMatrix(Vector3f.add(getPosition(),polygon,null), getRotX(), getRotY(), getRotZ(), getScale()),
					null
				);
				Vector4f vf = Matrix4f.transform(m,new Vector4f(getPosition(),1),null);
				polygons.add(new Vector3f(vf.getX(),vf.getY(),vf.getZ()));
			}
			primitives.add(new Primitive(p.getColor(),polygons));
		}

		return new Renderable(primitives);
	}
	public Renderable getIsometricRenderable(){
		List<Primitive> primitives = new ArrayList<>();
		for(Primitive p : getModel().getPrimitives()){
			List<Vector3f> polygons = new ArrayList<>();
			for(Vector3f polygon: p.getPoligons()) {
				Matrix4f m = Maths.createTransformationMatrix(Vector3f.add(getPosition(),polygon,null), getRotX(), getRotY(), getRotZ(), getScale());
				Vector4f vf = Matrix4f.transform(m,new Vector4f(1,1,1,1),null);
				Vector3f vN = Maths.calulateIsometric(polygon);
				System.out.println(vN);
				//polygons.add(Maths.calulateIsometric(new Vector3f(vf.getX(),vf.getY(),vf.getZ())));
				polygons.add(vN);
			}
			primitives.add(new Primitive(p.getColor(),polygons));
		}

		return new Renderable(primitives);
	}
}