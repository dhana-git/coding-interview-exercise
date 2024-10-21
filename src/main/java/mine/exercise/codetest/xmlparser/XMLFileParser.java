package mine.exercise.codetest.xmlparser;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;

public class XMLFileParser implements FlatFileParser {
    private static String DEFAULT_FILE_PATH = "./src/test/resources/sample-xml-test-file.xml";
    private String filePath;

    public XMLFileParser() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    public XMLFileParser(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getWholeXMLFileAsString() throws FileNotFoundException, XMLStreamException {
        return getMatchingElementAsString(null);
    }

    @Override
    public String getMatchingElementAsString(String element) throws FileNotFoundException, XMLStreamException {
        StringBuilder xmlContent = new StringBuilder();
        XMLStreamReader streamReader = FileParserUtil.readXMLFileAsStreamReader(filePath);
        boolean matchingStartElementFound = false;
        boolean matchingEndElementFound = false;
        while (streamReader.hasNext()) {
            int parsingEventType = streamReader.next();
            if (element != null) {
                if (parsingEventType == XMLStreamReader.START_ELEMENT && streamReader.getLocalName().equals(element)) {
                    matchingStartElementFound = true;
                }

                if (!matchingStartElementFound || (matchingStartElementFound && matchingEndElementFound)) {
                    continue;
                }

                if (parsingEventType == XMLStreamReader.END_ELEMENT && streamReader.getLocalName().equals(element)) {
                    matchingEndElementFound = true;
                }
            }

            switch (parsingEventType) {
                case XMLStreamReader.START_ELEMENT:
                    xmlContent.append("<" + streamReader.getLocalName() + ">");
                    break;
                case XMLStreamReader.END_ELEMENT:
                    xmlContent.append("</" + streamReader.getLocalName() + ">");
                    break;
                case XMLStreamReader.CHARACTERS:
                case XMLStreamReader.DTD:
                case XMLStreamReader.ENTITY_REFERENCE:
                case XMLStreamReader.COMMENT:
                case XMLStreamReader.SPACE:
                    if (streamReader.hasText())
                        xmlContent.append(streamReader.getText());
                    break;
            }

        }
        return xmlContent.toString();
    }

}
