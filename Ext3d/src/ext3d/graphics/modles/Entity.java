package ext3d.graphics.modles;


import java.util.ArrayList;
import java.util.List;

import ext3d.math.Maths;
import ext3d.math.vectors.Matrix4f;
import ext3d.math.vectors.Vector3f;
import ext3d.math.vectors.Vector4f;

public abstract class Entity {

	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;

	public Entity( Vector3f position, float rotX, float rotY, float rotZ, float scale) {
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

	public Renderable getIsometricRenderable(){
		List<Primitive> primitives = new ArrayList<>();
		for(Primitive p : getModel().getPrimitives()){
			List<Vector3f> polygons = new ArrayList<>();
			for(Vector3f polygon: p.getPoligons()) {
				Matrix4f m = Maths.createTransformationMatrix(new Vector3f(0,0,0), getRotX(), getRotY(), getRotZ(), getScale());
				Vector4f vf = Matrix4f.transform(m,new Vector4f(polygon,1),null);
				polygons.add(Maths.calulateIsometric(Vector3f.add(getPosition(),new Vector3f(vf),null)));
			}
			primitives.add(new Primitive(p.getColor(),polygons));
		}

		return new Renderable(primitives);
	}
}