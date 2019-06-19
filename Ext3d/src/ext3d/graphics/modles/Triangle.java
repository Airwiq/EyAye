package ext3d.graphics.modles;


public class Triangle {
	protected Vertex a;
	protected Vertex b;
	protected Vertex c;

	public Triangle(Vertex a, Vertex b, Vertex c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Vertex getA() {
		return a;
	}

	public void setA(Vertex a) {
		this.a = a;
	}

	public Vertex getB() {
		return b;
	}

	public void setB(Vertex b) {
		this.b = b;
	}

	public Vertex getC() {
		return c;
	}

	public void setC(Vertex c) {
		this.c = c;
	}
}
