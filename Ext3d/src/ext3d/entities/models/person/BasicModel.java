package ext3d.entities.models.person;


import java.net.URL;

import ext3d.entities.Person;


public class BasicModel implements Person.SlicedModel{
	@Override
	public int getCellHeight() {
		return 32;
	}
	@Override
	public URL getHead() {
		return this.getClass().getResource("basic_expo-Head.png");
	}
	@Override
	public URL getBody() {
		return this.getClass().getResource("basic_expo-Body.png");
	}
	@Override
	public URL getRightSchoulder() {
		return this.getClass().getResource("basic_expo-RArm_0.png");
	}
	@Override
	public URL getRightUpperArm() {
		return this.getClass().getResource("basic_expo-RArm_1.png");
	}
	@Override
	public URL getRightLowerArm() {
		return this.getClass().getResource("basic_expo-RArm_2.png");
	}
	@Override
	public URL getRightHand() {
		return null;//this.getClass().getResource("basic_expo-.png");
	}
	@Override
	public URL getLeftSchoulder() {
		return this.getClass().getResource("basic_expo-LArm_0.png");
	}
	@Override
	public URL getLeftUpperArm() {
		return this.getClass().getResource("basic_expo-LArm_1.png");
	}
	@Override
	public URL getLeftLowerArm() {
		return this.getClass().getResource("basic_expo-LArm_2.png");
	}
	@Override
	public URL getLeftHand() {
		return null;//this.getClass().getResource("basic_expo-.png");
	}
	@Override
	public URL getRightUpperLeg() {
		return this.getClass().getResource("basic_expo-RLeg_1.png");
	}
	@Override
	public URL getRightLowerLeg() {
		return this.getClass().getResource("basic_expo-RLeg_2.png");
	}
	@Override
	public URL getLeftUpperLeg() {
		return this.getClass().getResource("basic_expo-LLeg_1.png");
	}
	@Override
	public URL getLeftLowerLeg() {
		return this.getClass().getResource("basic_expo-LLeg_2.png");
	}
}
