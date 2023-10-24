package Entidad;

import Tablero.Tablero;

public class Gelatina extends Entidad{

    private Caramelo caramelo;

    public Gelatina(int f, int c, Color color){
        super(f,c,"Candy Crush/Imagenes/Extras/Gelatina.png");
        caramelo = new Caramelo(f,c, color);
    }

    public Color getColor() {
        return caramelo.getColor();
    }

    public void setCaramelo(Caramelo caramelo){
        this.caramelo = caramelo;
    }

    public void cambiarPosicion(int fila, int columna) {
        caramelo.cambiarPosicion(fila,columna);
    }

    public void cambiarPosicionCon(Entidad entidad, Tablero tablero) {
        entidad.cambiarPosicion(this, tablero);
    }
    public void cambiarPosicion(Caramelo caramelo, Tablero tablero) {
        int auxFila = caramelo.getFila();
        int auxColumna = caramelo.getColumna();
        caramelo.setFila(this.fila);
        caramelo.setColumna(this.columna);
        this.caramelo.fila = auxFila;
        this.caramelo.columna = auxColumna;

        tablero.getGrilla()[this.caramelo.getFila()][this.caramelo.getColumna()] = this.caramelo;
        this.caramelo.getEntidadGrafica().notificarCambioPosicion(caramelo.getEntidadGrafica());
        this.caramelo = caramelo;


    }

    public void cambiarPosicion(Gelatina gelatina, Tablero tablero) {
        int auxFila = gelatina.getFila();
        int auxColumna = gelatina.getColumna();
        gelatina.getCaramelo().setFila(this.fila);
        gelatina.getCaramelo().setColumna(this.columna);
        this.caramelo.fila = auxFila;
        this.caramelo.columna = auxColumna;

        Caramelo aux = gelatina.getCaramelo();
        gelatina.setCaramelo(this.caramelo);
        this.caramelo = aux;

        this.caramelo.getEntidadGrafica().notificarCambioPosicion(gelatina.getCaramelo().getEntidadGrafica());
    }

    public Caramelo getCaramelo(){
        return caramelo;
    }

    public void destruirse(Tablero t){
        if(!destruida){
            t.notificarDestruccionGelatina();
        }
        eg.destruirse();
        caramelo.destruirse(t);
        destruida = true;
    }
    public boolean se_destruye_con(Entidad entidad) {
        return entidad.se_destruyen(caramelo);
    }
    public boolean se_destruyen(Caramelo caramelo) {
        return false;
    }
    public boolean se_destruyen(RalladoH ralladoH) {
        return ralladoH.se_destruyen(caramelo);
    }
    public boolean se_destruyen(RalladoV ralladoV) {
        return ralladoV.se_destruyen(caramelo);
    }
    public boolean se_destruyen(Envuelto envuelto) {
        return envuelto.se_destruyen(caramelo);
    }

    public boolean es_posible_intercambiar(Entidad e) {
        return e.puede_recibir(this.getCaramelo());
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
        return entidad.match_with(this.caramelo);
    }
    public boolean match_with(Caramelo caramelo) {
        return this.caramelo.getColor() == caramelo.getColor();
    }
    public boolean match_with(RalladoH ralladoH) {
        return this.caramelo.getColor() == ralladoH.getColor();
    }
    public boolean match_with(RalladoV ralladoV) {
        return this.caramelo.getColor() == ralladoV.getColor();
    }
    public boolean match_with(Envuelto envuelto) {
        return this.caramelo.getColor() == envuelto.getColor();
    }
    public boolean match_with(Glaseado glaseado) {
        return false;
    }

    public void caer(int f, int c, Tablero t){
        cambiarPosicion(f, c);
        t.getGrilla()[f][c].recibir(f, c, caramelo, t); ;
    }

    public void recibir(int f, int c, Caramelo e, Tablero t) {
        if (destruida) {
            t.getGrilla()[f][c] = e;
        } else {
            setCaramelo(e);
        }
    }

    public void recibir(int f, int c, Glaseado gla, Tablero t){
        t.getGrilla()[f][c] = gla;
    }

}
