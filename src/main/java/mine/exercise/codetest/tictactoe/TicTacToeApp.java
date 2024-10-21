package mine.exercise.codetest.tictactoe;

import java.util.Arrays;
import java.util.Random;

public class TicTacToeApp {
    Player X = new Player(1, "X");
    Player O = new Player(-1, "O");
    private TicTacToeBoard board;
    private Player[] players;
    private boolean canMove = true;

    public TicTacToeApp(TicTacToeBoard board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public void start() {
        Player player = players[new Random().nextInt(2)];
        Random randomX = new Random();
        Random randomY = new Random();
        do {
            if (makeNextPossibleMove(player, randomX.nextInt(3), randomY.nextInt(3))) {
                checkStatus();
                player = switchPlayer(player);
            }
        } while (canMove);
    }

    public boolean makeNextPossibleMove(Player player, int x, int y) {
        if (x < 0 | x > 2 || y < 0 || y > 2) {
            return false;
        }
        int currCellVal = board.getBoardState()[x][y];
        if (currCellVal != 0) {
            return false;
        }
        board.getBoardState()[x][y] = player.getId();
        return true;
    }

    public void displayBoard() {
        System.out.println(board.toString());
    }

    private Player switchPlayer(Player currentPlayer) {
        return Arrays.stream(players).filter(p -> p != currentPlayer).findAny().get();
    }

    private void checkStatus() {
        if (board.isWinBy(players[0])) {
            System.out.println(players[0].getName() + " Won!");
            canMove = false;
        } else if (board.isWinBy(players[1])) {
            System.out.println(players[1].getName() + " Won!");
            canMove = false;
        } else {
            if (!canMove) {
                System.out.println("No more moves. Tie!");
            }
        }
    }
}
