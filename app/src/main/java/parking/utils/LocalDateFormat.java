package parking.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateFormat {
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	public static String getTimeToStringByLocalDateTime(LocalDateTime time) {
		return time.format(TIME_FORMATTER);
	}

	public static LocalDateTime getLocalDateTimeByString(String time) {
		return LocalDateTime.parse(time, TIME_FORMATTER);
	}
}
