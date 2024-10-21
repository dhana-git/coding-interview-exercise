package mine.exercise.codetest.csvfilereader.test;

import mine.exercise.codetest.csvfilereader.DelimitedFileReader;
import mine.exercise.codetest.csvfilereader.FileParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CSVFileReaderTest implements DelimitedFileReader {
    private static String DEFAULT_FILE_PATH = "./src/test/resources/sample-csv-test-file.csv";
    private String filePath;

    public CSVFileReaderTest() {
        this.filePath = DEFAULT_FILE_PATH;
    }

	/*-public CSVFileReaderTest(String filePath) {
		this.filePath = filePath;
	}*/

    @Test
    public void checkIfAbleToReadEntireFileContent() throws FileParserException {
        List<String> fileContent = readEntireFile();
        Assertions.assertTrue(fileContent.size() > 0);
    }

    @Override
    public List<String> readEntireFile() throws FileParserException {
        return FileReaderUtilTest.readFromLocalFileSystem(filePath);
    }

    @Test
    public void checkIfAbleToReadOutOfRangeLine() throws FileParserException {
        Optional<String> fileContent = readOneLine(1000);
        Assertions.assertThrows(NoSuchElementException.class, () -> fileContent.get());
    }

    @Test
    public void checkIfAbleToReadOneLine() throws FileParserException {
        Optional<String> fileContent = readOneLine(0);
        fileContent.get();
    }

    @Override
    public Optional<String> readOneLine(int rowNum) throws FileParserException {
        List<String> fileContent = FileReaderUtilTest.readFromLocalFileSystem(filePath);
        if (fileContent.size() > rowNum) {
            return Optional.ofNullable(fileContent.get(rowNum));
        }
        return Optional.empty();
    }

    @Test
    public void checkIfAbleToReadOutOfRangeCellAsString() throws FileParserException {
        Optional<String> fileContent = readCellAsString(1000, 0);
        Assertions.assertThrows(NoSuchElementException.class, () -> fileContent.get());
    }

    @Test
    public void checkIfAbleToReadOneCellAsString() throws FileParserException {
        Optional<String> fileContent = readCellAsString(0, 0);
        Assertions.assertTrue(fileContent.get() != null);
    }

    @Override
    public Optional<String> readCellAsString(int rowNum, int columNum) throws FileParserException {
        List<String> fileContent = FileReaderUtilTest.readFromLocalFileSystem(filePath);
        if (fileContent.size() > rowNum) {
            return Optional.ofNullable(fileContent.get(rowNum));
        }
        return Optional.empty();
    }

    @Test
    public void checkToToReadOneCellAsIntegerThrowsNFE() throws FileParserException {
        Optional<Integer> fileContent = readCellAsInteger(0, 3);
        Assertions.assertThrows(NumberFormatException.class, () -> fileContent.get());
    }

    @Test
    public void checkIfAbleToReadOneCellAsInteger() throws FileParserException {
        Optional<Integer> fileContent = readCellAsInteger(0, 1);
        Assertions.assertTrue(fileContent.get() != null);
    }

    @Override
    public Optional<Integer> readCellAsInteger(int rowNum, int columNum) throws FileParserException {
        String[] lineArray = readCellAsString(rowNum, columNum).orElse("").split(",");
        if (lineArray.length > columNum) {
            return Optional.ofNullable(Integer.valueOf(lineArray[columNum]));
        }
        return Optional.empty();
    }

    @Test
    public void checkToToReadOneCellAsDoubleThrowsNFE() throws FileParserException {
        Optional<Double> fileContent = readCellAsDouble(0, 3);
        Assertions.assertThrows(NumberFormatException.class, () -> fileContent.get());
    }

    @Test
    public void checkIfAbleToReadOneCellAsDouble() throws FileParserException {
        Optional<Double> fileContent = readCellAsDouble(0, 2);
        Assertions.assertTrue(fileContent.get() != null);
    }

    @Override
    public Optional<Double> readCellAsDouble(int rowNum, int columNum) throws FileParserException {
        String[] lineArray = readCellAsString(rowNum, columNum).orElse("").split(",");
        if (lineArray.length > columNum) {
            return Optional.ofNullable(Double.valueOf(lineArray[columNum]));
        }
        return Optional.empty();
    }

    @Test
    public void checkToToReadOneCellAsDateThrowsDTPE() throws FileParserException {
        Optional<LocalDateTime> fileContent = readCellAsDate(0, 2);
        Assertions.assertThrows(DateTimeParseException.class, () -> fileContent.get());
    }

    @Test
    public void checkIfAbleToReadOneCellAsDate() throws FileParserException {
        Optional<LocalDateTime> fileContent = readCellAsDate(0, 3);
        Assertions.assertTrue(fileContent.get() != null);
    }

    @Override
    public Optional<LocalDateTime> readCellAsDate(int rowNum, int columNum) throws FileParserException {
        String[] lineArray = readCellAsString(rowNum, columNum).orElse("").split(",");
        if (lineArray.length > columNum) {
            return Optional.ofNullable(DateUtilTest.convertToDate(lineArray[columNum]));
        }
        return Optional.empty();
    }

}
