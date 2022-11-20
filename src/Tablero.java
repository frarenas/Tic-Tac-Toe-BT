import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tablero implements ActionListener {

    private boolean jugador;

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
    private JComboBox<Jugador> cbJugador2;
    private JComboBox<Jugador> cbJugador1;
    private JButton btnEmpezar;
    private JLabel lblJugador1;
    private JLabel lblJugador2;

    public Tablero() {
        rootFrame = new JFrame("Tateti BT");

        rootFrame.setSize(800, 600);
        rootFrame.setLayout(null);
        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setLocationRelativeTo(null);
        //rootFrame.add(pnlPrincipal);
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

        cbJugador1.addItem(Jugador.HUMANO);
        cbJugador2.addItem(Jugador.HUMANO);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button == btnEmpezar) {
            reiniciar();
        } else {
            jugar(button);
        }
    }

    private void reiniciar() {
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

        jugador = true;
        pnlTablero.setVisible(true);
    }

    private void jugar(final JButton button) {
        button.setText(jugador ? "X" : "O");
        button.setEnabled(false);

        jugador = !jugador;
    }
}
