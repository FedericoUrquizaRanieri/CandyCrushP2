package Entidad;

import Tablero.Tablero;

public class Cruz extends Caramelo{
    boolean explotando;
    public Cruz(int f, int c, Color color,String path) {
        super(f, c, color, path);
        this.explotando = false;
        imagePath="Candy Crush/Imagenes/"+path+"/Cruz/"+color.toString().toLowerCase()+".png";
    }
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
            eg.destruirse();
            t.aumentarPuntaje(100);
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
    public boolean se_destruyen(Caramelo caramelo) {
        return this.color == caramelo.getColor();
    }
    public boolean es_posible_intercambiar(Entidad e) {
        return e.puede_recibir(this);
    }
}
