package mine.exercise.codetest.xmlparser;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

public interface FlatFileParser {
	String getWholeXMLFileAsString() throws FileNotFoundException, XMLStreamException;

	String getMatchingElementAsString(String element) throws FileNotFoundException, XMLStreamException;
}
