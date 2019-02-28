package mine.exercise.codetest.lotto;

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

import mine.exercise.codetest.csvfilereader.FileParserException;

public class LottoDrawAnalyzer {
	private LottoDrawLoader lottoDrawLoader;
	private List<LottoTicket> tickets;

	public LottoDrawAnalyzer(LottoDrawLoader lottoDrawLoader) {
		this.lottoDrawLoader = lottoDrawLoader;
	}

	public void loadTickets(String filePath) throws FileParserException {
		tickets = lottoDrawLoader.loadLottoDraws(filePath);
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
