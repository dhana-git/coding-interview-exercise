package mine.exercise.codetest.lotto;

import mine.exercise.codetest.csvfilereader.FileParserException;

public class LottoWinApp {
	private static String DEFAULT_FILE_PATH = "./src/test/resources/lotto-draws-input.csv";

	public static void main(String[] args) throws FileParserException {
		LottoDrawAnalyzer analyzer = new LottoDrawAnalyzer(new LottoDrawLoader());
		analyzer.loadTickets(DEFAULT_FILE_PATH);
		System.out.println("Three most frequent drawn numbers are:");
		System.out.println(analyzer.getThreeMostFrequentlyDrawnNumbers());
	}
}
