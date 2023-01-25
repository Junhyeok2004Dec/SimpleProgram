package main;

import java.util.ArrayList;

public class ReturnMethod
{



	private String name;
	private String date;
	private String site;


	private ArrayList<Object> data;





	public String getName() {
		return name;
	}



	public String getDate() {
	return date;
}

	public String getSite() {
		return site;
	}

	public ReturnMethod getData() {
		return this;
	}


}
