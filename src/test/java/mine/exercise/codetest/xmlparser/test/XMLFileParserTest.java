package mine.exercise.codetest.xmlparser.test;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.junit.Assert;
import org.junit.Test;

import mine.exercise.codetest.xmlparser.FlatFileParser;

public class XMLFileParserTest implements FlatFileParser {
	private String filePath;
	private static String DEFAULT_FILE_PATH = "./src/test/resources/sample-xml-test-file.xml";

	public XMLFileParserTest() {
		this.filePath = DEFAULT_FILE_PATH;
	}

	/*-public XMLFileParserTest(String filePath) {
		this.filePath = filePath;
	}*/

	@Test
	public void chewckIfGetWholeXMlFileAsStringReturnsData() throws FileNotFoundException, XMLStreamException {
		String xmlContent = getWholeXMLFileAsString();
		Assert.assertNotNull(xmlContent);
		System.out.println(xmlContent);
	}

	@Override
	public String getWholeXMLFileAsString() throws FileNotFoundException, XMLStreamException {
		return getMatchingElementAsString(null);
	}

	@Test
	public void chewckIfGetMatchingElementAsStringReturnsData() throws FileNotFoundException, XMLStreamException {
		String xmlContent = getMatchingElementAsString("address");
		Assert.assertNotNull(xmlContent);
		System.out.println(xmlContent);
	}

	@Test
	public void chewckIfGetMatchingElementAsStringReturnsData2() throws FileNotFoundException, XMLStreamException {
		String xmlContent = getMatchingElementAsString("line_1");
		Assert.assertNotNull(xmlContent);
		System.out.println(xmlContent);
	}

	@Override
	public String getMatchingElementAsString(String element) throws FileNotFoundException, XMLStreamException {
		StringBuilder xmlContent = new StringBuilder();
		XMLStreamReader streamReader = FileParserUtilTest.readXMLFileAsStreamReader(filePath);
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
