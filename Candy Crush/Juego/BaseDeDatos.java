package Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos implements Serializable{
    //Atributos
    protected int puntajeTotal;
    protected int puntajeActual;
    protected String nombreActual;
    protected List<Par<String,Integer>> top = new ArrayList<>(5);

    //Constructor
    public BaseDeDatos() {
        this.puntajeActual = 0;
        this.puntajeTotal = 0;
    }

    //Metodos
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

    public List<Par<String,Integer>> getLista(){
        return top; 
    }

    public void guardarPuntaje() {
        mezclarPuntos();
        if(top.size() == 5){
            if(top.get(4).getValor() < puntajeTotal){
                top.remove(top.get(4));
                top.add(new Par<String,Integer>(nombreActual, puntajeTotal));
            }
        }else{
            top.add(new Par<String,Integer>(nombreActual, puntajeTotal));
        }
        ordenarLista(top);
    }

    public void ordenarLista(List<Par<String,Integer>> top){
        int n = top.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Par<String, Integer> parActual = top.get(j);
                Par<String, Integer> parSiguiente = top.get(j + 1);
                if (parActual.getValor() < parSiguiente.getValor()) {
                    top.set(j, parSiguiente);
                    top.set(j + 1, parActual);
                }
            }
        }
    }
}
