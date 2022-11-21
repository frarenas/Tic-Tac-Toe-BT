import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Tablero implements ActionListener {

    private String[] tablero = {"","","","","","","","",""};
    private boolean jugadorActual;
    private Jugador jugador1;
    private Jugador jugador2;

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
        cbJugador1.addItem(Jugador.COMPUTADORA);
        cbJugador2.addItem(Jugador.COMPUTADORA);
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

        jugadorActual = true;
        jugador1 = (Jugador) cbJugador1.getSelectedItem();
        jugador2 = (Jugador) cbJugador2.getSelectedItem();
        tablero = new String[]{"", "", "", "", "", "", "", "", ""};
        pnlTablero.setVisible(true);
        prepararProximaJugada();
    }

    private void prepararProximaJugada() {
        pnlPrincipal.setEnabled(false);
        if ((jugadorActual && jugador1 == Jugador.HUMANO) || (!jugadorActual && jugador2 == Jugador.HUMANO)) {
            pnlPrincipal.setEnabled(false);
        } else {
            JugarComputadora();
        }
    }

    private void JugarComputadora() {
        try {
            TimeUnit.SECONDS.sleep(0);
        } catch (Exception ignored) {

        }


        final int[] disponibles = IntStream.range(0, tablero.length)
                .filter(i -> Objects.equals(tablero[i], ""))
                .toArray();

        final int elegido = new Random().nextInt(disponibles.length);
        final int posicionElegida = disponibles[elegido];

        switch (posicionElegida) {
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

    private void jugar(final JButton button) {
        final String marca = jugadorActual ? Marca.MARCA1.toString() : Marca.MARCA2.toString();
        button.setText(marca);
        button.setEnabled(false);

        int posicion = Integer.parseInt(button.getName());
        tablero[posicion] = marca;

        revisarEstado();
    }

    private void revisarEstado() {
        boolean juegoTerminado = false;

        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = tablero[0] + tablero[1] + tablero[2];
                    break;
                case 1:
                    line = tablero[3] + tablero[4] + tablero[5];
                    break;
                case 2:
                    line = tablero[6] + tablero[7] + tablero[8];
                    break;
                case 3:
                    line = tablero[0] + tablero[3] + tablero[6];
                    break;
                case 4:
                    line = tablero[1] + tablero[4] + tablero[7];
                    break;
                case 5:
                    line = tablero[2] + tablero[5] + tablero[8];
                    break;
                case 6:
                    line = tablero[0] + tablero[4] + tablero[8];
                    break;
                case 7:
                    line = tablero[2] + tablero[4] + tablero[6];
                    break;
            }
            //For X winner
            if (line.equals(Marca.MARCA1.toString() + Marca.MARCA1 + Marca.MARCA1)) {
                finalizarJuego("Gana el jugador 1");
                juegoTerminado = true;
            }

            // For O winner
            else if (line.equals(Marca.MARCA2.toString() + Marca.MARCA2 + Marca.MARCA2)) {
                finalizarJuego("Gana el jugador 2");
                juegoTerminado = true;
            }
        }

        if (!juegoTerminado && !Arrays.asList(tablero).contains("")) {
            finalizarJuego("Empate");
            juegoTerminado = true;
        }

        if (!juegoTerminado) {
            jugadorActual = !jugadorActual;
            prepararProximaJugada();
        }
    }

    private void finalizarJuego(final String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);

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
}
