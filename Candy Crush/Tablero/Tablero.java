package Tablero;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Entidad.*;
import GUI.EntidadGrafica;
import Juego.*;
import utils.Utils;

public class Tablero{

    //Atributos
    protected int posJugadorX;
    protected int posJugadorY;
    protected Entidad grilla[][];
    protected Juego miJuego;
    protected int dimension;
    private final Color[] colores = {Color.AZUL, Color.AMARILLO, Color.ROJO, Color.NARANJA, Color.ROSA, Color.VERDE};
    protected List<Boolean> condiciones;
    protected BaseDeDatos miBaseDeDatos;
    protected Queue<Subscriber> misSubscriptores;

    //Constructor
    public Tablero(Juego j){
        miJuego=j;
        dimension = Utils.dimension;
        grilla = new Entidad[dimension][dimension];
        posJugadorX=0;
        posJugadorY=0;
        condiciones= new ArrayList<>();
        this.miBaseDeDatos = new BaseDeDatos();
        this.misSubscriptores = new LinkedList<>();
    }

    //Metodos
    public void setDimension(int n){
        dimension = n;
    }

    public int getDimension(){
        return dimension;
    }
    public Entidad[][] getGrilla(){
        return grilla;
    }

    public Juego getJuego() {
        return miJuego;
    }

    public List<Boolean> getCondiciones(){
        return condiciones;
    }

    public boolean setPosJugadorX(int n){
        if(n >= 0 && n < dimension) {
            posJugadorX = n;
            return true;
        }
        return false;
    }

    public boolean setPosJugadorY(int n){
        if(n >= 0 && n < dimension) {
            posJugadorY = n;
            return true;
        }
        return false;
    }

    public int getPosJugadorX(){
        return posJugadorX;
    }

    public int getPosJugadorY(){
        return posJugadorY;
    }

    public void swap(int x, int y) {
        Entidad e1 = grilla[x][y];
        Entidad e2 = grilla[posJugadorX][posJugadorY];
        for (Boolean b : condiciones)
            System.out.println(b);
        if(e2.es_posible_intercambiar(e1)) {
            e1.cambiarPosicionCon(e2, this);
            e1 = grilla[x][y];
            e2 = grilla[posJugadorX][posJugadorY];
            mostrarGrilla();
            if (e1.se_destruye_con(e2)) {
                e1.destruirse(this);
                e2.destruirse(this);
                ordenarColumnas();
                mostrarGrilla();
            } else if ((chequeoMovimiento(x, y) | chequeoMovimiento(posJugadorX, posJugadorY))) {
                mostrarGrilla();
            } else {
                e1.cambiarPosicionCon(e2, this);
                mostrarGrilla();
            }
        }
    }
    //Estos metodos tienen el sout agregado para ver cuando y que rompes
    public void notificarDestruccion(Color color) {
        miJuego.notificarDestruccion(color);
        System.out.println("Rompi un caramelo");
    }

    public void notificarDestruccionGelatina() {
        miJuego.notificarDestruccionGelatina();
        System.out.println("Rompi una gelatina");
    }
    public void notificarDestruccionEnvuelto() {
        miJuego.notificarDestruccionEnvuelto();
        System.out.println("Rompi un envuelto");
    }

    public void notificarDestruccionGlaseado() {
        miJuego.notificarDestruccionGlaseado();
        System.out.println("Rompi un glaseado");
    }

