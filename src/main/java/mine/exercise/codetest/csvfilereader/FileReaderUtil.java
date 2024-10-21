package mine.exercise.codetest.csvfilereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderUtil {

    private static String DEFAULT_FILE_PATH = "./src/test/resources/sampe-csv-test-file.csv";

    public static List<String> readFromLocalFileSystem(String filePath) throws FileParserException {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new FileParserException("Unable to parse a file.");
        }
    }

    @Test()
    public void checkIfExcpetionIsThrownOnReadingInvalidFile() throws FileParserException {
        Assertions.assertThrows(FileParserException.class, () -> readFromLocalFileSystem("blah/blah"));
    }

    @Test
    public void checkForValidFilePath() throws FileParserException {
        List<String> fileContent = readFromLocalFileSystem(DEFAULT_FILE_PATH);
        Assertions.assertNotNull(fileContent);
    }

    @Test
    public void checkForValidFileContent() throws FileParserException {
        List<String> fileContent = readFromLocalFileSystem(DEFAULT_FILE_PATH);
        Assertions.assertNotNull(fileContent);
        Assertions.assertTrue(fileContent.size() > 0);
    }

}
