package ext3d.graphics.modles;


import java.util.List;

public class Model {
	protected List<Primitive> primitives;
	public Model(List<Primitive> primitives){
		this.primitives = primitives;
	}

	public List<Primitive> getPrimitives() {
		return primitives;
	}

	public void setPrimitives(List<Primitive> primitives) {
		this.primitives = primitives;
	}
	
}
