package mine.exercise.codetest.lotto.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import mine.exercise.codetest.csvfilereader.FileParserException;
import mine.exercise.codetest.lotto.LottoMachine;
import mine.exercise.codetest.lotto.LottoTicket;

public class LottoDrawLoaderTest {

	private static String DEFAULT_FILE_PATH = "./src/test/resources/lotto-draws-input.csv";

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

	public List<String> readFromLocalFileSystem(String filePath) throws FileParserException {
		try {
			return Files.readAllLines(Paths.get(filePath));
		} catch (IOException e) {
			throw new FileParserException("Unable to parse a file.");
		}
	}

	@Test
	public void checkForValidTickets() throws FileParserException {
		List<LottoTicket> tickets = loadLottoDraws(DEFAULT_FILE_PATH);
		Assert.assertNotNull(tickets);
		Assert.assertTrue(tickets.size() > 1);
	}

	@Test
	public void checkForValidTicketsMappingFromFile() throws FileParserException {
		List<LottoTicket> tickets = loadLottoDraws(DEFAULT_FILE_PATH);
		Assert.assertNotNull(tickets);
		Assert.assertTrue(tickets.size() > 1);
		tickets.stream().forEach(ticket -> {
			Assert.assertNotNull(ticket.getDrwanDate());
			Assert.assertNotNull(ticket.getId());
			Assert.assertNotNull(ticket.getMachine());
			Assert.assertNotNull(ticket.getNumbers());
		});
	}

	public List<LottoTicket> loadLottoDraws(String filePath) throws FileParserException {
		List<String> drawLines = readFromLocalFileSystem(filePath);

		final AtomicInteger ticketIdCounter = new AtomicInteger(0);
		return drawLines.stream().skip(1).map(draw -> {
			try {
				String[] ticketAttributes = draw.split(",");
				LottoTicket ticket = new LottoTicket();
				ticket.setId(ticketIdCounter.incrementAndGet());
				ticket.setDrwanDate(convertToDate(ticketAttributes[0]));
				ticket.setNumbers(new int[] { Integer.valueOf(ticketAttributes[1]), Integer.valueOf(ticketAttributes[2]), Integer.valueOf(ticketAttributes[3]),
						Integer.valueOf(ticketAttributes[4]), Integer.valueOf(ticketAttributes[5]), Integer.valueOf(ticketAttributes[6]), Integer.valueOf(ticketAttributes[7]) });
				ticket.setMachine(new LottoMachine(Integer.valueOf(ticketAttributes[8]), ticketAttributes[9]));
				return ticket;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}).collect(Collectors.toList());
	}

	@Test(expected = DateTimeParseException.class)
	public void throwDTPEonInvalidDateFormat() {
		System.out.println(convertToDate("03-01-2010"));
	}

	@Test
	public void checkFOrValidDateTimeFormat() {
		convertToDate("03-Mar-2010");
	}

	private static LocalDate convertToDate(String dateAsString) {
		return LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	}

}
