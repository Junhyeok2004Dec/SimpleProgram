package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Date {


	LocalTime now = LocalTime.now();
	LocalDate SeoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));




	DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("제 yyyy년 MM월 dd일");
	DateTimeFormatter formatterUS = DateTimeFormatter.ofPattern(
			"yyyy.MM.dd"
	);

	private String data = SeoulNow.format(formatter);
	private String time = now.format(formatTime);

	public String getDate() {
		now = LocalTime.now();
		SeoulNow = LocalDate.now(
				ZoneId.of("Asia/Seoul")
		);


		data = SeoulNow.format(formatterUS);
		return this.data;
	}

	public String getTime() {

		now = LocalTime.now();
		SeoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));

		this.time = now.format(formatTime);
		return this.time;
	}

	public void timeUpdate() {
		now = LocalTime.now();
		SeoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));


		this.data = SeoulNow.format(formatter);
		this.time = now.format(formatTime);
	}

}
