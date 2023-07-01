package parking.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateFormat {
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	public static LocalDateTime getLocalDateToFormatString(LocalDateTime time) {
		return LocalDateTime.parse(time.toString(), TIME_FORMATTER);
	}
}
