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



	public Position(int v, int v1) {
		this.x = v + 0.0;
		this.y = v1 + 0.0;

		//(0.0 요거 써도 됨??)
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

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}


	/*
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";

	}*/

	@Override
	public String toString() {
		return this.x + "";
	}
}
