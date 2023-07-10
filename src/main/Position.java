package main;

import java.nio.DoubleBuffer;
import java.util.Arrays;
import java.util.Vector;

public class Position extends Data {

	private Double x;
	private Double y;

	public Position () {

	}

	public Position(double v, double v1) {
		this.x = v;
		this.y = v1;

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
	public void subX() {
		this.x --;
	}
	public void subY() {
		this.y --;
	}

	public void modX(Double x) {
		this.x += x;
	}

	public void modY(Double y) {
		this.y += y;
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";

	}
}
