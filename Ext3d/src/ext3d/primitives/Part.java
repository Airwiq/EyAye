package ext3d.primitives;


import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;

public class Part extends Group {
	public Part() {
		super();
	}
	public Part(Node... children) {
		super(children);
	}
	public Part(Collection<Node> children) {
		super(children);
	}

	public void rotateY(double degree){
		double inX = getBoundsInParent().getMinX();
		double inY = getBoundsInParent().getMinY();
		double inZ = getBoundsInParent().getMinZ();
		setRotationAxis(Rotate.Z_AXIS);
		setRotate(degree);
		setTranslateY(inY-getBoundsInParent().getMinY());
		setTranslateX(inX-getBoundsInParent().getMinX());
	}
}
