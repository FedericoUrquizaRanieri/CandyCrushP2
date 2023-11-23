package Entidad;

import GUI.EntidadGrafica;
import Juego.Juego;
import Tablero.Tablero;
import utils.Utils;

public class Caramelo extends Entidad{
    //Constructor
    public Caramelo(int f, int c, Color color, String path){
        super(f,c);
        imagePath = Juego.class.getResource(path+"/Caramelos/"+ color.toString().toLowerCase()+".png");
        this.color = color;
    }

    //Metodos
    public void setEntidadGrafica(EntidadGrafica eg){
        this.eg = eg;
    }

    public Color getColor() {
        return color;
    }

    public int getPuntaje() {
        return Utils.getPuntaje(this.color);
    }

    public void destruirse(Tablero t){
        if(!destruida){
            destruida = true;
            t.notificarDestruccion(this.color);
            eg.destruirse();
            t.aumentarPuntaje(this.getPuntaje());
        }
    }

    public boolean seDestruyeCon(Entidad entidad) {
        return entidad.seDestruyen(this);
    }
    public boolean seDestruyen(Caramelo caramelo) {
        return false;
    }
    public boolean seDestruyen(RalladoH ralladoH) {
        return false;
    }
    public boolean seDestruyen(RalladoV ralladoV) {
        return false;
    }
    public boolean seDestruyen(Envuelto envuelto) {
        return false;
    }
    public boolean seDestruyen(Cruz cruz){
        return cruz.getColor() == color;
    }

    public void cambiarPosicionCon(Entidad entidad, Tablero tablero) {
        entidad.cambiarPosicion(this, tablero);
    }
    public void cambiarPosicion(Caramelo caramelo, Tablero tablero) {
        int fila = caramelo.getFila();
        int columna = caramelo.getColumna();
        caramelo.setFila(this.fila);
        caramelo.setColumna(this.columna);
        this.fila = fila;
        this.columna = columna;

        tablero.getGrilla()[caramelo.getFila()][caramelo.getColumna()] = caramelo;
        tablero.getGrilla()[this.fila][this.columna] = this;

        eg.notificarCambioPosicion(caramelo.getEntidadGrafica());
    }
    public void cambiarPosicion(Gelatina gelatina, Tablero tablero) {
        gelatina.cambiarPosicion(this,tablero);
    }

    public boolean esPosibleIntercambiar(Entidad e) {
        return e.puedeRecibir(this);
    };
    public boolean puedeRecibir(Caramelo c) {
        return true;
    }
    public boolean puedeRecibir(Glaseado g) {
        return false;
    }
    public boolean puedeRecibir(Envuelto p) {
        return true;
    }
    public boolean puedeRecibir(RalladoH rh) {
        return true;
    }
    public boolean puedeRecibir(RalladoV rv) {
        return true;
    }
    public boolean puedeRecibir(Cruz cruz) {
        return true;
    }

    public boolean match(Entidad entidad) {
        return entidad.matchWith(this);
    }
    public boolean matchWith(Caramelo caramelo) {
        return this.color == caramelo.getColor();
    }
    public boolean matchWith(RalladoH ralladoH) {
        return this.color == ralladoH.getColor();
    }
    public boolean matchWith(RalladoV ralladoV) {
        return this.color == ralladoV.getColor();
    }
    public boolean matchWith(Envuelto envuelto) {
        return this.color == envuelto.getColor();
    }
    public boolean matchWith(Glaseado glaseado) {
        return false;
    }
    public boolean matchWith(Cruz cruz) {
        return this.color == cruz.getColor();
    }

    public void caer(int f, int c, Tablero t){
        cambiarPosicion(f, c);
        t.getGrilla()[f][c].recibir(f, c, this, t);
    }

    public void recibir(int f, int c, Caramelo e, Tablero t){
        t.getGrilla()[f][c] = e;
    }

    public void recibir(int f, int c, Gelatina g, Tablero t){
        t.getGrilla()[f][c] = g.getCaramelo();
    }

    public void recibir(int f, int c, Glaseado gla, Tablero t){
        t.getGrilla()[f][c] = gla;
    }
}