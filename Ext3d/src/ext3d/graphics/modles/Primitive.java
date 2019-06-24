package ext3d.graphics.modles;


public class Primitive {
	public enum Normal{
		X,Y,Z
	}

	protected int color;
	protected Normal normal;
	protected Vertex center;
	protected float size;

	protected Vertex a;
	protected Vertex b;
	protected Vertex c;
	protected Vertex d;

	public Primitive(int color, Normal normal, Vertex center, float size) {
		this.color = color;
		this.normal = normal;
		this.center = center;
		this.size = size;
	}

}
