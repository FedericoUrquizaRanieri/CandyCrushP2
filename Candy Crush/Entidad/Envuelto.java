package Entidad;

import Tablero.Tablero;

public class Envuelto extends Caramelo{
    boolean explotando;
    public Envuelto(int f, int c, Color color){
        super(f, c, color);
        this.explotando = false;
        imagePath="Candy Crush/Imagenes/Envueltos/"+ color.toString().toUpperCase()+".png";
    }

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