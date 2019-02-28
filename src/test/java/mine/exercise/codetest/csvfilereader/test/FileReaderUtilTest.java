package mine.exercise.codetest.csvfilereader.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mine.exercise.codetest.csvfilereader.FileParserException;

public class FileReaderUtilTest {

	private static String DEFAULT_FILE_PATH = "./src/test/resources/sample-csv-test-file.csv";

	@Test(expected = FileParserException.class)
	public void checkIfExcpetionIsThrownOnReadingInvalidFile() throws FileParserException {
		readFromLocalFileSystem("blah/blah");
	}

	@Test
	public void checkForValidFilePath() throws FileParserException {
		List<String> fileContent = readFromLocalFileSystem(DEFAULT_FILE_PATH);
		Assert.assertNotNull(fileContent);
	}

	@Test
	public void checkForValidFileContent() throws FileParserException {
		List<String> fileContent = readFromLocalFileSystem(DEFAULT_FILE_PATH);
		Assert.assertNotNull(fileContent);
		Assert.assertTrue(fileContent.size() > 0);
	}

	public static List<String> readFromLocalFileSystem(String filePath) throws FileParserException {
		try {
			return Files.readAllLines(Paths.get(filePath));
		} catch (IOException e) {
			throw new FileParserException("Unable to parse a file.");
		}
	}

}
