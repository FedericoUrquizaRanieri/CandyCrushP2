package Entidad;

import Juego.Juego;
import Tablero.Tablero;

public class Envuelto extends Caramelo{
    //Atributos
    boolean explotando;

    //Constructor
    public Envuelto(int f, int c, Color color, String path){
        super(f, c, color,path);
        this.explotando = false;
        imagePath = Juego.class.getResource(path+"/Envueltos/"+ color.toString().toUpperCase()+".png");
    }

    //Metodos
    public Color getColor() {
        return color;
    }
    
    public void destruirse(Tablero t){
        if(!explotando) {
            explotando = true;
            Entidad e = null;
            for (int i = fila - 1; i <= fila + 1; i++) {
                for (int j = columna - 1; j <= columna + 1; j++) {
                    e = t.getEntidad(i, j);
                    if (e != null && i >= 0 && i < 10 && j >= 0 && j < 10 && (i != fila || j != columna)) {
                        e.destruirse(t);
                    }
                }
            }
            destruida = true;
            t.notificarDestruccion(this.color);
            t.notificarDestruccionEnvuelto();
            eg.destruirse();
            t.aumentarPuntaje(50);
        }
    }

    public boolean seDestruyeCon(Entidad entidad) {
        return entidad.seDestruyen(this);
    }
    public boolean seDestruyen(RalladoH ralladoH) {
        return true;
    }
    public boolean seDestruyen(RalladoV ralladoV) {
        return true;
    }
    public boolean seDestruyen(Envuelto envuelto) {
        return true;
    }
    public boolean seDestruyen(Cruz cruz) {
        return true;
    }
    public boolean esPosibleIntercambiar(Entidad e) {
        return e.puedeRecibir(this);
    }
}