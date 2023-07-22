package main;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Data {


	public int movement;
	public Object obj;
	Image image;
	Position position;
	private ArrayList<String> sensorData = new ArrayList<>();
	private int[] Vector; // get Vector from two var(integer, by file input stream)


	public Data() {

	}


	public Data(ArrayList<String> sensorData, Image image, Object obj) {
		this.image = image;
		this.sensorData = sensorData;
		this.obj = obj;
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

	public Image getImage() {
		return image;
	}

	public Position getPosition() {
		return position;
	}


	public void setPosition(int e1, int e2) {

		this.position = new Position(e1, e2);
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void txtToObjFile(File file) {
		String[] splitData;


		try {

			this.obj = new String(Files.readAllBytes(Paths.get(tempData.path)));
			splitData = (String.valueOf(this.obj)).split(",");


			BufferedReader br = new BufferedReader(new
					FileReader(file));

			String textstring;
			if ((textstring = br.readLine()) != null) {

				this.obj = textstring;
				this.position.setPosition(Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1]));


			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public int getMovement() {
		return this.movement;
	}

	public void setMovement(int mo) {
		this.movement += mo;

		System.out.println(this.getMovement());
	}

	@Override
	public String toString() {
		String a;

		a = "image : " + image.getSource().toString() + sensorData.toString();
		return a;
	}
}
