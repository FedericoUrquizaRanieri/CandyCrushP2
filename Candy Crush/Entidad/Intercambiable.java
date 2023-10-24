package Entidad;

import Tablero.Tablero;

public interface Intercambiable {
    public void cambiarPosicionCon(Entidad entidad, Tablero tablero);
    public void cambiarPosicion(Caramelo caramelo, Tablero tablero);
    public void cambiarPosicion(Gelatina gelatina, Tablero tablero);
    public boolean es_posible_intercambiar(Entidad e);
    public boolean puede_recibir(Caramelo c);
    public boolean puede_recibir(Glaseado g);
    public boolean puede_recibir(Envuelto p);
    public boolean puede_recibir(RalladoH rh);
    public boolean puede_recibir(RalladoV rv);
}
