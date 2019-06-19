package ext3d.math;


import ext3d.math.vectors.Matrix4f;
import ext3d.math.vectors.Vector3f;

public class Maths {
	public static final float FIELD_OF_VIEW = 70;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 1000;

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale,scale,scale), matrix, matrix);
		return matrix;
	}
	public static Matrix4f createProjectionMatrix(float displayWidth, float displayHeigth, float fieldOfView, float farPalne, float nearPlane){
		float aspectRatio = displayWidth / displayHeigth;
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(fieldOfView / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = farPalne - nearPlane;

		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((farPalne + nearPlane) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * nearPlane * farPalne) / frustum_length);
		projectionMatrix.m33 = 0;

		return projectionMatrix;
	}
	public static Vector3f calulateIsometric(Vector3f position){
		return new Vector3f(
			(position.getX()-position.getZ())*0.5f,
			((position.getX()+position.getZ())*0.125f)-(position.getY()*0.5f),
			0
		);
	}
}
