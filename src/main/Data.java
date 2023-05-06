package main;

import java.awt.*;

public class Data {


	private float[] sensorData;
	Image image;

	public Data() {

	}

	public Data getData() {
		return this;
	}

	public Data(float[] sensorData, Image image) {
		this.image = image;
		this.sensorData = sensorData;
	}



	@Override
	public String toString() {
		String a;

		a = "image : " + image.getSource().toString() + sensorData.toString();
		return a;
	}
}
