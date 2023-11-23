package Entidad;

import Juego.Juego;
import Tablero.Tablero;

public class Gelatina extends Entidad{
    //Atributos
    private Caramelo caramelo;

    //Constructor
    public Gelatina(int f, int c, Color color,String path){
        super(f,c);
        imagePath = Juego.class.getResource(path+"/Extras/Gelatina.png");
        caramelo = new Caramelo(f,c, color, path);
    }

    //Metodos
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
            eg.destruirse();
            caramelo.destruirse(t);
            destruida = true;
            t.aumentarPuntaje(10);
        }
    }
    public boolean seDestruyeCon(Entidad entidad) {
        return entidad.seDestruyen(caramelo);
    }
    public boolean seDestruyen(Caramelo caramelo) {
        return false;
    }
    public boolean seDestruyen(RalladoH ralladoH) {
        return ralladoH.seDestruyen(caramelo);
    }
    public boolean seDestruyen(RalladoV ralladoV) {
        return ralladoV.seDestruyen(caramelo);
    }
    public boolean seDestruyen(Envuelto envuelto) {
        return envuelto.seDestruyen(caramelo);
    }
    public boolean seDestruyen(Cruz cruz) {
        return cruz.seDestruyen(caramelo);
    }

    public boolean esPosibleIntercambiar(Entidad e) {
        return e.puedeRecibir(this.getCaramelo());
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
        return entidad.matchWith(this.caramelo);
    }
    public boolean matchWith(Caramelo caramelo) {
        return this.caramelo.getColor() == caramelo.getColor();
    }
    public boolean matchWith(RalladoH ralladoH) {
        return this.caramelo.getColor() == ralladoH.getColor();
    }
    public boolean matchWith(RalladoV ralladoV) {
        return this.caramelo.getColor() == ralladoV.getColor();
    }
    public boolean matchWith(Envuelto envuelto) {
        return this.caramelo.getColor() == envuelto.getColor();
    }
    public boolean matchWith(Glaseado glaseado) {
        return false;
    }
    public boolean matchWith(Cruz cruz) {
        return this.caramelo.getColor() == cruz.getColor();
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
