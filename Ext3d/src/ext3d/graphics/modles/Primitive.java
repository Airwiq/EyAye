package ext3d.graphics.modles;

import java.util.Arrays;
import java.util.List;

import ext3d.math.vectors.Vector3f;


public class Primitive {
	protected List<Vector3f> poligons;
	protected int color;

	public Primitive(int color, List<Vector3f> poligons){
		this.color = color;
		this.poligons = poligons;
	}
	public Primitive(int color, Vector3f... polygons){
		this(color,Arrays.asList(polygons));
	}

	public List<Vector3f> getPoligons() {
		return poligons;
	}

	public void setPoligons(List<Vector3f> poligons) {
		this.poligons = poligons;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	public double[] getXPoints(){
		double[] ret = new double[poligons.size()];
		for(int i = 0; i < ret.length; i++){
			ret[i] = poligons.get(i).getX();
		}
		return ret;
	}
	public double[] getYPoints(){
		double[] ret = new double[poligons.size()];
		for(int i = 0; i < ret.length; i++){
			ret[i] = poligons.get(i).getY();
		}
		return ret;
	}
}
