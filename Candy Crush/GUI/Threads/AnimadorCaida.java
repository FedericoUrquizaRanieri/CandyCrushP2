package GUI.Threads;

import GUI.EntidadGrafica;

public class AnimadorCaida extends Thread implements Animador{
    //Atributos
    protected EntidadGrafica entidadGrafica;
    protected int toX;
    protected int toY;
    protected int delay;
    private ManejadorAnimaciones manager;

    //Construcor
    public AnimadorCaida(ManejadorAnimaciones manager, int toX, int toY, EntidadGrafica entidadGrafica, int delay) {
        this.manager = manager;
        this.toX = toX;
        this.toY = toY;
        this.entidadGrafica = entidadGrafica;
        this.delay = delay;
    }

    public void run() {
        int posY = entidadGrafica.getY();
        boolean stopY = posY == toY;

        while(!stopY) {
            posY++;
            entidadGrafica.setLocation(toX, posY);
            stopY = posY == toY;
            try {
                if(posY%6 == 0)
                    sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        manager.terminoAnimacion(this);
    }

    public EntidadGrafica getEntidadGrafica() {
        return entidadGrafica;
    }

    public void comenzarAnimacion() {
        this.start();
    }
}
