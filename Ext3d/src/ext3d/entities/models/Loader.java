package ext3d.entities.models;


import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import ext3d.primitives.Part;
import ext3d.primitives.Voxel;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;

public class Loader {
	public static Part readImage(URL fl, int cellHeight, int voxelSize) throws Exception{
		return readImage(new File(fl.getFile()),cellHeight,voxelSize);
	}
	public static Part readImage(File fl, int cellHeight, int voxelSize) throws Exception{
		Part ret = new Part();
		BufferedImage img = ImageIO.read(fl);
		for(int y = 0; y < cellHeight; y++){
			for(int x = 0; x < img.getWidth(); x++){
				for(int z = 0; z < img.getWidth(); z++){
					int px = (img.getRGB(x, z + (y*cellHeight)));
					if(new java.awt.Color(px, true).getAlpha() > 0) {
						Color col = Color.rgb(
								(px >> 16) & 0xFF,
								(px >> 8) & 0xFF,
								(px >> 0) & 0xFF
						);
						PhongMaterial material = new PhongMaterial(col);
						Voxel c = new Voxel(voxelSize);
						c.setCullFace(CullFace.BACK);
						c.setDrawMode(DrawMode.FILL);
						c.setMaterial(material);
						c.setTranslateX((x * voxelSize)-(voxelSize*img.getWidth()/2));
						c.setTranslateY((y * voxelSize)-(voxelSize*cellHeight/2));
						c.setTranslateZ((z * voxelSize)-(voxelSize*img.getWidth()/2));
						ret.getChildren().add(c);
					}
				}
			}
		}
		Box bottom = new Box(cellHeight*voxelSize,1,cellHeight*voxelSize);
		bottom.setTranslateY(cellHeight*voxelSize/2);
		bottom.setMaterial(new PhongMaterial(Color.BEIGE));
		Box cell = new Box(cellHeight*voxelSize,cellHeight*voxelSize,cellHeight*voxelSize);
		cell.setDrawMode(DrawMode.LINE);
		return ret;
	}
}
