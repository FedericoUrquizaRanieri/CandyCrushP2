package Juego;

import Entidad.*;
import GUI.EntidadGrafica;
import GUI.EntidadGraficaDoble;
import Tablero.Tablero;

public class MineFactory implements Factory{
    //Atributos
    private String pathCreador;
    private Juego miJuego;
    //constructor
    public MineFactory(Juego j){
        pathCreador="Minecraft";
        miJuego = j;
    }
    @Override
    public Caramelo crearCaramelo(int f, int c, Color color, Tablero t) {
        Caramelo e=new Caramelo(f,c,color, pathCreador);
        t.getGrilla()[f][c] = e;
        EntidadGrafica eg = new EntidadGrafica(f, c, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        return e;
    }
    public Caramelo crearCarameloCaida(int f,int c,Color color,Tablero t){
        Caramelo e=new Caramelo(f,c,color, pathCreador);
        t.getGrilla()[f][c] = e;
        EntidadGrafica eg = new EntidadGrafica(-1, c, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        return e;
    }
    @Override
    public Gelatina crearGelatina(int f, int c, Color color, Tablero t) {
        Gelatina g= new Gelatina(f,c,color,pathCreador);
        t.getGrilla()[f][c]=g;
        EntidadGrafica eg = new EntidadGrafica(f, c, g.getCaramelo(), miJuego.getMiGUI().getPanel());
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        EntidadGrafica eg1 = new EntidadGrafica(f, c, g, miJuego.getMiGUI().getPanel());
        g.setEntidadGrafica(eg1);
        g.getCaramelo().setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg1);
        miJuego.getMiGUI().getPanel().setLayer(eg1, -1);
        return g;
    }
    @Override
    public Glaseado crearGlaseado(int f, int c, Tablero t) {
        Glaseado e=new Glaseado(f,c, pathCreador);
        t.getGrilla()[f][c] = e;
        EntidadGrafica eg = new EntidadGrafica(f, c, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        t.addSubscriber(e);
        return e;
    }
    @Override
    public Bomba crearBomba(int f, int c, Tablero t) {
        Bomba e=new Bomba(f,c, pathCreador,t);
        t.getGrilla()[f][c] = e;
        EntidadGraficaDoble eg = new EntidadGraficaDoble(f, c, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        t.addSubscriber(e);
        return e;
    }
    @Override
    public Cruz crearCruz(int f, int c, Color color, Tablero t) {
        Cruz g= new Cruz(f,c,color, pathCreador);
        t.getGrilla()[f][c]=g;
        EntidadGrafica eg1 = new EntidadGrafica(f, c, g, miJuego.getMiGUI().getPanel());
        g.setEntidadGrafica(eg1);
        miJuego.getMiGUI().insertarEntidadGrafica(eg1);
        return g;
    }
    @Override
    public Envuelto crearEnvuelto(int f, int c, Color color, Tablero t) {
        Envuelto e=new Envuelto(f,c,color, pathCreador);
        t.getGrilla()[f][c] = e;
        EntidadGrafica eg = new EntidadGrafica(f, c, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        return e;
    }
    @Override
    public RalladoH crearRalladoH(int f, int c, Color color, Tablero t) {
        RalladoH e=new RalladoH(f,c,color, pathCreador);
        t.getGrilla()[f][c] = e;
        EntidadGrafica eg = new EntidadGrafica(f, c, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        return e;
    }
    @Override
    public RalladoV crearRalladoV(int f, int c, Color color, Tablero t) {
        RalladoV e=new RalladoV(f,c,color, pathCreador);
        t.getGrilla()[f][c] = e;
        EntidadGrafica eg = new EntidadGrafica(f, c, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        return e;
    }

}