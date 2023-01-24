package main;

import java.text.SimpleDateFormat;

public class Date {


	java.util.Date sumbitDay = new java.util.Date();
	SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM");
	String data = dataFormat.format(sumbitDay);



}
