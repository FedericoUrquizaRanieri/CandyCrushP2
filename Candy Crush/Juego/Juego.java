package Juego;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.*;

import GUI.GUI;
import Nivel.Nivel;
import Tablero.Tablero;
import Entidad.Color;
import GUI.EntidadGrafica;

public class Juego{
    //Atributos
    protected GUI miGUI;
    protected Tablero miTablero;
    protected Nivel miNivel;
    protected GeneradorDeNivel miGenerador;

    //Constructor
    public Juego(){
        miNivel = new Nivel(this,1);
        miTablero = new Tablero(this);
        miGUI = new GUI(this);
        miGenerador = new GeneradorDeNivel();
        regenerar(1);
    }

    //Metodos
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

    public void animacionesTerminadas(){
        if(miNivel.objetivosTerminados()){
            miNivel.setNivel(NivelActual()+1);
            if(miNivel.getNivel() != 6){
                regenerar(NivelActual());
                ImageIcon icono = new ImageIcon("Candy Crush/Imagenes/Extras/creeper.gif");
                JOptionPane.showMessageDialog(null, "Pasaste de nivel", "Felicidades", JOptionPane.PLAIN_MESSAGE, icono);
            }
            else{
                ImageIcon icono = new ImageIcon("Candy Crush/Imagenes/Extras/Rana.gif");
                JOptionPane.showMessageDialog(null, "Finalizaste el juego", "Felicidades", JOptionPane.PLAIN_MESSAGE, icono);
                miGUI.setVisible(false);
                miGUI.dispatchEvent(new WindowEvent(miGUI, WindowEvent.WINDOW_CLOSING));
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
