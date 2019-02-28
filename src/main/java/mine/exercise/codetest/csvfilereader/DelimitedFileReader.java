package mine.exercise.codetest.csvfilereader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DelimitedFileReader {
	List<String> readEntireFile() throws FileParserException;

	Optional<String> readOneLine(int rowNum) throws FileParserException;

	Optional<String> readCellAsString(int rowNum, int columNum) throws FileParserException;

	Optional<Integer> readCellAsInteger(int rowNum, int columNum) throws FileParserException;

	Optional<Double> readCellAsDouble(int rowNum, int columNum) throws FileParserException;

	Optional<LocalDateTime> readCellAsDate(int rowNum, int columNum) throws FileParserException;

}
