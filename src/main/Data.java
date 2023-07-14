package main;

import java.awt.*;

public class Data {


	private float[] sensorData;
	Image image;
	Position position;

	public int movement;

	public Data() {

	}

	public Data getData() {
		return this;
	}

	public Data(float[] sensorData, Image image) {
		this.image = image;
		this.sensorData = sensorData;
	}


	public float[] getSensorData() {
		return sensorData;
	}

	public Image getImage() {
		return image;
	}

	public Position getPosition() {
		return position;
	}


	public void setPosition(Position position) {
		this.position = position;
	}

	public void setMovement(int mo) {
		this.movement += mo;

		System.out.println(this.getMovement());
	}

	public int getMovement() {
		System.out.println(movement);
		return this.movement;
	}

	@Override
	public String toString() {
		String a;

		a = "image : " + image.getSource().toString() + sensorData.toString();
		return a;
	}
}
