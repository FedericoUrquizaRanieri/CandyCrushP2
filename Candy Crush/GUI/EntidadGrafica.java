package GUI;

import Entidad.Entidad;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class EntidadGrafica extends JLabel{
    //Atributos
    private Entidad entidad;
    private Panel panel;

    //Constructor
    public EntidadGrafica(int x, int y,Entidad e, Panel panel) {
        entidad=e;
        this.panel = panel;
        ImageIcon ico = new ImageIcon(entidad.getImage());
        Image img = ico.getImage();
        Image new_img = img.getScaledInstance(Utils.labelWidth, Utils.labelHeight, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(new_img));
        setBounds(Utils.labelPositionX(y),Utils.labelPositionY(x), Utils.labelWidth, Utils.labelHeight);
    }

    //Metodos
    public void destruirse() {
        panel.animarDestruccion(this);
        entidad = null;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void notificarCambioPosicion(EntidadGrafica entidadGrafica) {
        panel.animarMovimiento(this, entidadGrafica);
    }
    public void notificarCaida(int toX, int toY) {
        panel.animarCaida(this, toX, toY);
    }
    public void notificarDestruccion(){
        panel.animarDestruccion(this);
    }
}