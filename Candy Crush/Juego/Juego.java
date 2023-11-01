package Juego;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.*;

import GUI.GUI;
import GUI.PanelMenu;
import Nivel.Nivel;
import Tablero.Tablero;
import utils.Utils;
import Entidad.Color;
import GUI.EntidadGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Juego{
    //Atributos
    protected GUI miGUI;
    protected Tablero miTablero;
    protected Nivel miNivel;
    protected GeneradorDeNivel miGenerador;
    protected BaseDeDatos miBaseDeDatos;

    //Constructor
    public Juego(){
        PanelMenu panelMenu = new PanelMenu(this);
    }

    //Metodos
    public void crear(){
        miBaseDeDatos = new BaseDeDatos();
        miNivel = new Nivel(this,1);
        miTablero = new Tablero(this, miBaseDeDatos);
        miGUI = new GUI(this);
        miGenerador = new GeneradorDeNivel();
        regenerar(1);
    }

    public void notificarDestruccion(Color color) {
        miNivel.restarCaramelo(color);
    }

    public void notificarDestruccionGelatina() {
        miNivel.restarGelatina();
    }

    public void notificarDestruccionEnvuelto() {
        miNivel.restarEnvuelto();
    }

    public void notificarDestruccionGlaseado() {
        miNivel.restarGlaseado();
    }

    public void regenerar(int nivel){
        //consultarSkin();
        miGUI.limpiarPanel();
        miGenerador.parseLvl(nivel,miTablero,miNivel);
        miGUI.notificarMovimiento();
    }

    public boolean moverCursor(int x,int y){
        return miTablero.setPosJugadorX(x) & miTablero.setPosJugadorY(y);
    }

    public void swap(int x, int y) {
        miTablero.swap(x, y);
        miNivel.restarMov();
    }

    public GUI getMiGUI() {
        return miGUI;
    }

    public void asociar_entidad_grafica(EntidadGrafica entidadGrafica) {
        miGUI.insertarEntidadGrafica(entidadGrafica);
    }

    public Nivel getNivel(){
        return miNivel;
    }

    public int NivelActual(){
        return miNivel.getNivel();
    }
    
    public int getPuntaje(){
        return miBaseDeDatos.getPuntajeActual();
    }
    
    public void animacionesTerminadas(){
        if(miNivel.objetivosTerminados()){
            miNivel.setNivel(NivelActual()+1);
            if(miNivel.getNivel() != 6){
                regenerar(NivelActual());
                ImageIcon icono = new ImageIcon("Candy Crush/Imagenes/"+Utils.skin+"/Extras/NivelGanado.gif");
                JOptionPane.showMessageDialog(null, "Pasaste de nivel", "Felicidades", JOptionPane.PLAIN_MESSAGE, icono);
            }
            else{
                ImageIcon icono = new ImageIcon("Candy Crush/Imagenes/"+Utils.skin+"/Extras/JuegoGanado.gif");
                JOptionPane.showMessageDialog(null, "Finalizaste el juego", "Felicidades", JOptionPane.PLAIN_MESSAGE, icono);
                
                JFrame frame = new JFrame("Formulario de Nombre");

                // Crear un JTextField
                JTextField textField = new JTextField(20); // 20 es el ancho del campo de texto
        
                // Crear un botón
                JButton button = new JButton("Aceptar");
        
                // Crear un contenedor
                JPanel panel = new JPanel();
                panel.add(new JLabel("Nombre: "));
                panel.add(textField);
                panel.add(button);
        
                // Agregar un ActionListener al botón para capturar el nombre ingresado y cerrar la GUI
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombre = textField.getText();
                        JOptionPane.showMessageDialog(frame, "Hola, " + nombre + " tu puntaje final fue de ");
                        frame.dispose(); // Cierra el marco actual
        
                miGUI.setVisible(false);
                miGUI.dispatchEvent(new WindowEvent(miGUI, WindowEvent.WINDOW_CLOSING));
                    }
                });
        
                // Configurar el marco
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);
            }
        } else {
            boolean stop = false;
            for (int i = 0; i < miTablero.getDimension() && !stop; i++) {
                for (int j = 0; j < miTablero.getDimension() && !stop; j++) {
                    stop = miTablero.chequeoMovimiento(i,j);
                }
            }
        }
    }

    public static void main(String [] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Juego();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
