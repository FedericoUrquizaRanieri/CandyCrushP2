package GUI.Threads;

import GUI.EntidadGrafica;

public class AnimadorSwap extends Thread implements Animador{

    //Atributos
    protected EntidadGrafica entidadGrafica1;
    protected EntidadGrafica entidadGrafica2;
    protected int delay;
    private ManejadorAnimaciones manager;

    //Constructor
    public AnimadorSwap(ManejadorAnimaciones manager, EntidadGrafica entidadGrafica1, EntidadGrafica entidadGrafica2,int delay) {
        this.manager = manager;
        this.entidadGrafica1 = entidadGrafica1;
        this.entidadGrafica2 = entidadGrafica2;
        this.delay = delay;
    }

    public void run() {
        int eg2_toX = entidadGrafica1.getX();
        int eg2_toY = entidadGrafica1.getY();
        int cursorX1 = eg2_toX;
        int cursorY1 = eg2_toY;

        int eg1_toX = entidadGrafica2.getX();
        int eg1_toY = entidadGrafica2.getY();
        int cursorX2 = eg1_toX;
        int cursorY2 = eg1_toY;

        boolean stopX = cursorX1 == eg1_toX;
        boolean stopY = cursorY1 == eg1_toY;

        while(!stopX || !stopY) {
            if(!stopX) {
                cursorX1 += cursorX1 > eg1_toX ? -1 : 1;
                cursorX2 += cursorX2 > eg2_toX ? -1 : 1;
            }
            if(!stopY) {
                cursorY1 += cursorY1 > eg1_toY ? -1 : 1;
                cursorY2 += cursorY2 > eg2_toY ? -1 : 1;
            }
            entidadGrafica1.setLocation(cursorX1, cursorY1);
            entidadGrafica2.setLocation(cursorX2, cursorY2);
            stopX = cursorX1 == eg1_toX;
            stopY = cursorY1 == eg1_toY;
            try {
                sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        manager.terminoAnimacion(this);
        entidadGrafica1 = null;
        entidadGrafica2 = null;
    }

    public EntidadGrafica getEntidadGrafica() {
        return entidadGrafica1;
    }

    public void comenzarAnimacion() {
        this.start();
    }
}
