package Entidad;

import Tablero.Tablero;

public class Cruz extends Caramelo{
    boolean explotando;
    public Cruz(int f, int c, Color color,String path) {
        super(f, c, color,path);
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
        }
    }
    //agregar los intercambios o eso
}
