package GUI;

import GUI.Threads.CentralAnimaciones;
import Juego.Juego;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JLayeredPane {
    //Atributos
    protected JLabel jugador;
    protected Juego juego;
    protected int posX;
    protected int posY;
    protected int animacionesPendientes;
    protected static boolean bloquearIntercambios;
    protected CentralAnimaciones centralAnimaciones;

    //constructor
    public Panel(Juego juego, JLabel jugador) {
        this.jugador = jugador;
        this.juego = juego;
        this.centralAnimaciones = new CentralAnimaciones(this);
        bloquearIntercambios = false;
        posX = 0; // Ubiacion del jugador en el tablero
        posY = 0; // Ubiacion del jugador en el tablero

        setLayout(null);
        setPreferredSize(new Dimension(Utils.panelWidth(), Utils.panelHeight()));
        setFocusable(true);
        setOpaque(false);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                repaint();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:{
                        int swapX = jugador.getX() - Utils.labelSpacing - Utils.labelWidth;
                        int swapY = jugador.getY();
                        if (posY - 1 >= 0) {
                            posY--;
                            juego.moverCursor(posX, posY);
                            jugador.setLocation(swapX, swapY);
                        }
                        break;
                    }
                    case KeyEvent.VK_A:{
                        if(!bloquearIntercambios && posY - 1 >= 0) {
                            juego.swap(posX, posY - 1);
                        }
                        break;
                    }
                    case KeyEvent.VK_RIGHT:{
                        int swapX = jugador.getX() + Utils.labelSpacing + Utils.labelWidth;
                        int swapY = jugador.getY();
                        if (posY + 1 < Utils.dimension) {
                            posY++;
                            juego.moverCursor(posX, posY);
                            jugador.setLocation(swapX, swapY);
                        }
                        break;
                    }
                    case KeyEvent.VK_D:{
                        if(!bloquearIntercambios && posY + 1 < Utils.dimension) {
                            juego.swap(posX, posY + 1);
                        }
                        break;
                    }
                    case KeyEvent.VK_UP:{
                        int swapX = jugador.getX();
                        int swapY = jugador.getY() - Utils.labelSpacing - Utils.labelHeight;
                        if (posX - 1 >= 0) {
                            posX--;
                            juego.moverCursor(posX, posY);
                            jugador.setLocation(swapX, swapY);
                        }
                        break;
                    }
                    case KeyEvent.VK_W:{
                        if(!bloquearIntercambios && posX - 1 >= 0) {
                            juego.swap(posX - 1, posY);
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        int swapX = jugador.getX();
                        int swapY = jugador.getY() + Utils.labelSpacing + Utils.labelHeight;
                        if (posX + 1 < Utils.dimension) {
                            posX++;
                            juego.moverCursor(posX, posY);
                            jugador.setLocation(swapX, swapY);
                        }
                        break;
                    }
                    case KeyEvent.VK_S:{
                        if(!bloquearIntercambios && posX + 1 < Utils.dimension){
                            juego.swap(posX + 1, posY);
                        }
                        break;
                    }
                }
            }
        });
        setVisible(true);
    }

    //Metodos
    public void animarMovimiento(EntidadGrafica origen, EntidadGrafica destino) {
        centralAnimaciones.animarSwap(origen, destino);
    }

    public void animarCaida(EntidadGrafica entidadGrafica, int toX, int toY) {
        centralAnimaciones.animarCaida(entidadGrafica, toX, toY);
    }

    public void animarDestruccion(EntidadGrafica entidadGrafica) {
        centralAnimaciones.animarDestruccion(entidadGrafica);
    }

    public void comenzoAnimacion() {
        bloquearIntercambios = true;
    }

    public void terminoAnimacion() {
        bloquearIntercambios = false;
        juego.animacionesTerminadas();
        repaint();
    }

    public void limpiar() {
        this.removeAll();
        this.add(jugador);
    }

    public void notificar_Animaciones_terminadas(){
        juego.animacionesTerminadas();
    }
}
