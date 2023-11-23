package Juego;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        miBaseDeDatos = new BaseDeDatos();
        try{
            FileInputStream fileInputStream = new FileInputStream("puntajes.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            miBaseDeDatos = (BaseDeDatos) objectInputStream.readObject();
            objectInputStream.close(); 
        }catch(ClassNotFoundException e){
        e.printStackTrace();
        }catch(FileNotFoundException e){
        }catch(IOException e){
        e.printStackTrace();
        }
        new PanelMenu(this);
    }

    //Metodos
    public void crear(){
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
    public void notificarDestruccionBomba() {
        miNivel.restarBomba();
    }
    public void notificarDestruccionCruz() {
        miNivel.restarCruz();
    }

    public void aumentarPuntaje(int puntaje){
        miBaseDeDatos.aumentarPuntaje(puntaje);
    }

    public void regenerar(int nivel){
        miGUI.limpiarPanel();
        miGenerador.parseLvl(nivel,miTablero,miNivel);
        miGUI.notificarMovimiento();
    }

    public void guardarDatos(){
        miBaseDeDatos.reiniciarPuntos();
        try{
        FileOutputStream fileOutputStream = new FileOutputStream("puntajes.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(miBaseDeDatos);
        objectOutputStream.flush();
        objectOutputStream.close();
        }catch(IOException e){e.printStackTrace();}
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

    public void asociarEntidadGrafica(EntidadGrafica entidadGrafica) {
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
            miBaseDeDatos.mezclarPuntos();
            miNivel.setNivel(NivelActual()+1);
            if(miNivel.getNivel() != 6){
                regenerar(NivelActual());
                miGUI.NivelGanado();
            }
            else{
                miBaseDeDatos.guardarPuntaje();
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
    public void terminarNivel(){
        miNivel.terminarNivel();
    }

    public void setNombre(String name){
        miBaseDeDatos.setNombre(name);
    }

    public void bajarPuntaje(){
        miBaseDeDatos.setPuntaje(0);
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

    public void abrirPanelScore(){
        miGUI.abrirPanelScore(miBaseDeDatos.getLista());
    }
}
