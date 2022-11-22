import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI implements ActionListener {

    private String[] board = {"","","","","","","","",""};
    private boolean currentPlayer;
    private PlayerType player1;
    private PlayerType player2;

    private final TicTacToe ticTacToe;

    private BackTrackingPlayer backTrackingPlayer;

    public static void main(String[] args) {
        new GameUI();

    }

    JFrame rootFrame;
    private JPanel pnlMain;
    private JButton btnSquare1;
    private JButton btnSquare4;
    private JButton btnSquare7;
    private JButton btnSquare2;
    private JButton btnSquare5;
    private JButton btnSquare8;
    private JButton btnSquare3;
    private JButton btnSquare6;
    private JButton btnSquare9;
    private JPanel pnlBoard;
    private JPanel pnlControl;
    private JComboBox<PlayerType> cbPlayer2;
    private JComboBox<PlayerType> cbPlayer1;
    private JButton btnStart;
    private JLabel lblPlayer1;
    private JLabel lblPlayer2;
    private JLabel lblState;

    public GameUI() {
        ticTacToe = new TicTacToe();
        backTrackingPlayer = new BackTrackingPlayer(ticTacToe);
        rootFrame = new JFrame("Tateti BT");

        rootFrame.setSize(620, 460);
        rootFrame.setLayout(null);
        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setLocationRelativeTo(null);
        rootFrame.setResizable(false);
        rootFrame.setContentPane(pnlMain);

        btnSquare1.addActionListener(this);
        btnSquare2.addActionListener(this);
        btnSquare3.addActionListener(this);
        btnSquare4.addActionListener(this);
        btnSquare5.addActionListener(this);
        btnSquare6.addActionListener(this);
        btnSquare7.addActionListener(this);
        btnSquare8.addActionListener(this);
        btnSquare9.addActionListener(this);
        btnStart.addActionListener(this);

        pnlBoard.setVisible(false);

        rootFrame.setVisible(true);

        cbPlayer1.addItem(PlayerType.HUMANO);
        cbPlayer2.addItem(PlayerType.HUMANO);
        cbPlayer1.addItem(PlayerType.AZAR);
        cbPlayer2.addItem(PlayerType.AZAR);
        cbPlayer1.addItem(PlayerType.BACKTRACKING);
        cbPlayer2.addItem(PlayerType.BACKTRACKING);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button == btnStart) {
            restart();
        } else {
            play(button);
        }
    }

    private void restart() {
        btnSquare1.setText("");
        btnSquare2.setText("");
        btnSquare3.setText("");
        btnSquare4.setText("");
        btnSquare5.setText("");
        btnSquare6.setText("");
        btnSquare7.setText("");
        btnSquare8.setText("");
        btnSquare9.setText("");

        btnSquare1.setEnabled(true);
        btnSquare2.setEnabled(true);
        btnSquare3.setEnabled(true);
        btnSquare4.setEnabled(true);
        btnSquare5.setEnabled(true);
        btnSquare6.setEnabled(true);
        btnSquare7.setEnabled(true);
        btnSquare8.setEnabled(true);
        btnSquare9.setEnabled(true);

        btnStart.setText("Reiniciar");

        currentPlayer = true;
        player1 = (PlayerType) cbPlayer1.getSelectedItem();
        player2 = (PlayerType) cbPlayer2.getSelectedItem();
        board = new String[]{"", "", "", "", "", "", "", "", ""};
        pnlBoard.setVisible(true);
        setNextMove();
    }

    private void setNextMove() {
        final String currentPlayerName = currentPlayer ? "Jugador 1" : "Jugador 2";
        lblState.setText("Juega el " + currentPlayerName);
        pnlMain.setEnabled(false);
        if ((currentPlayer && player1 == PlayerType.HUMANO) || (!currentPlayer && player2 == PlayerType.HUMANO)) {
            pnlMain.setEnabled(false);
        } else {
            computerPlay();
        }
    }

    private void computerPlay() {
        final int selectedSquare;

        if ((currentPlayer && player1 == PlayerType.AZAR) || (!currentPlayer && player2 == PlayerType.AZAR)) {
            selectedSquare = ticTacToe.getRandomAvailableSquare(board);
        } else {
            if(ticTacToe.isBoardEmpty(board)){
                selectedSquare = ticTacToe.getRandomAvailableSquare(board);
            } else {
                selectedSquare = backTrackingPlayer.findBestSquare(board, currentPlayer);
            }
        }

        switch (selectedSquare) {
            case 0:
                btnSquare1.doClick();
                break;
            case 1:
                btnSquare2.doClick();
                break;
            case 2:
                btnSquare3.doClick();
                break;
            case 3:
                btnSquare4.doClick();
                break;
            case 4:
                btnSquare5.doClick();
                break;
            case 5:
                btnSquare6.doClick();
                break;
            case 6:
                btnSquare7.doClick();
                break;
            case 7:
                btnSquare8.doClick();
                break;
            case 8:
                btnSquare9.doClick();
                break;
        }
    }

    private void play(final JButton button) {
        final String mark = getMark();
        button.setText(mark);
        button.setEnabled(false);

        int position = Integer.parseInt(button.getName());
        board[position] = mark;

        checkGameState();
    }

    private void checkGameState() {
        GameState gameState = ticTacToe.getGameState(board);
        switch (gameState) {
            case PLAYER1WIN:
                finishGame("Gana el jugador 1");
                break;
            case PLAYER2WIN:
                finishGame("Gana el jugador 2");
                break;
            case DRAW:
                finishGame("Empate");
                break;
            case INPROGRESS:
                currentPlayer = !currentPlayer;
                setNextMove();
                break;
        }
    }

    private void finishGame(final String message) {
        JOptionPane.showMessageDialog(null, message);
        lblState.setText(message);

        btnSquare1.setEnabled(false);
        btnSquare2.setEnabled(false);
        btnSquare3.setEnabled(false);
        btnSquare4.setEnabled(false);
        btnSquare5.setEnabled(false);
        btnSquare6.setEnabled(false);
        btnSquare7.setEnabled(false);
        btnSquare8.setEnabled(false);
        btnSquare9.setEnabled(false);
    }

    private String getMark() {
        return currentPlayer ? Mark.MARK1.toString() : Mark.MARK2.toString();
    }
}
