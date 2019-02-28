package mine.exercise.codetest.lotto;

import java.time.LocalDate;

public class LottoTicket {
	private int id;
	private int[] numbers;
	private LocalDate drwanDate;
	private LottoMachine machine;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public LocalDate getDrwanDate() {
		return drwanDate;
	}

	public void setDrwanDate(LocalDate drwanDate) {
		this.drwanDate = drwanDate;
	}

	public LottoMachine getMachine() {
		return machine;
	}

	public void setMachine(LottoMachine machine) {
		this.machine = machine;
	}

}
