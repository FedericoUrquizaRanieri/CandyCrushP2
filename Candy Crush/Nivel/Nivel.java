package Nivel;

import java.util.Timer;
import java.util.TimerTask;

import Entidad.Color;
import Juego.Juego;

public class Nivel{
    //Atributos
    protected Juego miJuego;
    protected int nivel;
    protected int vidas = 3;
    protected int tiempo;
    protected int movimientos;
    protected int objetivoCaramelo;
    protected Color tipoCaramelo;
    protected int objetivoGlaseado;
    protected int objetivoGelatina;
    protected int objetivoEnvuelto;
    protected int objetivoCruz;
    protected int objetivoBomba;
    protected contadorTiempo time;

    //Constructor
    public Nivel(Juego j,int nivel){
        miJuego=j;
        this.nivel=nivel;
        tiempo = 300000;
        time = new contadorTiempo();
    }
    
    //Metodos
    public void restarVida(){
        vidas--;
        if(vidas==0){
            miJuego.llamarJuegoPerdido();
            nivel=1;
            miJuego.regenerar(1);
            vidas=3;
        }
        else{
            miJuego.llamarNivelPerdido();
            miJuego.bajarPuntaje();
            miJuego.regenerar(nivel);
        }
    }
    public void terminarNivel(){
        miJuego.llamarJuegoPerdido();
        nivel=1;
        miJuego.regenerar(1);
        vidas=3;
    }
    public int getVidas(){
        return vidas;
    }
    public void setTiempo(String t){
        tiempo=(((t.charAt(0)-48)*10+t.charAt(1)-48)*60+((t.charAt(3)-48)*10+t.charAt(4)-48));
    }
    public void setMov(int m){
        movimientos=m;
    }
    public int getMov(){
        return movimientos;
    }
    public int getObjetivoCaramelo(){
        return objetivoCaramelo;
    }
    public Color getColorObjetivo(){
        return tipoCaramelo;
    }
    public void setObjetivoCaramelo(int o, Color c){
        objetivoCaramelo=o;
        tipoCaramelo=c;
    }
    public int getObjetivoGlaseado(){
        return objetivoGlaseado;
    }
    public void setObjetivoGlaseado(int o){
        objetivoGlaseado=o;
    }
    public int getObjetivoGelatina(){
        return objetivoGelatina;
    }
    public void setObjetivoGelatina(int o){
        objetivoGelatina=o;
    }
    public int getObjetivoCruz(){
        return objetivoCruz;
    }
    public void setObjetivoCruz(int o){
        objetivoCruz = o;
    }
    public int getObjetivoBomba(){
        return objetivoBomba;
    }
    public void setObjetivoBomba(int o){
        objetivoBomba = o;
    }
    public int getObjetivoEnvuelto(){
        return objetivoEnvuelto;
    }
    public void setObjetivoEnvuelto(int o){
        objetivoEnvuelto=o;
    }
    public void restarCaramelo(Color c){
        if(objetivoCaramelo>0){
            if(c==tipoCaramelo){
                objetivoCaramelo--;
                miJuego.getMiGUI().notificarObjetivoCaramelo();
            }
        }
    }
    public void restarGlaseado(){
        if(objetivoGlaseado>0){
            objetivoGlaseado--;
            miJuego.getMiGUI().notificarObjetivoGlaseado();
        }
    }
    public void restarGelatina(){
        if(objetivoGelatina>0){
            objetivoGelatina--;
            miJuego.getMiGUI().notificarObjetivoGelatina();
        }
    }
    public void restarEnvuelto(){
        if(objetivoEnvuelto>0){
            objetivoEnvuelto--;
            miJuego.getMiGUI().notificarObjetivoEnvuelto();
        }
    }
    public void restarBomba() {
        if(objetivoBomba>0){
            objetivoBomba--;
            miJuego.getMiGUI().notificarObjetivoBomba();
        }
    }
    public void restarMov(){
        if(movimientos>0){
            movimientos--;
            miJuego.getMiGUI().notificarMovimiento();
        }
        if(movimientos==0){
            restarVida();
        }
    }
    public int getNivel(){
        return nivel;
    }
    public boolean objetivosTerminados(){
        return (objetivoCaramelo<=0 && objetivoGelatina<=0 && objetivoGlaseado<=0 && objetivoEnvuelto<=0);
    }
    public void setNivel(int numero){
        nivel = numero;
    }

    //Clase interna del tiempo
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
                    if(miJuego.getMiGUI()!=null)
                        miJuego.getMiGUI().notificarTiempo(tiempo);
                }
                else {
                    restarVida();
                }
            }
        }
    }
}