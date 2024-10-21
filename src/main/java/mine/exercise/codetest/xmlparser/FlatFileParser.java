package mine.exercise.codetest.xmlparser;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public interface FlatFileParser {
    String getWholeXMLFileAsString() throws FileNotFoundException, XMLStreamException;

    String getMatchingElementAsString(String element) throws FileNotFoundException, XMLStreamException;
}
