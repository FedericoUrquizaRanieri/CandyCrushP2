package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Juego.Juego;
import Juego.Par;
import Nivel.Nivel;
import utils.Utils;



public class GUI extends JFrame{
    //Atributos
    protected Juego juego;
    protected Panel panel;
    protected JLabel labelInfo;
    protected JLabel jugador;
    protected JLabel nivelActual;
    protected JLabel tiempo;
    protected JLabel vidas;
    protected JLabel objetivo;
    protected JLabel movimientos;
    protected JLabel palabraScore; 
    protected JLabel fotoObjetivo;
    protected JLabel fotoMovimiento;
    protected JLabel score;
    protected PanelScore miPanelScore;

    //Constructor
    public GUI(Juego juego) {

        Nivel n = juego.getNivel();
        this.juego = juego;
        miPanelScore = new PanelScore(juego);
        miPanelScore.setVisible(false);
        
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jugador = new JLabel();
        jugador.setOpaque(false);
        jugador.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED,8));
        jugador.setBounds(Utils.labelSpacing, Utils.labelSpacing, Utils.labelWidth, Utils.labelHeight);

        panel = new Panel(juego, jugador);
        panel.add(jugador);
  
        labelInfo = new JLabel();
        labelInfo.setLayout(null);
        labelInfo.setPreferredSize(new Dimension(Utils.panelWidth()/2, Utils.panelHeight()));
        labelInfo.setOpaque(false);
        ImageIcon fondoInfo = new ImageIcon(Juego.class.getResource(Utils.skin+"/Fondo2.png"));
        labelInfo.setIcon(new ImageIcon(fondoInfo.getImage().getScaledInstance(350, Utils.panelHeight(), Image.SCALE_SMOOTH)));
        
        nivelActual = new JLabel();
        nivelActual.setText(String.valueOf("Nivel  "+n.getNivel()));
        nivelActual.setBounds(10, 0, 90, 90);
        nivelActual.setForeground(Color.WHITE);
        nivelActual.setHorizontalAlignment(SwingConstants.CENTER);
        nivelActual.setFont(new Font("TimesRoman", Font.BOLD, 27));
        labelInfo.add(nivelActual);
      

        tiempo = new JLabel();
        tiempo.setText("00:00");
        tiempo.setBounds(70, 20, 180, 180);
        tiempo.setHorizontalAlignment(SwingConstants.CENTER);
        tiempo.setForeground(Color.WHITE);
        tiempo.setOpaque(false);
        tiempo.setFont(new Font("TimesRoman", Font.BOLD, 85));
        labelInfo.add(tiempo);


        ImageIcon imagenVida = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/Vidas.png"));
        JLabel fotoVidas = new JLabel();
        fotoVidas.setBounds(45, 185, 80, 80);
        fotoVidas.setIcon(new ImageIcon(imagenVida.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        labelInfo.add(fotoVidas);

        vidas = new JLabel();
        vidas.setText(String.valueOf(n.getVidas()));
        vidas.setBounds(150, 180, 80, 80);
        vidas.setHorizontalAlignment(SwingConstants.CENTER);
        vidas.setForeground(Color.WHITE);
        vidas.setOpaque(false);
        vidas.setFont(new Font("TimesRoman", Font.BOLD, 65));
        labelInfo.add(vidas);

        ImageIcon imagenCaramelo = new ImageIcon(Juego.class.getResource(Utils.skin+"/Caramelos/amarillo.png"));
        fotoObjetivo = new JLabel();
        fotoObjetivo.setBounds(45, 300, 80, 80);
        fotoObjetivo.setIcon(new ImageIcon(imagenCaramelo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        fotoObjetivo.setOpaque(false);
        labelInfo.add(fotoObjetivo);

        objetivo = new JLabel();
        objetivo.setText(String.valueOf(2));
        objetivo.setBounds(150, 295, 80, 80);
        objetivo.setHorizontalAlignment(SwingConstants.CENTER);
        objetivo.setForeground(Color.WHITE);
        objetivo.setOpaque(false);
        objetivo.setFont(new Font("TimesRoman", Font.BOLD, 65));
        labelInfo.add(objetivo);

        ImageIcon imagenMovimiento = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/Velocidad.png"));
        fotoMovimiento = new JLabel();
        fotoMovimiento.setBounds(45, 420, 80, 80);
        fotoMovimiento.setIcon(new ImageIcon(imagenMovimiento.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        fotoMovimiento.setOpaque(false);
        labelInfo.add(fotoMovimiento);

        movimientos = new JLabel();
        movimientos.setText("");
        movimientos.setBounds(150, 420, 80, 80);
        movimientos.setHorizontalAlignment(SwingConstants.CENTER);
        movimientos.setForeground(Color.white);
        movimientos.setOpaque(false);
        movimientos.setFont(new Font("TimesRoman", Font.BOLD, 65));
        labelInfo.add(movimientos);

        palabraScore = new JLabel();
        palabraScore.setText("Score: ");
        palabraScore.setBounds(15, 500, 120, 120);
        palabraScore.setHorizontalAlignment(SwingConstants.CENTER);
        palabraScore.setForeground(Color.white);
        palabraScore.setOpaque(false);
        palabraScore.setFont(new Font("TimesRoman", Font.BOLD, 40));
        labelInfo.add(palabraScore);

        score  = new JLabel();
        score.setText("");
        score.setBounds(70, 414, 300, 300);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setForeground(Color.white);
        score.setOpaque(false);
        score.setFont(new Font("TimesRoman", Font.BOLD, 45));
        labelInfo.add(score);

        getContentPane().add(labelInfo, BorderLayout.EAST);
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        getContentPane().add(new JLabel(new ImageIcon(Juego.class.getResource(Utils.skin+"/Fondo.png"))));
        setVisible(true);
    }

    //Metodos
    public void insertarEntidadGrafica(EntidadGrafica eg) {
        if(eg != null) {
            int eg_X = eg.getX();
            int eg_Y = eg.getY();
            eg.setLocation(eg_X, eg_Y); // Para la animacion poner donde va eg_Y el valor -50
            panel.add(eg);
        }
    }
    public Panel getPanel() {
        return panel;
    }
    public void notificarMovimiento(){
        Nivel miNivel = juego.getNivel();
        movimientos.setText(String.valueOf(miNivel.getMov()));
        vidas.setText(String.valueOf(miNivel.getVidas()));
        score.setText(String.valueOf(juego.getPuntaje()));
        nivelActual.setText(String.valueOf("Nivel "+miNivel.getNivel()));
        ImageIcon ico = new ImageIcon();
        if(miNivel.getObjetivoCaramelo()>0){
            ico = new ImageIcon(Juego.class.getResource(Utils.skin+"/Caramelos/"+ miNivel.getColorObjetivo().toString().toLowerCase()+".png"));
            objetivo.setText(String.valueOf(miNivel.getObjetivoCaramelo()));
        }
        else{
            if(miNivel.getObjetivoGelatina()>0){
                ico = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/Gelatina.png"));
                objetivo.setText(String.valueOf(miNivel.getObjetivoGelatina()));
            }
            else{
                if(miNivel.getObjetivoGlaseado()>0){
                    ico = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/Merengue.png"));
                    objetivo.setText(String.valueOf(miNivel.getObjetivoGlaseado()));
                }
                else{
                    if(miNivel.getObjetivoEnvuelto()>0){
                        ico = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/EnvueltoObjetivo.png"));
                        objetivo.setText(String.valueOf(miNivel.getObjetivoEnvuelto()));
                }
                    else{
                        if(miNivel.getObjetivoCruz()>0){
                        ico = new ImageIcon(Juego.class.getResource(Utils.skin+"/Cruz/ROSA.png"));
                        objetivo.setText(String.valueOf(miNivel.getObjetivoCruz()));
                        }
                        else{
                            ico = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/Bomba.png"));
                            objetivo.setText(String.valueOf(miNivel.getObjetivoBomba()));
                        }
                    }
                }
            }
        }
        Image img = ico.getImage();
        Image new_img = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        fotoObjetivo.setIcon(new ImageIcon(new_img));
    }
    public void limpiarPanel() {
        panel.limpiar();
    }
    public void notificarObjetivoCaramelo(){
        Nivel n = juego.getNivel();
        objetivo.setText(String.valueOf(n.getObjetivoCaramelo()));
    }
    public void notificarObjetivoGelatina(){
        Nivel n = juego.getNivel();
        objetivo.setText(String.valueOf(n.getObjetivoGelatina()));
    }
    public void notificarObjetivoEnvuelto(){
        Nivel n = juego.getNivel();
        objetivo.setText(String.valueOf(n.getObjetivoEnvuelto()));
    }
    public void notificarObjetivoGlaseado(){
        Nivel n = juego.getNivel();
        objetivo.setText(String.valueOf(n.getObjetivoGlaseado()));
    }
    public void notificarObjetivoCruz(){
        Nivel n = juego.getNivel();
        objetivo.setText(String.valueOf(n.getObjetivoCruz()));
    }
    public void notificarObjetivoBomba(){
        Nivel n = juego.getNivel();
        objetivo.setText(String.valueOf(n.getObjetivoBomba()));
    }
    public void notificarTiempo(int t){
        tiempo.setText(String.valueOf(t/60)+":"+String.valueOf(t%60));
        tiempo.setHorizontalTextPosition(SwingConstants.CENTER);
    }
    public void nivelPerdido(){
        ImageIcon icono = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/NivelPerdido.gif"));
        JOptionPane.showMessageDialog(null, "Perdiste el nivel", ":(", JOptionPane.PLAIN_MESSAGE, icono);
    }
    public void NivelGanado(){
        ImageIcon icono = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/NivelGanado.gif"));
        JOptionPane.showMessageDialog(null, "Pasaste de nivel", "Felicidades", JOptionPane.PLAIN_MESSAGE, icono);
    }
    public void juegoGanado(){
        ImageIcon icono = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/JuegoGanado.gif"));
        JOptionPane.showMessageDialog(null, "Finalizaste el juego", "Felicidades", JOptionPane.PLAIN_MESSAGE, icono);
        juego.guardarDatos();
        System.exit(0);
    }
    public void juegoPerdido(){
        ImageIcon icono = new ImageIcon(Juego.class.getResource(Utils.skin+"/Extras/JuegoPerdido.gif"));
        JOptionPane.showMessageDialog(null, "Perdiste el nivel", ":(", JOptionPane.PLAIN_MESSAGE, icono);
        System.exit(0);
    }
    public void abrirPanelScore(List<Par<String,Integer>> top) {
        miPanelScore.parsearScore(top);
        miPanelScore.setVisiblePanel(true);
    }
}
