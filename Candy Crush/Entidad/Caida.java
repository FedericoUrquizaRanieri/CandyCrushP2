package Entidad;

import Tablero.Tablero;

public interface Caida {
    public void recibir(int f, int c, Caramelo caramelo, Tablero tablero);
    public void recibir(int f, int c, Glaseado glaseado, Tablero tablero);
    public void caer(int f, int c, Tablero tablero);
}
