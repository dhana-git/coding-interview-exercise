package mine.exercise.codetest.csvfilereader.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.Test;

public class DateUtilTest {
	@Test(expected = DateTimeParseException.class)
	public void throwDTPEonInvalidDateFormat() {
		convertToDate("2019-02-01");
	}

	@Test
	public void checkFOrValidDateTimeFormat() {
		convertToDate("2019-02-01T16:07:02");
	}

	public static LocalDateTime convertToDate(String dateAsString) {
		return LocalDateTime.parse(dateAsString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
}
