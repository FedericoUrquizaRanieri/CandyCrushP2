package Entidad;

import Tablero.Tablero;

public interface Intercambiable {
    public void cambiarPosicionCon(Entidad entidad, Tablero tablero);
    public void cambiarPosicion(Caramelo caramelo, Tablero tablero);
    public void cambiarPosicion(Gelatina gelatina, Tablero tablero);
    public boolean esPosibleIntercambiar(Entidad e);
    public boolean puedeRecibir(Caramelo c);
    public boolean puedeRecibir(Glaseado g);
    public boolean puedeRecibir(Envuelto p);
    public boolean puedeRecibir(RalladoH rh);
    public boolean puedeRecibir(RalladoV rv);
    public boolean puedeRecibir(Cruz cruz);
}
