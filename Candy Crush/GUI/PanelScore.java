package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import Juego.Juego;

public class PanelScore extends JPanel{
    //Atributos
    protected Juego miJuego;
    protected String nombreJugador;

    //Constructor
    public PanelScore(Juego juego){
        miJuego = juego;
        JFrame frame = new JFrame("PanelScore"); // Crea un marco (ventana)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setResizable(false);
        frame.setSize(500, 400);

        JPanel panel = new JPanel(); // Crea un panel
        panel.setBackground(Color.black);
        panel.setOpaque(true);
        panel.setLayout(new BorderLayout());

        // Crear un JLabel
        JLabel titulo = new JLabel("Records ");
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); 
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setVerticalAlignment(JLabel.NORTH);
        panel.add(titulo);

        frame.add(panel);
        frame.setVisible(true);
    }
}
