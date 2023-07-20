package main;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Data {


	private ArrayList<String> sensorData = new ArrayList<>();

	private int[] Vector; // get Vector from two var(integer, by file input stream)



	Image image;
	Position position;

	public int movement;
	public Object obj;


	public Data() {

	}



	public void addVar(float var) {
		this.sensorData.add(String.valueOf(var));

	}



//포지션 -> get Vector(2 - dimension -> get )
	public void getVector2f(int e1, int e2) {

		this.Vector[0] = e1;
		this.Vector[1] = e2;

	}

	//getCompressed() -> {};
	public int getE1() {
		return this.Vector[0];
	}
	public int getE2() {
		return this.Vector[1];
	}
	public Data getData() {
		return this;
	}





	public Data(ArrayList<String> sensorData, Image image, Object obj) {
		this.image = image;
		this.sensorData = sensorData;
		this.obj = obj;
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
