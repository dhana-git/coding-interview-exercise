package mine.exercise.codetest.lotto;

import mine.exercise.codetest.csvfilereader.FileParserException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LottoDrawLoader {

    private static LocalDate convertToDate(String dateAsString) {
        return LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }

    public List<String> readFromLocalFileSystem(String filePath) throws FileParserException {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new FileParserException("Unable to parse a file.");
        }
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
                ticket.setNumbers(new int[]{Integer.valueOf(ticketAttributes[1]), Integer.valueOf(ticketAttributes[2]), Integer.valueOf(ticketAttributes[3]),
                        Integer.valueOf(ticketAttributes[4]), Integer.valueOf(ticketAttributes[5]), Integer.valueOf(ticketAttributes[6]), Integer.valueOf(ticketAttributes[7])});
                ticket.setMachine(new LottoMachine(Integer.valueOf(ticketAttributes[8]), ticketAttributes[9]));
                return ticket;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }).collect(Collectors.toList());
    }

}
