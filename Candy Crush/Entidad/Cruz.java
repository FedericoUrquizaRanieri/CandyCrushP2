package Entidad;

import Juego.Juego;
import Tablero.Tablero;

public class Cruz extends Caramelo{
    //Atributos
    boolean explotando;

    //Constructor
    public Cruz(int f, int c, Color color,String path) {
        super(f, c, color, path);
        this.explotando = false;
        imagePath = Juego.class.getResource(path+"/Cruz/"+color.toString().toLowerCase()+".png");
    }

    //Metodos
    public void destruirse(Tablero t){
        if(!explotando) {
            explotando = true;
            for(int i=0; i<t.getDimension(); i++){
                if(i != columna && t.getEntidad(fila, i) != null){
                    t.getEntidad(fila, i).destruirse(t);
                }
            }
            for(int i=0; i<t.getDimension(); i++){
                if(i != fila && t.getEntidad(i, columna) != null){
                    t.getEntidad(i, columna).destruirse(t);
                }
            }
            destruida = true;
            t.notificarDestruccion(this.color);
            t.notificarDestruccionCruz();
            eg.destruirse();
            t.aumentarPuntaje(100);
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
    public boolean seDestruyen(Caramelo caramelo) {
        return this.color == caramelo.getColor();
    }
    public boolean esPosibleIntercambiar(Entidad e) {
        return e.puedeRecibir(this);
    }
}
