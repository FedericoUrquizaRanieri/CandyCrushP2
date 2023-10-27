package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Juego.Juego;
import utils.Utils;

public class PanelMenu extends JPanel{
    Juego miJuego;
    protected JLabel labelCandy;
    protected JLabel labelMine;
    protected String nombreJugador;

    public PanelMenu(Juego juego){
        miJuego = juego;
        JFrame frame = new JFrame("PanelMenu"); // Crea un marco (ventana)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        frame.setResizable(false);

        JPanel panel = new JPanel(); // Crea un panel
        panel.setBackground(Color.BLACK);
        panel.setOpaque(true);

        //Casilla de texto donde el jugador coloca su nombre
        JTextField textField = new JTextField();
        textField.setBounds(185, 550, 300 ,40);
        frame.getContentPane().add(textField);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setBackground(Color.BLACK);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setToolTipText("Ingresar Nombre (MAX 3 caracteres)");
        textField.setForeground(Color.white);
        textField.setColumns(1);

        //Panel Candy Crush
        labelCandy = new JLabel();
        labelCandy.setLayout(null);
        labelCandy.setPreferredSize(new Dimension(Utils.panelWidth()/2, Utils.panelHeight()));
        labelCandy.setOpaque(false);
        ImageIcon fondoCandy = new ImageIcon("Candy Crush/Imagenes/Candy/Extras/BotonCandy.jpg");
        labelCandy.setIcon(new ImageIcon(fondoCandy.getImage().getScaledInstance(350, Utils.panelHeight(), Image.SCALE_SMOOTH)));
        labelCandy.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e){  
                nombreJugador = textField.getText();
                chequeoNombre();
                Utils.setSkin("Candy");
                juego.crear();
                frame.setVisible(false);
            }});
  
        panel.add(labelCandy);
        labelCandy.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Panel Minecraft
        labelMine = new JLabel();
        labelMine.setLayout(null);
        labelMine.setPreferredSize(new Dimension(Utils.panelWidth()/2, Utils.panelHeight()));
        labelMine.setOpaque(false);
        ImageIcon fondoInfo = new ImageIcon("Candy Crush/Imagenes/Minecraft/Extras/BotonMinecraft.png");
        labelMine.setIcon(new ImageIcon(fondoInfo.getImage().getScaledInstance(350, Utils.panelHeight(), Image.SCALE_SMOOTH)));
        labelMine.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e){  
                nombreJugador = textField.getText();
                chequeoNombre();
                Utils.setSkin("Minecraft");
                juego.crear();
                frame.setVisible(false);
            }});

        panel.add(labelMine);
        labelMine.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agrega el panel al marco
        frame.add(panel);

        // Hacer visible el marco
        frame.setVisible(true);

        frame.pack();
    }
     private void chequeoNombre(){
        if(nombreJugador.length()==0)
            nombreJugador = "AAA";
    }
}
