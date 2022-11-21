import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class TicTacToe {
    public GameState getGameState(final String[] board) {
        GameState gameState = GameState.INPROGRESS;
        boolean gameFinished = false;

        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            if (line.equals(Mark.MARK1.toString() + Mark.MARK1 + Mark.MARK1)) {
                gameFinished = true;
                gameState = GameState.PLAYER1WIN;
                break;
            }

            else if (line.equals(Mark.MARK2.toString() + Mark.MARK2 + Mark.MARK2)) {
                gameFinished = true;
                gameState = GameState.PLAYER2WIN;
                break;
            }
        }

        if (!gameFinished && !Arrays.asList(board).contains("")) {
            //gameFinished = true;
            gameState = GameState.DRAW;
        }

        return gameState;
    }

    public int[] getAvailableSquares(final String[] board) {
        return IntStream.range(0, board.length)
                .filter(i -> Objects.equals(board[i], ""))
                .toArray();
    }

    public int getRandomAvailableSquare(final String[] board) {
        final int[] availableSquares = getAvailableSquares(board);
        final int randomSquare = new Random().nextInt(availableSquares.length);
        return availableSquares[randomSquare];
    }

    public boolean isBoardEmpty(final String[] board) {
        final int[] availableSquares = getAvailableSquares(board);
        return availableSquares.length == 9;
    }
}
