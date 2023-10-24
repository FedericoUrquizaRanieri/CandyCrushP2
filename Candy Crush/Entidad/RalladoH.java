package Entidad;

import Tablero.Tablero;

public class RalladoH extends Caramelo{
    boolean explotando;
    public RalladoH(int f, int c, Color color){
        super(f, c, color);
        this.explotando = false;
        imagePath="Candy Crush/Imagenes/Rayados/RayadosH/"+ color.toString().toUpperCase()+".png";
    }
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

    public boolean es_posible_intercambiar(Entidad e) {
        return e.puede_recibir(this);
    }
}
