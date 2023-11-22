package Entidad;

import Juego.Juego;
import Tablero.Tablero;

public class RalladoH extends Caramelo{
    //Atributos
    boolean explotando;

    //Constructor
    public RalladoH(int f, int c, Color color,String path){
        super(f, c, color,path);
        this.explotando = false;
        imagePath = Juego.class.getResource(path+"/Rayados/RayadosH/"+ color.toString().toUpperCase()+".png");
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
            destruida = true;
            t.notificarDestruccion(this.color);
            eg.destruirse();
            t.aumentarPuntaje(45);
        }
    }

    public boolean se_destruye_con(Entidad entidad) {
        return entidad.se_destruyen(this);
    }
    public boolean se_destruyen(RalladoH ralladoH) {
        return true;
    }
    public boolean se_destruyen(RalladoV ralladoV) {
        return true;
    }
    public boolean se_destruyen(Envuelto envuelto) {
        return true;
    }
    public boolean se_destruyen(Cruz cruz) {
        return true;
    }

    public boolean es_posible_intercambiar(Entidad e) {
        return e.puede_recibir(this);
    }
}
