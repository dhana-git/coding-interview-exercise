package mine.exercise.codetest.csvfilereader.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateUtilTest {
    public static LocalDateTime convertToDate(String dateAsString) {
        return LocalDateTime.parse(dateAsString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Test
    public void throwDTPEonInvalidDateFormat() {
        Assertions.assertThrows(DateTimeParseException.class, () -> convertToDate("2019-02-01"));
    }

    @Test
    public void checkFOrValidDateTimeFormat() {
        convertToDate("2019-02-01T16:07:02");
    }
}
