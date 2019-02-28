package mine.exercise.codetest.tictactoe;

import java.util.stream.IntStream;

import org.junit.Test;

import mine.exercise.codetest.tictactoe.Player;

public class TicTacToeBoard {
	int[][] boardState;

	public TicTacToeBoard() {
		initBoard();
	}

	/*-public TicTacToeBoard(int[][] boardState) {
		this.boardState = boardState;
	}*/

	@Test
	public void checkToDisplayTheBoard() {
		initBoard();
		System.out.println(this.toString());

	}

	public void initBoard() {
		this.boardState = new int[3][3];
		IntStream.range(0, 3).forEach(i -> IntStream.range(0, 3).forEach(j -> {
			boardState[i][j] = 0;
		}));

	}

	public boolean isWinBy(Player p) {
		int playerWinValue = p.getId() * 3;
		int row1 = boardState[0][0] + boardState[0][1] + boardState[0][2];
		int row2 = boardState[1][0] + boardState[1][1] + boardState[1][2];
		int row3 = boardState[2][0] + boardState[2][1] + boardState[2][2];
		int col1 = boardState[0][0] + boardState[1][0] + boardState[2][0];
		int col2 = boardState[0][1] + boardState[1][1] + boardState[2][1];
		int col3 = boardState[0][2] + boardState[1][2] + boardState[2][2];
		int diag1 = boardState[0][0] + boardState[1][1] + boardState[2][2];
		int diag2 = boardState[0][2] + boardState[1][1] + boardState[2][0];

		return row1 == playerWinValue || row2 == playerWinValue || row3 == playerWinValue || col1 == playerWinValue || col2 == playerWinValue || col3 == playerWinValue
				|| diag1 == playerWinValue || diag2 == playerWinValue;
	}

	@Override
	public String toString() {
		final StringBuilder board = new StringBuilder();
		IntStream.range(0, 3).forEach(i -> {
			IntStream.range(0, 3).forEach(j -> {
				switch (boardState[i][j]) {
				case -1:
					board.append("O");
					break;
				case 1:
					board.append("X");
					break;
				case 0:
					board.append(" ");
					break;
				}
				if (j < 2) {
					board.append("|");
				}
			});
			if (i < 2) {
				board.append("\n");
			}
		});
		return board.toString();
	}

	public int[][] getBoardState() {
		return boardState;
	}

	public void setBoardState(int[][] boardState) {
		this.boardState = boardState;
	}

}
