package mine.exercise.codetest.lotto.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import mine.exercise.codetest.csvfilereader.FileParserException;
import mine.exercise.codetest.lotto.LottoTicket;

public class LottoDrawAnalyzerTest {
	private LottoDrawLoaderTest lottoDrawLoader;
	private List<LottoTicket> tickets;

	public LottoDrawAnalyzerTest() {
		this.lottoDrawLoader = new LottoDrawLoaderTest();
	}

	/*-public LottoDrawAnalyzer(LottoDrawLoaderTest lottoDrawLoader) {
		this.lottoDrawLoader = lottoDrawLoader;
	}*/

	public void loadTickets(String filePath) throws FileParserException {
		tickets = lottoDrawLoader.loadLottoDraws(filePath);
	}

	@Test
	public void getThreeMostFrequentlyDrawnNumbersReturnsOneValueForOneTicketWithOneNumber() {
		LottoTicket ticket = new LottoTicket();
		ticket.setNumbers(new int[] { 99 });
		this.tickets = Arrays.asList(ticket);
		Assert.assertTrue(getThreeMostFrequentlyDrawnNumbers().size() == 1);
		Assert.assertEquals(99, (int) getThreeMostFrequentlyDrawnNumbers().get(0));
	}

	@Test
	public void getThreeMostFrequentlyDrawnNumbersReturnsOneValueForMultipleTicketWithIdenticalNumbers() {
		LottoTicket ticket1 = new LottoTicket();
		ticket1.setNumbers(new int[] { 999 });
		LottoTicket ticket2 = new LottoTicket();
		ticket2.setNumbers(new int[] { 999 });
		LottoTicket ticket3 = new LottoTicket();
		ticket3.setNumbers(new int[] { 999 });
		this.tickets = Arrays.asList(ticket1, ticket2, ticket3);
		Assert.assertTrue(getThreeMostFrequentlyDrawnNumbers().size() == 1);
		Assert.assertEquals(999, (int) getThreeMostFrequentlyDrawnNumbers().get(0));
	}

	@Test
	public void getThreeMostFrequentlyDrawnNumbersReturnsOneValueForMultipleTicketWithUniqueNumbers() {
		LottoTicket ticket1 = new LottoTicket();
		ticket1.setNumbers(new int[] { 99 });
		LottoTicket ticket2 = new LottoTicket();
		ticket2.setNumbers(new int[] { 999 });
		LottoTicket ticket3 = new LottoTicket();
		ticket3.setNumbers(new int[] { 9999 });
		this.tickets = Arrays.asList(ticket1, ticket2, ticket3);
		System.out.println(getThreeMostFrequentlyDrawnNumbers());
		Assert.assertTrue(getThreeMostFrequentlyDrawnNumbers().size() == 3);
	}

	@Test
	public void getThreeMostFrequentlyDrawnNumbersReturnsOneValueForMultipleTicketWithDupNumbers() {
		LottoTicket ticket1 = new LottoTicket();
		ticket1.setNumbers(new int[] { 99 });
		LottoTicket ticket2 = new LottoTicket();
		ticket2.setNumbers(new int[] { 999 });
		LottoTicket ticket3 = new LottoTicket();
		ticket3.setNumbers(new int[] { 9999 });
		LottoTicket ticket4 = new LottoTicket();
		ticket4.setNumbers(new int[] { 9999 });
		this.tickets = Arrays.asList(ticket1, ticket2, ticket3, ticket4);
		Assert.assertTrue(getThreeMostFrequentlyDrawnNumbers().size() == 3);
		Assert.assertEquals(9999, (int) getThreeMostFrequentlyDrawnNumbers().get(0));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Integer> getThreeMostFrequentlyDrawnNumbers() {
		Map<Integer, Integer> ticketNumToFreqMap = groupTicketNumToFreq(tickets);
		Set<Integer> sortedFreqInDesc = new TreeSet(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		sortedFreqInDesc.addAll(ticketNumToFreqMap.values());
		List<Integer> threeMostFrequentlyDrawnNumbers = new ArrayList<>();
		sortedFreqInDesc.stream().forEach(value -> {
			ticketNumToFreqMap.entrySet().stream().filter(entry -> entry.getValue().equals(value) && threeMostFrequentlyDrawnNumbers.size() <= 3).mapToInt(e -> e.getKey())
					.forEach(val -> threeMostFrequentlyDrawnNumbers.add(val));
		});
		return threeMostFrequentlyDrawnNumbers;
	}

	@Test
	public void checkIfNoValuesReturnedOnEmptyTicket() {
		Assert.assertTrue(groupTicketNumToFreq(null).size() <= 0);
	}

	@Test
	public void groupTicketNumToFreqReturnsOneValueForOneTicketWithOneNumber() {
		LottoTicket ticket = new LottoTicket();
		ticket.setNumbers(new int[] { 99 });
		List<LottoTicket> tickets = Arrays.asList(ticket);
		Assert.assertTrue(groupTicketNumToFreq(tickets).size() == 1);
		Assert.assertEquals(1, (int) groupTicketNumToFreq(tickets).get(99));
	}

	@Test
	public void groupTicketNumToFreqReturnsOneValueForMultipleTicketWithIdenticalNumbers() {
		LottoTicket ticket1 = new LottoTicket();
		ticket1.setNumbers(new int[] { 999 });
		LottoTicket ticket2 = new LottoTicket();
		ticket2.setNumbers(new int[] { 999 });
		LottoTicket ticket3 = new LottoTicket();
		ticket3.setNumbers(new int[] { 999 });
		List<LottoTicket> tickets = Arrays.asList(ticket1, ticket2, ticket3);
		Assert.assertTrue(groupTicketNumToFreq(tickets).size() == 1);
		Assert.assertEquals(3, (int) groupTicketNumToFreq(tickets).get(999));
	}

	@Test
	public void groupTicketNumToFreqReturnsOneValueForMultipleTicketWithUniqueNumbers() {
		LottoTicket ticket1 = new LottoTicket();
		ticket1.setNumbers(new int[] { 99 });
		LottoTicket ticket2 = new LottoTicket();
		ticket2.setNumbers(new int[] { 999 });
		LottoTicket ticket3 = new LottoTicket();
		ticket3.setNumbers(new int[] { 9999 });
		List<LottoTicket> tickets = Arrays.asList(ticket1, ticket2, ticket3);
		Assert.assertTrue(groupTicketNumToFreq(tickets).size() == 3);
		Assert.assertEquals(1, (int) groupTicketNumToFreq(tickets).get(99));
		Assert.assertEquals(1, (int) groupTicketNumToFreq(tickets).get(999));
		Assert.assertEquals(1, (int) groupTicketNumToFreq(tickets).get(9999));
	}

	private Map<Integer, Integer> groupTicketNumToFreq(List<LottoTicket> tickets) {
		if (tickets == null || tickets.size() < 1) {
			return new HashMap<Integer, Integer>();
		}
		Map<Integer, AtomicInteger> ticketNumGroup = new HashMap<>();
		tickets.stream().forEach(ticket -> {
			Arrays.stream(ticket.getNumbers()).forEach(num -> {
				if (ticketNumGroup.containsKey(num)) {
					ticketNumGroup.get(num).getAndIncrement();
				} else {
					ticketNumGroup.put(num, new AtomicInteger(1));
				}
			});

		});
		return ticketNumGroup.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get()));
	}
}
