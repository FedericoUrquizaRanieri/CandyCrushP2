package Entidad;

import Juego.Juego;
import Tablero.Tablero;

public class Glaseado extends Entidad implements Subscriber{
    //Constructor
    public Glaseado(int f, int c, String path){
        super(f,c);
        imagePath = Juego.class.getResource(path+"/Extras/Merengue.png");
        this.color = Color.NEGRO;
    }

    //Metodos
    public void destruirse(Tablero t){
        destruida = true;
        eg.destruirse();
        t.notificarDestruccionGlaseado();
        t.aumentarPuntaje(25);
    }
    public boolean seDestruyeCon(Entidad entidad) {
        return entidad.seDestruyen(this);
    }
    public void chequeoDestruccion(Tablero tablero){
        destruirse(tablero);
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
    public boolean seDestruyen(Cruz cruz) {
        return false;
    }

    public void cambiarPosicionCon(Entidad entidad, Tablero tablero) {}
    public void cambiarPosicion(Caramelo caramelo, Tablero tablero) {}
    public void cambiarPosicion(Gelatina gelatina, Tablero tablero) {}

    public boolean esPosibleIntercambiar(Entidad e) {
        return false;
    };
    public boolean puedeRecibir(Caramelo c) {
        return false;
    }
    public boolean puedeRecibir(Glaseado g) {
        return false;
    }
    public boolean puedeRecibir(Envuelto p) {
        return false;
    }
    public boolean puedeRecibir(RalladoH rh) {
        return false;
    }
    public boolean puedeRecibir(RalladoV rv) {
        return false;
    }
    public boolean puedeRecibir(Cruz cruz) {
        return false;
    }

    public boolean match(Entidad entidad) {
        return false;
    }

    public boolean matchWith(Caramelo caramelo) {
        return false;
    }

    public boolean matchWith(RalladoH ralladoH) {
        return false;
    }

    public boolean matchWith(RalladoV ralladoV) {
        return false;
    }

    public boolean matchWith(Envuelto envuelto) {
        return false;
    }

    public boolean matchWith(Glaseado glaseado) {
        return false;
    }

    public boolean matchWith(Cruz cruz) {
        return false;
    }

    public void caer(int f, int c, Tablero t){
        cambiarPosicion(f, c);
        t.getGrilla()[f][c].recibir(f, c, this, t);
    }

    public void recibir(int f, int c, Caramelo e, Tablero t){
        t.getGrilla()[f][c] = e;
    }

    public void recibir(int f, int c, Glaseado gla, Tablero t){
        t.getGrilla()[f][c] = gla;
    }

    public void avisar(Tablero tablero) {
        Entidad adyacente1 = tablero.getEntidad(this.fila, this.columna + 1);
        Entidad adyacente2 = tablero.getEntidad(this.fila, this.columna - 1);
        Entidad adyacente3 = tablero.getEntidad(this.fila + 1, this.columna);
        Entidad adyacente4 = tablero.getEntidad(this.fila - 1, this.columna);

        if((adyacente1 != null && adyacente1.estaDestruida()) ||
                (adyacente2 != null && adyacente2.estaDestruida()) ||
                (adyacente3 != null && adyacente3.estaDestruida()) ||
                (adyacente4 != null && adyacente4.estaDestruida())) {
            this.destruirse(tablero);
        } else {
            tablero.addSubscriber(this);
        }
    }
}