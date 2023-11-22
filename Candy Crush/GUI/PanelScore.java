package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Juego.Juego;
import Juego.Par;

public class PanelScore extends JPanel{
    //Atributos
    protected Juego miJuego;
    protected String nombreJugador;
    protected JFrame frame;
    protected JPanel panel;
    protected JLabel jugador1;
    protected JLabel jugador2;
    protected JLabel jugador3;
    protected JLabel jugador4;
    protected JLabel jugador5;
    protected JLabel[] labelJugadores;
    protected JLabel score1;
    protected JLabel score2;
    protected JLabel score3;
    protected JLabel score4;
    protected JLabel score5;
    protected JLabel[] labelScores;

    //Constructor
    public PanelScore(Juego juego){
        miJuego = juego;
        frame = new JFrame("PanelScore");
        frame.setResizable(false);
        frame.setSize(500, 400);

        jugador1 = new JLabel();
        jugador2 = new JLabel();
        jugador3 = new JLabel();
        jugador4 = new JLabel();
        jugador5 = new JLabel();

        score1 = new JLabel();
        score2 = new JLabel();
        score3 = new JLabel();
        score4 = new JLabel();
        score5 = new JLabel();

        labelJugadores = new JLabel[] { jugador1, jugador2, jugador3, jugador4, jugador5};
        labelScores = new JLabel[] { score1, score2, score3, score4, score5};

        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setOpaque(true);
        panel.setLayout(null);

        JLabel jugador = new JLabel();
        jugador.setText("SCORE");
        jugador.setFont(new Font("OCR A Extended", Font.HANGING_BASELINE, 25));
        jugador.setBounds(100, 0, 100, 100);
        jugador.setForeground(Color.ORANGE);
        panel.add(jugador);

        JLabel puntos = new JLabel();
        puntos.setText("NAME");
        puntos.setFont(new Font("OCR A Extended", Font.HANGING_BASELINE, 25));
        puntos.setBounds(320, 0, 100, 100);
        puntos.setForeground(Color.ORANGE);
        panel.add(puntos);

        frame.add(panel);
        frame.setVisible(false);
    }

    public void parsearScore(List<Par<String,Integer>> top){
        JLabel auxJugador;
        JLabel auxScore;
        int largo = 55 ;
        int cont = 0;
        for(Par<String,Integer> k: top){
            auxJugador = labelJugadores[cont];
            auxJugador.setText(k.getClave());
            auxJugador.setFont(new Font("OCR A Extended", Font.BOLD, 25));
            auxJugador.setBounds(320, largo, 100, 100);
            auxJugador.setForeground(Color.WHITE);

            auxScore = labelScores[cont];
            auxScore.setText(k.getValor().toString());
            auxScore.setFont(new Font("OCR A Extended", Font.BOLD, 25));
            auxScore.setBounds(100, largo, 100, 100);
            auxScore.setForeground(Color.WHITE);

            panel.add(auxJugador);
            panel.add(auxScore);

            largo += 50;
            cont++;
        }
        
    }
    public void setVisiblePanel(boolean activo){
        frame.setVisible(activo);
    }
}
