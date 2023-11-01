package Juego;

import Entidad.*;
import Tablero.Tablero;

public interface Factory {
    public Caramelo crearCaramelo(int f, int c, Color color,Tablero t);
    public Caramelo crearCarameloCaida(int f,int c,Color color,Tablero t);
    public Gelatina crearGelatina(int f, int c, Color color, Tablero t);
    public Glaseado crearGlaseado(int f, int c,  Tablero t);
    public Bomba crearBomba(int f, int c, Tablero t);
    public Cruz crearCruz(int f, int c, Color color, Tablero t);
    public Envuelto crearEnvuelto(int f, int c, Color color,  Tablero t);
    public RalladoH crearRalladoH(int f, int c, Color color, Tablero t);
    public RalladoV crearRalladoV(int f, int c, Color color, Tablero t);
}