package ext3d.entities;


import java.net.URL;

import javafx.scene.Group;

public class Person extends Group {
	protected Group head;
	protected Group torso;

	protected Group rightArm;
	protected Group rightShoulder;
	protected Group rightUpperArm;
	protected Group rightLowerArm;
	protected Group rightHand;

	protected Group leftArm;
	protected Group leftShoulder;
	protected Group leftUpperArm;
	protected Group leftLowerArm;
	protected Group leftHand;

	protected Group rightLeg;
	protected Group rightUpperLeg;
	protected Group rightLowerLeg;
	protected Group leftLeg;
	protected Group leftUpperLeg;
	protected Group leftLowerLeg;

	public Group getHead() {
		return head;
	}

	public Group getTorso() {
		return torso;
	}

	public Group getRightArm() {
		return rightArm;
	}

	public Group getRightShoulder() {
		return rightShoulder;
	}

	public Group getRightUpperArm() {
		return rightUpperArm;
	}

	public Group getRightLowerArm() {
		return rightLowerArm;
	}

	public Group getRightHand() {
		return rightHand;
	}

	public Group getLeftArm() {
		return leftArm;
	}

	public Group getLeftShoulder() {
		return leftShoulder;
	}

	public Group getLeftUpperArm() {
		return leftUpperArm;
	}

	public Group getLeftLowerArm() {
		return leftLowerArm;
	}

	public Group getLeftHand() {
		return leftHand;
	}

	public Group getRightLeg() {
		return rightLeg;
	}

	public Group getRightUpperLeg() {
		return rightUpperLeg;
	}

	public Group getRightLowerLeg() {
		return rightLowerLeg;
	}

	public Group getLeftLeg() {
		return leftLeg;
	}

	public Group getLeftUpperLeg() {
		return leftUpperLeg;
	}

	public Group getLeftLowerLeg() {
		return leftLowerLeg;
	}

	public interface SlicedModel {
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
