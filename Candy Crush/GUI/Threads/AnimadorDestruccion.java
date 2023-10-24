package GUI.Threads;

import javax.swing.ImageIcon;

import GUI.EntidadGrafica;
import utils.Utils;

import java.awt.*;

public class AnimadorDestruccion extends Thread implements Animador{
    //Atributos
    protected EntidadGrafica entidadGrafica;
    private ManejadorAnimaciones manager;

    //Constructor
    public AnimadorDestruccion(EntidadGrafica entidadGrafica, ManejadorAnimaciones manager) {
        this.entidadGrafica = entidadGrafica;
        this.manager = manager;
    }

    public void run() {
        ImageIcon ico = new ImageIcon("Candy Crush/Imagenes/Extras/Explosion.png");
        Image img = ico.getImage();
        Image new_img = img.getScaledInstance(Utils.labelWidth, Utils.labelHeight, Image.SCALE_SMOOTH);
        entidadGrafica.setIcon(new ImageIcon(new_img));
        entidadGrafica.repaint();
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        entidadGrafica.setIcon(null);
        manager.terminoAnimacion(this);
    }

    @Override
    public EntidadGrafica getEntidadGrafica() {
        return null;
    }

    @Override
    public void comenzarAnimacion() {
        this.start();
    }
}