    //Metodo para imprimir logica por consola(test)
    public void mostrarGrilla() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(getEntidad(i,j) != null)
                    System.out.print(getEntidad(i,j).getColor().toString().substring(0,3).toUpperCase() + " - ");
                else
                    System.out.print("NUL - ");
            }
            System.out.println('\n');
        }
        System.out.println("-------------------------------------------------------");
    }

    public boolean chequeoMovimiento(int fila, int columna){
        return checkCombinaciones(fila, columna);
    }

    public void ordenarColumnas() {
        for(int j = 0; j < dimension; j++) {
            int idx = dimension - 1;
            for(int i = dimension - 1; i >= 0; i--) {
                if(!grilla[i][j].estaDestruida()) {
                    if(idx != i) {
                        grilla[i][j].caer(idx,j,this);
                    }
                    idx--;
                }
            }
            for (int i = idx; i >= 0; i--) {
                Entidad e = new Caramelo(i, j, colores[(int) (Math.random() * 6)]);
                EntidadGrafica eg = new EntidadGrafica(-1,j, e, miJuego.getMiGUI().getPanel());
                e.setEntidadGrafica(eg);
                e.caer(i,j,this);
                miJuego.asociar_entidad_grafica(eg);
                eg.notificarCaida(Utils.labelPositionX(j),Utils.labelPositionY(i));
            }
        }
    }

    public Entidad getEntidad(int f, int c){
        Entidad toReturn = null;
        if(f >= 0 && f<dimension && c >= 0 && c < dimension){
            toReturn = grilla[f][c];
        }
        return toReturn;
    }

    public boolean checkCombinaciones(int x, int y) {
        if(grilla[x][y] == null) return false;
        Entidad e = grilla[x][y];
        Color color = e.getColor();
        Entidad e1,e2,e3,especialCreado=null;
        boolean huboCambios = false;
        List<Entidad> verticales = new ArrayList<>();
        List<Entidad> horizontales = new ArrayList<>();

        for (int i = 0; i < dimension-2; i++) {
            e1 = grilla[x][i];
            e2 = grilla[x][i+1];
            e3 = grilla[x][i+2];
            if(e1!=null && e2!=null && e3!=null && e.match(e1) && e.match(e2) && e.match(e3)) {
                verticales.add(e1);
                verticales.add(e2);
                verticales.add(e3);
            }
            e1 = grilla[i][y];
            e2 = grilla[i+1][y];
            e3 = grilla[i+2][y];
            if(e1!=null && e2!=null && e3!=null && e.match(e1) && e.match(e2) && e.match(e3)) {
                horizontales.add(e1);
                horizontales.add(e2);
                horizontales.add(e3);
            }
        }
        int cursor = 0;
        if(!horizontales.isEmpty() && !verticales.isEmpty() && (condiciones.get(3) || condiciones.get(4) || condiciones.get(5))) {
            huboCambios = true;
            for(int i = 0; i<6; i++) {
                if(condiciones.get(5) && (horizontales.size() == 5 && verticales.size() == 5)){ // Caso de un MAS
                    horizontales.get(i).destruirse(this);
                    verticales.get(i).destruirse(this);
                } else if(condiciones.get(4) && (horizontales.size() == 5 || verticales.size() == 5)) { // Caso de una T
                    if(horizontales.size() == 5 && verticales.size() == 5) {
                        if(cursor < 3)
                            horizontales.get(cursor).destruirse(this);
                        verticales.get(i).destruirse(this);
                    } else {
                        horizontales.get(i).destruirse(this);
                        verticales.get(i).destruirse(this);
                    }
                    cursor++;
                } else if(condiciones.get(3)) { // Caso de una L
                    if(cursor < 3) {
                        horizontales.get(cursor).destruirse(this);
                        verticales.get(cursor).destruirse(this);
                    }
                    cursor++;
                }
            }
            for (int i = 0; i < horizontales.size(); i++)
                if(verticales.contains(horizontales.get(i)))
                    especialCreado = grilla[x][y] = new Envuelto(x, y, color);
        } else if((!horizontales.isEmpty() || !verticales.isEmpty()) && (condiciones.get(0) || condiciones.get(1) || condiciones.get(2))){
            huboCambios = true;
            List<Entidad> aRecorrer = verticales.size() > horizontales.size() ? verticales : horizontales;
            for(Entidad entidad : aRecorrer) {
                if(cursor < 5 && condiciones.get(2))
                    entidad.destruirse(this);
                else if(cursor < 4 && condiciones.get(1))
                    entidad.destruirse(this);
                else if(cursor < 3 && condiciones.get(0))
                    entidad.destruirse(this);
                cursor++;
            }
            if(horizontales.size() > 3 && (condiciones.get(1) || condiciones.get(2)))
                especialCreado = grilla[x][y] = new RalladoH(x,y,color);
            else if(verticales.size() > 3 && (condiciones.get(1) || condiciones.get(2)))
                especialCreado = grilla[x][y] = new RalladoV(x,y,color);
        }
        if(especialCreado != null) {
            EntidadGrafica eg = new EntidadGrafica(especialCreado.getFila(),especialCreado.getColumna(),especialCreado,miJuego.getMiGUI().getPanel());
            especialCreado.setEntidadGrafica(eg);
            miJuego.asociar_entidad_grafica(eg);
        }
        if(huboCambios)
            notifySubscribers();
        ordenarColumnas();
        return huboCambios;
    }

    public void ponerCaramelo(int x, int y, Color c){
        Caramelo e=new Caramelo(x,y,c);
        grilla[x][y] = e;
        EntidadGrafica eg = new EntidadGrafica(x, y, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
    }
    public void ponerGelatina(int x, int y,Color c){
        Gelatina g= new Gelatina(x,y,c);
        grilla[x][y]=g;
        EntidadGrafica eg = new EntidadGrafica(x, y, g.getCaramelo(), miJuego.getMiGUI().getPanel());
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        EntidadGrafica eg1 = new EntidadGrafica(x, y, g, miJuego.getMiGUI().getPanel());
        g.setEntidadGrafica(eg1);
        g.getCaramelo().setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg1);
        miJuego.getMiGUI().getPanel().setLayer(eg1, -1);
        
    }
    public void ponerGlaseado(int x, int y){
        Glaseado e=new Glaseado(x,y);
        grilla[x][y] = e;
        EntidadGrafica eg = new EntidadGrafica(x, y, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
        addSubscriber(e);
    }
    public void ponerCruz(int x, int y, Color c){
        Cruz g= new Cruz(x,y,c);
        grilla[x][y]=g;
        EntidadGrafica eg1 = new EntidadGrafica(x, y, g, miJuego.getMiGUI().getPanel());
        g.setEntidadGrafica(eg1);
        miJuego.getMiGUI().insertarEntidadGrafica(eg1);
    }
    public void ponerBomba(int x, int y){
        Bomba e=new Bomba(x,y);
        grilla[x][y] = e;
        EntidadGrafica eg = new EntidadGrafica(x, y, e, miJuego.getMiGUI().getPanel());
        e.setEntidadGrafica(eg);
        miJuego.getMiGUI().insertarEntidadGrafica(eg);
    }
    public void aumentarPuntaje(int puntaje) {
        miBaseDeDatos.aumentarPuntaje(puntaje);
    }

    public void addSubscriber(Subscriber subscriber) {
        misSubscriptores.offer(subscriber);
    }

    public void notifySubscribers() {
        int size = misSubscriptores.size();
        for (int i = 0; i < size; i++)
            misSubscriptores.poll().avisar(this);
    }
}