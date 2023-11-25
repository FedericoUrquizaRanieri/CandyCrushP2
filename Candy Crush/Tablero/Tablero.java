package Tablero;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Entidad.*;
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
    protected Queue<Subscriber> misSubscriptores;
    protected Factory miFabrica;

    //Constructor
    public Tablero(Juego juego, BaseDeDatos b){
        miJuego=juego;
        dimension = Utils.dimension;
        grilla = new Entidad[dimension][dimension];
        posJugadorX=0;
        posJugadorY=0;
        condiciones= new ArrayList<>();
        this.misSubscriptores = new LinkedList<>();
        miFabrica = generateFactory();
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
        if(e2.esPosibleIntercambiar(e1)) {
            e1.cambiarPosicionCon(e2, this);
            if (e1.seDestruyeCon(e2)) {
                e1.destruirse(this);
                e2.destruirse(this);
                ordenarColumnas();
            } else if (!chequeoMovimiento(x, y) & !chequeoMovimiento(posJugadorX, posJugadorY)) {
                e1.cambiarPosicionCon(e2, this);
            }
        }
    }
    public void notificarDestruccion(Color color) {
        miJuego.notificarDestruccion(color);
    }

    public void notificarDestruccionGelatina() {
        miJuego.notificarDestruccionGelatina();
    }
    public void notificarDestruccionEnvuelto() {
        miJuego.notificarDestruccionEnvuelto();
    }

    public void notificarDestruccionGlaseado() {
        miJuego.notificarDestruccionGlaseado();
    }
    public void notificarDestruccionBomba() {
        miJuego.notificarDestruccionBomba();
    }
    public void notificarDestruccionCruz() {
        miJuego.notificarDestruccionCruz();
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
                Entidad e = miFabrica.crearCarameloCaida(i,j,colores[(int)(Math.random()*6)],this);
                e.caer(i,j,this);
                e.getEntidadGrafica().notificarCaida(Utils.labelPositionX(j),Utils.labelPositionY(i));
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
        Entidad e1,e2,e3;
        boolean huboCambios = false;
        List<Entidad> verticales = new ArrayList<>();
        List<Entidad> horizontales = new ArrayList<>();

        for (int i = 0; i < dimension-2; i++) {
            e1 = grilla[x][i];
            e2 = grilla[x][i+1];
            e3 = grilla[x][i+2];
            if(e1!=null && e2!=null && e3!=null && e.match(e1) && e.match(e2) && e.match(e3)) {
                if(!verticales.contains(e1)) verticales.add(e1);
                if(!verticales.contains(e2)) verticales.add(e2);
                if(!verticales.contains(e3)) verticales.add(e3);
            }
            e1 = grilla[i][y];
            e2 = grilla[i+1][y];
            e3 = grilla[i+2][y];
            if(e1!=null && e2!=null && e3!=null && e.match(e1) && e.match(e2) && e.match(e3)) {
                if(!horizontales.contains(e1)) horizontales.add(e1);
                if(!horizontales.contains(e2)) horizontales.add(e2);
                if(!horizontales.contains(e3)) horizontales.add(e3);
            }
        }
        int cursor = 0;
        if(!horizontales.isEmpty() && !verticales.isEmpty() && condicionEspeciales()) {
            huboCambios = true;
            for(int i = 0; i<6; i++) {
                if(condicionMatchMas() && (horizontales.size() == 5 && verticales.size() == 5)){ // Caso de un MAS
                    horizontales.get(i).destruirse(this);
                    verticales.get(i).destruirse(this);
                } else if(condicionMatchT() && (horizontales.size() == 5 || verticales.size() == 5)) { // Caso de una T
                    if(horizontales.size() == 5 && verticales.size() == 5) {
                        if(cursor < 3)
                            horizontales.get(cursor).destruirse(this);
                        verticales.get(i).destruirse(this);
                    } else {
                        horizontales.get(i).destruirse(this);
                        verticales.get(i).destruirse(this);
                    }
                    cursor++;
                } else if(condicionMatchL()) { // Caso de una L
                    if(cursor < 3) {
                        horizontales.get(cursor).destruirse(this);
                        verticales.get(cursor).destruirse(this);
                    }
                    cursor++;
                }
            }
            for (int i = 0; i < horizontales.size(); i++)
                if(verticales.contains(horizontales.get(i)))
                    miFabrica.crearEnvuelto(x,y,color,this);
        } else if((!horizontales.isEmpty() || !verticales.isEmpty()) && condicionSimples()){
            huboCambios = true;
            List<Entidad> aRecorrer = verticales.size() > horizontales.size() ? verticales : horizontales;
            for(Entidad entidad : aRecorrer) {
                if(cursor < 5 && condicionMatch5())
                    entidad.destruirse(this);
                else if(cursor < 4 && condicionMatch4())
                    entidad.destruirse(this);
                else if(cursor < 3 && condicionMatch3())
                    entidad.destruirse(this);
                cursor++;
            }
            if(horizontales.size() > 3 && (condicionMatch4() || condicionMatch5()))
                miFabrica.crearRalladoH(x,y,color,this);
            else if(verticales.size() > 3 && (condicionMatch4() || condicionMatch5()))
                miFabrica.crearRalladoV(x,y,color,this);
        }
        if(huboCambios)
            notifySubscribers();
        ordenarColumnas();
        return huboCambios;
    }

    public void ponerCaramelo(int x, int y, Color c){
        miFabrica.crearCaramelo(x, y, c,this);
    }
    
    public void ponerGelatina(int x, int y,Color c){
        miFabrica.crearGelatina(x, y, c, this); 
    }

    public void ponerGlaseado(int x, int y){
        miFabrica.crearGlaseado(x, y, this);
    }

    public void ponerCruz(int x, int y, Color c){
        miFabrica.crearCruz(x, y, c, this);
    }

    public void ponerBomba(int f, int c){
        miFabrica.crearBomba(f,c, this);
    }

    public void aumentarPuntaje(int puntaje) {
        miJuego.aumentarPuntaje(puntaje);
    }

    public void addSubscriber(Subscriber subscriber) {
        misSubscriptores.offer(subscriber);
    }

    public void notifySubscribers() {
        int size = misSubscriptores.size();
        for (int i = 0; i < size; i++)
            misSubscriptores.poll().avisar(this);
    }

    public void terminarNivel(){
        miJuego.terminarNivel();
    }

    public Factory generateFactory() {
        return Utils.skin.equals("Minecraft") ? new MineFactory(miJuego) : new CandyFactory(miJuego);
    }

    public boolean condicionMatch3() {
        return condiciones.get(0);
    }

    public boolean condicionMatch4() {
        return condiciones.get(1);
    }

    public boolean condicionMatch5() {
        return condiciones.get(2);
    }

    public boolean condicionMatchL() {
        return condiciones.get(3);
    }

    public boolean condicionMatchT() {
        return condiciones.get(4);
    }

    public boolean condicionMatchMas() {
        return condiciones.get(5);
    }

    public boolean condicionEspeciales() {
        return (condicionMatchL() || condicionMatchT() || condicionMatchMas());
    }

    public boolean condicionSimples() {
        return (condicionMatch3() || condicionMatch4() || condicionMatch5());
    }
}