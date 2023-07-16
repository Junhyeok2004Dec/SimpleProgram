package main;

import java.awt.*;
import java.io.*;

public class Data {


	private float[] sensorData;
	Image image;
	Position position;

	public int movement;
	public Object obj;


	public Data() {

	}

	public Data getData() {
		return this;
	}

	public Data(float[] sensorData, Image image, Object obj) {
		this.image = image;
		this.sensorData = sensorData;
		this.obj = obj;
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

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}


	public void txtToObjFile(File file) {
		try{
			BufferedReader br = new BufferedReader(new
					FileReader(file));

			String textstring;
			if((textstring = br.readLine()) != null) {

				this.obj = textstring;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public int getMovement() {
		return this.movement;
	}

	@Override
	public String toString() {
		String a;

		a = "image : " + image.getSource().toString() + sensorData.toString();
		return a;
	}
}
