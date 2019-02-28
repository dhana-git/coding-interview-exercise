package mine.exercise.codetest.csvfilereader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CSVFileReader implements DelimitedFileReader {
	private String filePath;
	private static String DEFAULT_FILE_PATH = "./src/test/resources/sampe-csv-test-file.csv";

	public CSVFileReader() {
		this.filePath = DEFAULT_FILE_PATH;
	}

	public CSVFileReader(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<String> readEntireFile() throws FileParserException {
		return FileReaderUtil.readFromLocalFileSystem(filePath);
	}

	@Override
	public Optional<String> readOneLine(int rowNum) throws FileParserException {
		List<String> fileContent = FileReaderUtil.readFromLocalFileSystem(filePath);
		if (fileContent.size() > rowNum) {
			return Optional.ofNullable(fileContent.get(rowNum));
		}
		return Optional.empty();
	}

	@Override
	public Optional<String> readCellAsString(int rowNum, int columNum) throws FileParserException {
		List<String> fileContent = FileReaderUtil.readFromLocalFileSystem(filePath);
		if (fileContent.size() > rowNum) {
			return Optional.ofNullable(fileContent.get(rowNum));
		}
		return Optional.empty();
	}

	@Override
	public Optional<Integer> readCellAsInteger(int rowNum, int columNum) throws FileParserException {
		String[] lineArray = readCellAsString(rowNum, columNum).orElse("").split(",");
		if (lineArray.length > columNum) {
			return Optional.ofNullable(Integer.valueOf(lineArray[columNum]));
		}
		return Optional.empty();
	}

	@Override
	public Optional<Double> readCellAsDouble(int rowNum, int columNum) throws FileParserException {
		String[] lineArray = readCellAsString(rowNum, columNum).orElse("").split(",");
		if (lineArray.length > columNum) {
			return Optional.ofNullable(Double.valueOf(lineArray[columNum]));
		}
		return Optional.empty();
	}

	@Override
	public Optional<LocalDateTime> readCellAsDate(int rowNum, int columNum) throws FileParserException {
		String[] lineArray = readCellAsString(rowNum, columNum).orElse("").split(",");
		if (lineArray.length > columNum) {
			return Optional.ofNullable(DateUtil.convertToDate(lineArray[columNum]));
		}
		return Optional.empty();
	}

}
