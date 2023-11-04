package Entidad;

import java.util.Timer;
import java.util.TimerTask;

import GUI.EntidadGraficaDoble;
import Tablero.Tablero;

public class Bomba extends Glaseado{
    //Atributos
    protected int tiempo=30;

    public Bomba(int f, int c,String path) {
        super(f, c,path);
        imagePath="Candy Crush/Imagenes/"+path+"/Extras/Bomba.png";
        new contadorTiempo();
    }
    //Clase del tiempo
    class contadorTiempo {
        Timer t;
        public contadorTiempo() {
            t = new Timer();
            t.scheduleAtFixedRate(new contador(), 0,1000);
        }
        class contador extends TimerTask {
            public void run() {
                if (tiempo > 0) {
                    tiempo--;
                    if(eg!=null){
                        ((EntidadGraficaDoble) eg).setTiempo(tiempo);
                    }
                }
                else {
                    t.cancel();
                    explotar();
                }
            }
        }
    }
    public void destruirse(Tablero t){
        destruida = true;
        eg.destruirse();
        //t.notificarDestruccionBomba();
        //t.aumentarPuntaje(25);
    }
    public void explotar(){
        //eg.destruirse();
        //nivel.terminar vidas;
        //tablero.notificarDestruccionBomba();
    }
}
