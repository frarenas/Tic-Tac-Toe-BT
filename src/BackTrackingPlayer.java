import java.util.Objects;

public class BackTrackingPlayer {
    private final TicTacToe ticTacToe;

    public BackTrackingPlayer (TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    public int findBestSquare(String[] board, boolean currentPlayer)
    {
        //String[] board = Arrays.copyOf(board, board.length);

        if (ticTacToe.getGameState(board) == GameState.DRAW) {
            return 0;
        } else if (ticTacToe.getGameState(board) == GameState.PLAYER1WIN) {
            return -9;
        } else if (ticTacToe.getGameState(board) == GameState.PLAYER2WIN){
            return 9;
        }

        int bestVal;
        int bestSquare = 0;


        if (!currentPlayer)
        {
            bestVal = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (!Objects.equals(board[i], ""))
                    continue;
                board[i] = Mark.MARK2.toString();
                int value = findBestSquare(board, true);
                if (value > bestVal) {
                    bestVal = value;
                    bestSquare = i;
                }
                board[i] = "";
            }
        } else {
            bestVal = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (!Objects.equals(board[i], ""))
                    continue;
                board[i] = Mark.MARK1.toString();
                int value = findBestSquare(board, false);
                if (value < bestVal) {
                    bestVal = value;
                    bestSquare = i;
                }
                board[i] = "";
            }
        }
        return bestSquare;
    }
}
