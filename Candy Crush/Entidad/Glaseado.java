package Entidad;

import Juego.Juego;
import Tablero.Tablero;

public class Glaseado extends Entidad implements Subscriber{
    public Glaseado(int f, int c, String path){
        super(f,c);
        imagePath = Juego.class.getResource(path+"/Extras/Merengue.png");
        this.color = Color.NEGRO;
    }
    public void destruirse(Tablero t){
        destruida = true;
        eg.destruirse();
        t.notificarDestruccionGlaseado();
        t.aumentarPuntaje(25);
    }
    public boolean se_destruye_con(Entidad entidad) {
        return entidad.se_destruyen(this);
    }
    public void chequeoDestruccion(Tablero tablero){
        destruirse(tablero);
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
    public boolean se_destruyen(Cruz cruz) {
        return false;
    }

    public void cambiarPosicionCon(Entidad entidad, Tablero tablero) {}
    public void cambiarPosicion(Caramelo caramelo, Tablero tablero) {}
    public void cambiarPosicion(Gelatina gelatina, Tablero tablero) {}

    public boolean es_posible_intercambiar(Entidad e) {
        return false;
    };
    public boolean puede_recibir(Caramelo c) {
        return false;
    }
    public boolean puede_recibir(Glaseado g) {
        return false;
    }
    public boolean puede_recibir(Envuelto p) {
        return false;
    }
    public boolean puede_recibir(RalladoH rh) {
        return false;
    }
    public boolean puede_recibir(RalladoV rv) {
        return false;
    }
    public boolean puede_recibir(Cruz cruz) {
        return false;
    }

    public boolean match(Entidad entidad) {
        return false;
    }

    public boolean match_with(Caramelo caramelo) {
        return false;
    }

    public boolean match_with(RalladoH ralladoH) {
        return false;
    }

    public boolean match_with(RalladoV ralladoV) {
        return false;
    }

    public boolean match_with(Envuelto envuelto) {
        return false;
    }

    public boolean match_with(Glaseado glaseado) {
        return false;
    }

    public boolean match_with(Cruz cruz) {
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