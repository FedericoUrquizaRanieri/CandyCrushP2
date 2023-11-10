package Juego;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseDeDatos implements Serializable{
    private int puntaje;
    private int puntajeActual;
    private String jugadorActual;
    protected Map<String,Integer> top = new HashMap<String,Integer>(5);

    public BaseDeDatos() {
        this.puntajeActual = 0;
        this.puntaje = 0;
    }

    public void aumentarPuntaje(int puntaje) {
        puntajeActual += puntaje;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

    public void setPuntaje(int i){
        puntajeActual = i;
    }
    public void reiniciarPuntos(){
        puntajeActual=0;
    }
    //Agregar esto en el futuro
    //es para cuando se termina el lvl pasar a puntos y a guaradr el archivo
    //Va en un metodo afuera de esta clase
    /*
    try{
        FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();
    }catch(FileNotFoundException || IOException e){e.printStackTrace();}
     */
    //Agregar esto probablemente en clase juego cuando quiera juntar los puntajes guardados
    /*
    try{
        FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        mibasededatos = (BaseDeDatos) objectInputStream.readObject();
        objectInputStream.close(); 
    }catch(ClassNotFoundException || IOException e){
        e.printStackTrace();
    }
    catch(FileNotFoundException e){}
     */
    //cambiar el file ese por algo en utils que sea el nombre del archivo que vamos a usar para guardar los puntos
}