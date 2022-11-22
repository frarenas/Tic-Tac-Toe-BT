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
    private JPanel pnlPrincipal;
    private JButton btnCasillero1;
    private JButton btnCasillero4;
    private JButton btnCasillero7;
    private JButton btnCasillero2;
    private JButton btnCasillero5;
    private JButton btnCasillero8;
    private JButton btnCasillero3;
    private JButton btnCasillero6;
    private JButton btnCasillero9;
    private JPanel pnlTablero;
    private JPanel pnlControl;
    private JComboBox<PlayerType> cbJugador2;
    private JComboBox<PlayerType> cbJugador1;
    private JButton btnEmpezar;
    private JLabel lblJugador1;
    private JLabel lblJugador2;
    private JLabel lblEstado;

    public GameUI() {
        ticTacToe = new TicTacToe();
        backTrackingPlayer = new BackTrackingPlayer(ticTacToe);
        rootFrame = new JFrame("Tateti BT");

        rootFrame.setSize(620, 460);
        rootFrame.setLayout(null);
        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setLocationRelativeTo(null);
        rootFrame.setResizable(false);
        rootFrame.setContentPane(pnlPrincipal);

        btnCasillero1.addActionListener(this);
        btnCasillero2.addActionListener(this);
        btnCasillero3.addActionListener(this);
        btnCasillero4.addActionListener(this);
        btnCasillero5.addActionListener(this);
        btnCasillero6.addActionListener(this);
        btnCasillero7.addActionListener(this);
        btnCasillero8.addActionListener(this);
        btnCasillero9.addActionListener(this);
        btnEmpezar.addActionListener(this);

        pnlTablero.setVisible(false);

        rootFrame.setVisible(true);

        cbJugador1.addItem(PlayerType.HUMANO);
        cbJugador2.addItem(PlayerType.HUMANO);
        cbJugador1.addItem(PlayerType.COMPUTADORA);
        cbJugador2.addItem(PlayerType.COMPUTADORA);
        cbJugador1.addItem(PlayerType.COMPUTADORABT);
        cbJugador2.addItem(PlayerType.COMPUTADORABT);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button == btnEmpezar) {
            restart();
        } else {
            play(button);
        }
    }

    private void restart() {
        btnCasillero1.setText("");
        btnCasillero2.setText("");
        btnCasillero3.setText("");
        btnCasillero4.setText("");
        btnCasillero5.setText("");
        btnCasillero6.setText("");
        btnCasillero7.setText("");
        btnCasillero8.setText("");
        btnCasillero9.setText("");

        btnCasillero1.setEnabled(true);
        btnCasillero2.setEnabled(true);
        btnCasillero3.setEnabled(true);
        btnCasillero4.setEnabled(true);
        btnCasillero5.setEnabled(true);
        btnCasillero6.setEnabled(true);
        btnCasillero7.setEnabled(true);
        btnCasillero8.setEnabled(true);
        btnCasillero9.setEnabled(true);

        btnEmpezar.setText("Reiniciar");

        currentPlayer = true;
        player1 = (PlayerType) cbJugador1.getSelectedItem();
        player2 = (PlayerType) cbJugador2.getSelectedItem();
        board = new String[]{"", "", "", "", "", "", "", "", ""};
        pnlTablero.setVisible(true);
        setNextMove();
    }

    private void setNextMove() {
        final String currentPlayerName = currentPlayer ? "Jugador 1" : "Jugador 2";
        lblEstado.setText("Juega el " + currentPlayerName);
        pnlPrincipal.setEnabled(false);
        if ((currentPlayer && player1 == PlayerType.HUMANO) || (!currentPlayer && player2 == PlayerType.HUMANO)) {
            pnlPrincipal.setEnabled(false);
        } else {
            computerPlay();
        }
    }

    private void computerPlay() {
        final int selectedSquare;

        if ((currentPlayer && player1 == PlayerType.COMPUTADORA) || (!currentPlayer && player2 == PlayerType.COMPUTADORA)) {
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
                btnCasillero1.doClick();
                break;
            case 1:
                btnCasillero2.doClick();
                break;
            case 2:
                btnCasillero3.doClick();
                break;
            case 3:
                btnCasillero4.doClick();
                break;
            case 4:
                btnCasillero5.doClick();
                break;
            case 5:
                btnCasillero6.doClick();
                break;
            case 6:
                btnCasillero7.doClick();
                break;
            case 7:
                btnCasillero8.doClick();
                break;
            case 8:
                btnCasillero9.doClick();
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
        lblEstado.setText(message);

        btnCasillero1.setEnabled(false);
        btnCasillero2.setEnabled(false);
        btnCasillero3.setEnabled(false);
        btnCasillero4.setEnabled(false);
        btnCasillero5.setEnabled(false);
        btnCasillero6.setEnabled(false);
        btnCasillero7.setEnabled(false);
        btnCasillero8.setEnabled(false);
        btnCasillero9.setEnabled(false);
    }

    private String getMark() {
        return currentPlayer ? Mark.MARK1.toString() : Mark.MARK2.toString();
    }
}
