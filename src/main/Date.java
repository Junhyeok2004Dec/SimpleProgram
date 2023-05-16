package main;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Date {


	LocalTime now = LocalTime.now();
	LocalDate SeoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));




	DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("제 yyyy년 MM월 dd일");

	private String data = SeoulNow.format(formatter);
	private String time = now.format(formatTime);

	public String getDate() {
		return this.data;
	}

	public String getTime() {
		return this.time;
	}

}
