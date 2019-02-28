package mine.exercise.codetest.xmlparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class FileParserUtil {
	public static XMLStreamReader readXMLFileAsStreamReader(String filePath) throws FileNotFoundException, XMLStreamException {
		XMLInputFactory readerFactory = XMLInputFactory.newInstance();
		return readerFactory.createXMLStreamReader(new FileReader(new File(filePath)));
	}
}
