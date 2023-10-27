package Juego;

public class BaseDeDatos {
    private int puntajeActual;

    public BaseDeDatos() {
        this.puntajeActual = 0;
    }

    public void aumentarPuntaje(int puntaje) {
        puntajeActual += puntaje;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }
}
