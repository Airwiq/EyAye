package ext3d.graphics.modles;

import java.util.Arrays;
import java.util.List;


public class OldyPrimitive {

	protected List<Vertex> poligons;
	protected Vertex a;
	protected Vertex b;
	protected Vertex c;
	protected Vertex d;

	protected int color;

	public OldyPrimitive(int color, List<Vertex> poligons){
		this.color = color;
		this.poligons = poligons;
	}
	public OldyPrimitive(int color, Vertex... polygons){
		this(color,Arrays.asList(polygons));
	}

	public List<Vertex> getPoligons() {
		return poligons;
	}

	public void setPoligons(List<Vertex> poligons) {
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
