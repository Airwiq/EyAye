package ext3d.graphics;


import ext3d.math.vectors.Matrix4f;
import ext3d.math.vectors.Vector3f;

public class Camera {
	protected Vector3f m_Position;
	protected float     m_Pitch;
	protected float     m_Yaw;
	protected float     m_Roll;

	public Camera(Vector3f p_Postion, float p_Pitch, float p_Yaw, float p_Roll){
		m_Position  = p_Postion;
		m_Pitch     = p_Pitch;
		m_Yaw       = p_Yaw;
		m_Roll      = p_Roll;
	}
	public <C extends Camera> C setPitch(float p_Pitch){
		m_Pitch = p_Pitch;
		return (C) this;
	}
	public <C extends Camera> C setYaw(float p_Yaw){
		m_Yaw = p_Yaw;
		return (C) this;
	}
	public <C extends Camera> C setRoll(float p_Roll){
		m_Roll = p_Roll;
		return (C) this;
	}
	public <C extends Camera> C setPostion(Vector3f p_Position){
		m_Position = p_Position;
		return (C) this;
	}
	public Vector3f getPosition() {
		return m_Position;
	}
	public Vector3f getNegativePosition(){
		return new Vector3f(-m_Position.x,-m_Position.y,-m_Position.z);
	}
	public float getPitch() {
		return m_Pitch;
	}
	public float getYaw() {
		return m_Yaw;
	}
	public float getRoll() {
		return m_Roll;
	}

	public Matrix4f getViewMatrix() {
		Matrix4f o_View = new Matrix4f();
		o_View.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(getPitch()), new Vector3f(1, 0, 0), o_View, o_View);
		Matrix4f.rotate((float) Math.toRadians(getYaw()), new Vector3f(0, 1, 0), o_View, o_View);
		Matrix4f.rotate((float) Math.toRadians(getRoll()), new Vector3f(0, 0, 1), o_View, o_View);
		Matrix4f.translate(getNegativePosition(), o_View, o_View);
		return o_View;
	}
	public Matrix4f getDirectionMatrix(){
		Matrix4f o_Direction = new Matrix4f();
		o_Direction.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(getPitch()), new Vector3f(1, 0, 0), o_Direction, o_Direction);
		Matrix4f.rotate((float) Math.toRadians(getYaw()), new Vector3f(0, 1, 0), o_Direction, o_Direction);
		Matrix4f.rotate((float) Math.toRadians(getRoll()), new Vector3f(0, 0, 1), o_Direction, o_Direction);
		Matrix4f.translate(m_Position, o_Direction, o_Direction);
		return o_Direction;
	}
	public Vector3f rotV3fByV3f(Vector3f npos, Vector3f nrot, float rotation) {
		Matrix4f matrix = new Matrix4f();

		Vector3f pos = new Vector3f(npos);

		matrix.m03 = pos.x;
		matrix.m13 = pos.y;
		matrix.m23 = pos.z;

		Vector3f rot = new Vector3f(nrot);

		Matrix4f.rotate((float) Math.toRadians(rotation), rot, matrix, matrix);

		return new Vector3f(matrix.m03, matrix.m13, matrix.m23);
	}
}
