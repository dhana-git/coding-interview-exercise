package mine.exercise.codetest.xmlparser.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.junit.Test;

public class FileParserUtilTest {
	private static String DEFAULT_FILE_PATH = "./src/test/resources/sample-xml-test-file.xml";

	@Test(expected = FileNotFoundException.class)
	public void testIfInvalidFilePathThrowsFNFE() throws FileNotFoundException, XMLStreamException {
		readXMLFileAsStreamReader("blah/blah");
	}

	@Test
	public void ableToParseAValidFile() throws FileNotFoundException, XMLStreamException {
		readXMLFileAsStreamReader(DEFAULT_FILE_PATH);
	}

	public static XMLStreamReader readXMLFileAsStreamReader(String filePath) throws FileNotFoundException, XMLStreamException {
		XMLInputFactory readerFactory = XMLInputFactory.newInstance();
		return readerFactory.createXMLStreamReader(new FileReader(new File(filePath)));
	}
}
