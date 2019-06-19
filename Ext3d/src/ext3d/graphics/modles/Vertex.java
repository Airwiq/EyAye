package ext3d.graphics.modles;


import ext3d.math.vectors.ReadableVector3f;
import ext3d.math.vectors.Vector3f;

public class Vertex extends Vector3f{
	public Vertex() {
		super();
	}
	public Vertex(ReadableVector3f vec){
		super(vec);
	}
	public Vertex(float x, float y, float z) {
		super(x, y, z);
	}
}
