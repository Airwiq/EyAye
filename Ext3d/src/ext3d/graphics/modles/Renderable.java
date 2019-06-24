package ext3d.graphics.modles;


import java.util.List;

public class Renderable {
	protected List<OldyPrimitive> primitives;
	public Renderable(List<OldyPrimitive> primitives){
		this.primitives = primitives;
	}

	public List<OldyPrimitive> getPrimitives() {
		return primitives;
	}

	public void setPrimitives(List<OldyPrimitive> primitives) {
		this.primitives = primitives;
	}
}
