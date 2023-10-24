package GUI.Threads;

import GUI.EntidadGrafica;
import GUI.Panel;

import java.util.LinkedList;
import java.util.Queue;

public class CentralAnimaciones implements ManejadorAnimaciones{

    //Atributos
    protected Panel gui;
    protected Queue<Animador> swapQueue, destroyQueue, fallingQueue;
    protected boolean animacionesActivas;

    //Constructor
    public CentralAnimaciones(Panel panel) {
        this.animacionesActivas = false;
        this.gui = panel;
        this.swapQueue = new LinkedList<>();
        this.destroyQueue = new LinkedList<>();
        this.fallingQueue = new LinkedList<>();
    }

    //Metodos
    public void ejecutarAnimadores() {
        if(!swapQueue.isEmpty()) {
            animacionesActivas = true;
            Animador animador = swapQueue.poll();

            animador.comenzarAnimacion();
        } else if(!destroyQueue.isEmpty()) {
            animacionesActivas = true;
            Animador animador = destroyQueue.poll();

            animador.comenzarAnimacion();
        } else if(!fallingQueue.isEmpty()) {
            animacionesActivas = true;
            Animador animador = fallingQueue.poll();

            animador.comenzarAnimacion();
        } else {
            animacionesActivas = false;
            gui.terminoAnimacion();
        }
    }

    public void animarSwap(EntidadGrafica origen, EntidadGrafica destino) {
        Animador animador = new AnimadorSwap(this, origen, destino, 1);
        gui.comenzoAnimacion();
        swapQueue.offer(animador);
        if(!animacionesActivas) {
            ejecutarAnimadores();
        }
    }

    public void animarCaida(EntidadGrafica entidadGrafica, int toX, int toY) {
        Animador animador = new AnimadorCaida(this, toX, toY, entidadGrafica, 1);
        gui.comenzoAnimacion();
        fallingQueue.offer(animador);
        if(!animacionesActivas) {
            ejecutarAnimadores();
        }
    }

    public void animarDestruccion(EntidadGrafica entidadGrafica) {
        Animador animador = new AnimadorDestruccion(entidadGrafica, this);
        gui.comenzoAnimacion();
        destroyQueue.offer(animador);
        if(!animacionesActivas)
            ejecutarAnimadores();
    }

    public void terminoAnimacion(Animador a) {
        ejecutarAnimadores();
    }

    public void AnimacionesActivas(){
        if(swapQueue.isEmpty() && destroyQueue.isEmpty() && fallingQueue.isEmpty())
            gui.notificar_Animaciones_terminadas();
    }
}
