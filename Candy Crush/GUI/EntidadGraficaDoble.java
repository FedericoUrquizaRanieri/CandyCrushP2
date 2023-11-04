package GUI;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Entidad.Entidad;

public class EntidadGraficaDoble extends EntidadGrafica{
    //Atributos
    protected JLabel agregado;
    protected int tiempo=999;
    //Constructor
    public EntidadGraficaDoble(int x, int y, Entidad e, Panel panel) {
        super(x, y, e, panel);
        agregado= new JLabel();
        agregado.setText(Integer.toString(tiempo));
        agregado.setHorizontalAlignment(SwingConstants.CENTER);
        agregado.setForeground(Color.WHITE);
        agregado.setBounds(0, 0, 60, 60);
        agregado.setFont(new Font("TimesRoman", Font.BOLD, 30));
        this.add(agregado);
    }
    public void setTiempo(int time){
        this.tiempo=time;
        agregado.setText(String.valueOf(tiempo));
    }
    public void destruirse(){
        this.panel.remove(this);
        this.remove(agregado);
        this.entidad=null;
    }
}
