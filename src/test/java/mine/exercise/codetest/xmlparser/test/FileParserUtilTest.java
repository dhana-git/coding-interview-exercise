package mine.exercise.codetest.xmlparser.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class FileParserUtilTest {
    private static String DEFAULT_FILE_PATH = "./src/test/resources/sample-xml-test-file.xml";

    public static XMLStreamReader readXMLFileAsStreamReader(String filePath) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory readerFactory = XMLInputFactory.newInstance();
        return readerFactory.createXMLStreamReader(new FileReader(new File(filePath)));
    }

    @Test
    public void testIfInvalidFilePathThrowsFNFE() throws FileNotFoundException, XMLStreamException {
        Assertions.assertThrows(FileNotFoundException.class, () -> readXMLFileAsStreamReader("blah/blah"));
    }

    @Test
    public void ableToParseAValidFile() throws FileNotFoundException, XMLStreamException {
        readXMLFileAsStreamReader(DEFAULT_FILE_PATH);
    }
}
