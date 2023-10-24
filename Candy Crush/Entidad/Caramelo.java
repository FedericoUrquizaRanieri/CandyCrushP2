package Entidad;

import GUI.EntidadGrafica;
import Tablero.Tablero;

public class Caramelo extends Entidad{

    public Caramelo(int f, int c, Color color){
        super(f,c,"Candy Crush/Imagenes/Caramelos/"+ color.toString().toLowerCase()+".png");
        this.color = color;
    }

    public void setEntidadGrafica(EntidadGrafica eg){
        this.eg = eg;
    }

    public Color getColor() {
        return color;
    }

    public void destruirse(Tablero t){
        if(t.getEntidad(fila-1,columna) != null){
            t.getEntidad(fila-1,columna).chequeoDestruccion(t);
        }
        if(t.getEntidad(fila+1,columna) != null){
            t.getEntidad(fila+1,columna).chequeoDestruccion(t);
        }
        if(t.getEntidad(fila,columna+1) != null){
            t.getEntidad(fila,columna+1).chequeoDestruccion(t);
        }
        if(t.getEntidad(fila,columna-1) != null){
            t.getEntidad(fila,columna-1).chequeoDestruccion(t);
        }
        if(!destruida){
            destruida = true;
            t.notificarDestruccion(this.color);
            eg.destruirse();
        }
    }

    public boolean se_destruye_con(Entidad entidad) {
        return entidad.se_destruyen(this);
    }
    public boolean se_destruyen(Caramelo caramelo) {
        return false;
    }
    public boolean se_destruyen(RalladoH ralladoH) {
        return false;
    }
    public boolean se_destruyen(RalladoV ralladoV) {
        return false;
    }
    public boolean se_destruyen(Envuelto envuelto) {
        return false;
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

    public boolean es_posible_intercambiar(Entidad e) {
        return e.puede_recibir(this);
    };
    public boolean puede_recibir(Caramelo c) {
        return true;
    }
    public boolean puede_recibir(Glaseado g) {
        return false;
    }
    public boolean puede_recibir(Envuelto p) {
        return true;
    }
    public boolean puede_recibir(RalladoH rh) {
        return true;
    }
    public boolean puede_recibir(RalladoV rv) {
        return true;
    }

    public boolean match(Entidad entidad) {
        return entidad.match_with(this);
    }
    public boolean match_with(Caramelo caramelo) {
        return this.color == caramelo.getColor();
    }
    public boolean match_with(RalladoH ralladoH) {
        return this.color == ralladoH.getColor();
    }
    public boolean match_with(RalladoV ralladoV) {
        return this.color == ralladoV.getColor();
    }
    public boolean match_with(Envuelto envuelto) {
        return this.color == envuelto.getColor();
    }
    public boolean match_with(Glaseado glaseado) {
        return false;
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