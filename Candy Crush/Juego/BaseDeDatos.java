package Juego;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseDeDatos implements Serializable{
    private int puntajeTotal;
    private int puntajeActual;
    private String nombreActual;
    protected Map<String,Integer> top = new HashMap<String,Integer>(5);

    public BaseDeDatos() {
        this.puntajeActual = 0;
        this.puntajeTotal = 0;
    }

    public void aumentarPuntaje(int punt) {
        puntajeActual += punt;
    }

    public void mezclarPuntos(){
        puntajeTotal += puntajeActual;
        puntajeActual = 0;
    }

    public int getPuntajeActual() {
        return puntajeTotal+puntajeActual;
    }

    public void setPuntaje(int i){
        puntajeActual = i;
    }

    public void reiniciarPuntos(){
        puntajeActual=0;
        puntajeTotal=0;
    }

    public int getPuntosTotales(){
        return puntajeTotal;
    }
    
    public void setNombre(String name){
        nombreActual=name;
    }

    public String getNombre(){
        return nombreActual;
    }
}