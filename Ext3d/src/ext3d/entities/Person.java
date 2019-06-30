package ext3d.entities;


import java.net.URL;

import ext3d.entities.models.Loader;
import ext3d.primitives.Part;
import javafx.scene.Group;

public class Person extends Group {
	protected Part body;
	protected Part head;
	protected Part torso;


	protected Part rightArm;
	protected Part rightShoulder;
	protected Part rightUpperArm;
	protected Part rightLowerArm;
	protected Part rightHand;

	protected Part leftArm;
	protected Part leftShoulder;
	protected Part leftUpperArm;
	protected Part leftLowerArm;
	protected Part leftHand;

	protected Part rightLeg;
	protected Part rightUpperLeg;
	protected Part rightLowerLeg;
	protected Part leftLeg;
	protected Part leftUpperLeg;
	protected Part leftLowerLeg;

	public Person(SlicedModel model, int voxelSize) throws Exception{
		int cellHeight = model.getCellHeight();
		head = Loader.readImage(model.getHead(),cellHeight,voxelSize);
		torso = Loader.readImage(model.getBody(),cellHeight,voxelSize);

		leftShoulder = Loader.readImage(model.getRightSchoulder(),cellHeight,voxelSize);
		leftUpperArm = Loader.readImage(model.getRightUpperArm(),cellHeight,voxelSize);
		leftLowerArm = Loader.readImage(model.getRightLowerArm(),cellHeight,voxelSize);

		rightShoulder = Loader.readImage(model.getLeftSchoulder(),cellHeight,voxelSize);
		rightUpperArm = Loader.readImage(model.getLeftUpperArm(),cellHeight,voxelSize);
		rightLowerArm = Loader.readImage(model.getLeftLowerArm(),cellHeight,voxelSize);

		rightUpperLeg = Loader.readImage(model.getRightUpperLeg(),cellHeight,voxelSize);
		rightLowerLeg = Loader.readImage(model.getRightLowerLeg(),cellHeight,voxelSize);
		leftUpperLeg = Loader.readImage(model.getLeftUpperLeg(),cellHeight,voxelSize);
		leftLowerLeg = Loader.readImage(model.getLeftLowerLeg(),cellHeight,voxelSize);


		body = new Part(head,torso);
		rightArm = new Part(rightShoulder);
			rightShoulder.getChildren().add(rightUpperArm);
				rightUpperArm.getChildren().add(rightLowerArm);
		leftArm = new Part(leftShoulder);
			leftShoulder.getChildren().add(leftUpperArm);
				leftUpperArm.getChildren().add(leftLowerArm);
		torso.getChildren().addAll(rightArm,leftArm);
		rightLeg = new Part(rightUpperLeg);
		rightUpperLeg.getChildren().add(rightLowerLeg);
		leftLeg = new Part(leftUpperLeg);
		leftUpperLeg.getChildren().add(leftLowerLeg);
		getChildren().addAll(body,leftLeg,rightLeg);


		/*  Vielleicht wäre es ja von Vorteil mit "Joins" zu arbeiten,
			sodass jedes Gelenk aus Zwei Kugeln besteht, welche immer
			übereinander liegen müssen.
			Sprich: Rotation und danach wieder die Kugeln übereinander
			legen..
		 */

		body.setTranslateY(voxelSize);
		//leftUpperArm.rotateY(45);
		leftShoulder.rotateY(-45);
	}



	public interface SlicedModel {
		public int getCellHeight();
		public URL getHead();
		public URL getBody();
		public URL getRightSchoulder();
		public URL getRightUpperArm();
		public URL getRightLowerArm();
		public URL getRightHand();
		public URL getLeftSchoulder();
		public URL getLeftUpperArm();
		public URL getLeftLowerArm();
		public URL getLeftHand();
		public URL getRightUpperLeg();
		public URL getRightLowerLeg();
		public URL getLeftUpperLeg();
		public URL getLeftLowerLeg();
	}

}
