package main;

import java.nio.DoubleBuffer;
import java.util.Arrays;
import java.util.Vector;

public class Position extends Data {

	private Double x;
	private Double y;

	public Position () {

	}

	public Vector<Double> Position(double x, double y) {
		return new Vector<Double>(Arrays.asList(x,y));
	}

	public void addX() {
		this.x ++;
	}
	public void addY() {
		this.y ++;
	}

	public void addX(Double x) {
		this.x += x;
	}

	public void addY(Double y) {
		this.y += y;
	}


}
