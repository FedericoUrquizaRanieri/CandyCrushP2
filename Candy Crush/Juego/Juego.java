package Juego;
import java.awt.EventQueue;

import GUI.GUI;
import GUI.PanelMenu;
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
                miGUI.NivelGanado();
            }
            else{
                miGUI.juegoGanado();
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
    public void llamarJuegoPerdido(){
        miGUI.juegoPerdido();
    }
    public void llamarNivelPerdido(){
        miGUI.nivelPerdido();
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
