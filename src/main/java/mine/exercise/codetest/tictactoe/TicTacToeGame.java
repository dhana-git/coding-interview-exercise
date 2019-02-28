package mine.exercise.codetest.tictactoe;

public class TicTacToeGame {
	public static void main(String[] args) {
		Player[] players = { new Player(1, "X"), new Player(-1, "O") };
		TicTacToeApp app = new TicTacToeApp(new TicTacToeBoard(), players);
		app.start();
		app.displayBoard();
	}
}
