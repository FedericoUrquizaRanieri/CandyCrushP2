package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Juego.Juego;
import utils.Utils;

public class PanelMenu extends JPanel{
    Juego miJuego;
    protected JLabel labelCandy;
    protected JLabel labelMine;

    public PanelMenu(Juego juego){
        miJuego = juego;
        JFrame frame = new JFrame("PanelMenu"); // Crea un marco (ventana)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        frame.setSize(700, 700); // Establece el tamaño del marco
        frame.setResizable(false);

        JPanel panel = new JPanel(); // Crea un panel

        //Panel Candy
        labelCandy = new JLabel();
        labelCandy.setLayout(null);
        labelCandy.setPreferredSize(new Dimension(Utils.panelWidth()/2, Utils.panelHeight()));
        labelCandy.setOpaque(false);
        ImageIcon fondoCandy = new ImageIcon("Candy Crush/Imagenes/Candy/Fondo2.png");
        labelCandy.setIcon(new ImageIcon(fondoCandy.getImage().getScaledInstance(350, Utils.panelHeight(), Image.SCALE_SMOOTH)));
        labelCandy.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e){  
                Utils.setSkin("Candy");
                juego.crear();
            }});
        JLabel nombre1 = new JLabel();
        nombre1.setText(String.valueOf("Candy"));
        nombre1.setBounds(100, 0, 200, 200);
        nombre1.setForeground(Color.WHITE);
        nombre1.setFont(new Font("TimesRoman", Font.BOLD, 30));
        labelCandy.add(nombre1);
        panel.add(labelCandy);
        labelCandy.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Panel Mine
        labelMine = new JLabel();
        labelMine.setLayout(null);
        labelMine.setPreferredSize(new Dimension(Utils.panelWidth()/2, Utils.panelHeight()));
        labelMine.setOpaque(false);
        ImageIcon fondoInfo = new ImageIcon("Candy Crush/Imagenes/Minecraft/Fondo2.png");
        labelMine.setIcon(new ImageIcon(fondoInfo.getImage().getScaledInstance(350, Utils.panelHeight(), Image.SCALE_SMOOTH)));
        labelMine.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e){  
                Utils.setSkin("Minecraft");
                juego.crear();
            }});
        JLabel nombre2 = new JLabel();
        nombre2.setText(String.valueOf("Minecraft"));
        nombre2.setBounds(100, 0, 200, 200);
        nombre2.setForeground(Color.WHITE);
        nombre2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        labelMine.add(nombre2);
        panel.add(labelMine);
        labelMine.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agrega el panel al marco
        frame.add(panel);

        // Hacer visible el marco
        frame.setVisible(true);
    }
}
