package Entidad;

import java.util.Timer;
import java.util.TimerTask;

import Tablero.Tablero;

public class Bomba extends Glaseado {
    // Atributos
    protected int tiempo = (int) (Math.random() * ((55 - 25) + 1)) + 25;
    protected Tablero tablero;
    protected Timer timer;

    public Bomba(int f, int c, String path, Tablero t) {
        super(f, c, path);
        imagePath = "Candy Crush/Imagenes/" + path + "/Extras/Bomba.png";
        tablero = t;
        timer = new Timer();
        timer.scheduleAtFixedRate(new contador(), 0, 1000);
    }

    // Clase del tiempo
    class contador extends TimerTask {
        public void run() {
            if (tiempo > 0) {
                tiempo--;
                if (eg != null) {
                    eg.actualizarse(tiempo);
                }
            }else{
                destruirse(tablero);
                tablero.terminar();
            }
        }
    }

    public void destruirse(Tablero t) {
        timer.cancel();
        timer.purge();
        destruida = true;
        eg.destruirse();
        t.notificarDestruccionBomba();
        tablero.aumentarPuntaje(150);
    }
}
