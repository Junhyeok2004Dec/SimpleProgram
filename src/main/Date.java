package main;

import java.text.SimpleDateFormat;

public class Date {





	java.util.Date sumbitDay = new java.util.Date();
	SimpleDateFormat dataFormat = new SimpleDateFormat("제 yyyy년 MM월 dd일 HH:MM");

	private String data = dataFormat.format(sumbitDay);


	public String getDate() {
		return this.data;
	}

}
