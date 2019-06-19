package ext3d.graphics.modles;


import java.util.List;

public class Renderable {
	protected List<Primitive> primitives;
	public Renderable(List<Primitive> primitives){
		this.primitives = primitives;
	}

	public List<Primitive> getPrimitives() {
		return primitives;
	}

	public void setPrimitives(List<Primitive> primitives) {
		this.primitives = primitives;
	}
}
