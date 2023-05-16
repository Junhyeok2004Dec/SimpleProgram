package main;

import java.awt.*;

public class Data {


	private float[] sensorData;
	Image image;
	Position position;

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

	@Override
	public String toString() {
		String a;

		a = "image : " + image.getSource().toString() + sensorData.toString();
		return a;
	}
}
