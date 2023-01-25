package main;

import java.io.Serializable;
import java.util.ArrayList;

public class ReturnMethod implements Serializable
{


	static final long serialVersionUID = 1L;


	private String name;
	private String date;
	private String site;


	private ArrayList<Object> type;
	private ArrayList<Object> bad;





	public String getName() {
		return name;
	}



	public String getDate() {
	return date;
}

	public String getSite() {
		return site;
	}



	public ReturnMethod(String name, String date, String site,
	                    ReturnMethod type, ArrayList<Object> bad)
	{

		this.bad=bad;
		this.type=type;
		this.date=date;
		this.name=name;
		this.site = site;


	}




	@Override
	public String toString() {
		return "ReturnMethod{" +
				"name='" + name + '\'' +
				", date='" + date + '\'' +
				", site='" + site + '\'' +
				", type=" + type +
				", bad=" + bad +
				'}';
	}
}
