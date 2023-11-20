package Entidad;

import java.net.URL;

import GUI.EntidadGrafica;
import Tablero.Tablero;
import utils.Utils;

public abstract class Entidad implements Intercambiable, Destruible, Matcheable, Caida{
    protected EntidadGrafica eg;
    protected Color color;
    protected int fila;
    protected int columna;
    protected URL imagePath;
    protected boolean destruida;
    protected int puntaje;

    protected Entidad(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.destruida = false;
    }

    public void setEntidadGrafica(EntidadGrafica eg){
        this.eg = eg;
    }

    public EntidadGrafica getEntidadGrafica() {
        return eg;
    }

    public Color getColor() {
        return color;
    }
    public void chequeoDestruccion(Tablero tablero) {}
    public void cambiarPosicion(int fila, int columna) {
        eg.notificarCaida(Utils.labelPositionX(columna), Utils.labelPositionY(fila));
        this.fila = fila;
        this.columna = columna;
    }
    public boolean se_destruyen(Glaseado glaseado) {
        return false;
    }
    public URL getImage(){
        return imagePath;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public boolean estaDestruida() {
        return destruida;
    }
}