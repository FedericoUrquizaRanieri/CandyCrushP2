package Juego;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entidad.Color;
import Nivel.Nivel;
import Tablero.Tablero;

public class GeneradorDeNivel {
    //Atributos
    private List<List<String>> objetivos;
    private static String archivos[]=new String[]{"Archivo1.txt","Archivo2.txt","Archivo3.txt","Archivo4.txt","Archivo5.txt"};
    //Constructor
    public GeneradorDeNivel() {
        objetivos = new ArrayList<>();
        try {
            for (int i = 0; i < archivos.length; i++) {
                File f = new File("Candy Crush/Juego/"+archivos[i]);
                FileReader fr= new FileReader(f);
                char[] data=new char[1000];
                fr.read(data,0,1000);
                ArrayList<String> strings = new ArrayList<>();
                StringBuilder str = new StringBuilder();

                for (char c : data) {
                    if (c == '\n') {
                        strings.add(str.toString().trim());
                        str = new StringBuilder();
                    } else {
                        str.append(c);
                    }
                }
                if (str.length() > 0) {
                    strings.add(str.toString().trim());
                }

                objetivos.add(strings);
                fr.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        //Imprime todo para verlo
        System.out.println(objetivos.toString());
    }
    //Metodos
    public void parseLvl(int nivel, Tablero t, Nivel n){
        generarCaramelos(nivel, t);
        generarNivel(nivel, n);
    }
    //Imprime la grilla inicial ademas de generarlos
    private void generarCaramelos(int n,Tablero t){
        String obj=null;
        int y;
        for(int i=0;i<10;i++){
            obj=objetivos.get(n-1).get(i+11);
            y=0;
            for(int j=0;j<obj.length();j++){
                if(obj.charAt(j)-48>0 && obj.charAt(j)-48<7){
                    t.ponerCaramelo(i,y,retColroes(obj.charAt(j)));
                    y++;
                    System.out.print("C-");
                } else if(obj.charAt(j)-64>0 && obj.charAt(j)-64<7){
                    t.ponerGelatina(i, y, retColroes(obj.charAt(j)-16));
                    y++;
                    System.out.print(obj.charAt(j)+"-");
                } else if(obj.charAt(j)-48==7){
                    t.ponerGlaseado(i,y);
                    y++;
                    System.out.print("G-");
                }
            }
            System.out.println();
        }
    }
    private Color retColroes(int c){
        switch(c-48){
            case 1:
                return Color.VERDE;
            case 2:
                return Color.AMARILLO;
            case 3:
                return Color.ROSA;
            case 4:
                return Color.NARANJA;
            case 5:
                return Color.ROJO;
            case 6:
                return Color.AZUL;
        }
        return Color.NARANJA;
    }
    private void generarNivel(int x, Nivel n){
        n.setMov(Integer.parseInt(objetivos.get(x-1).get(0)));
        n.setTiempo(objetivos.get(x-1).get(1));
        if(Integer.parseInt(objetivos.get(x-1).get(2))!=0)
            n.setObjetivoCaramelo(Integer.parseInt(objetivos.get(x-1).get(2)),Color.VERDE);
        if(Integer.parseInt(objetivos.get(x-1).get(3))!=0)
            n.setObjetivoCaramelo(Integer.parseInt(objetivos.get(x-1).get(3)),Color.AMARILLO);
        if(Integer.parseInt(objetivos.get(x-1).get(4))!=0)
            n.setObjetivoCaramelo(Integer.parseInt(objetivos.get(x-1).get(4)),Color.ROSA);
        if(Integer.parseInt(objetivos.get(x-1).get(5))!=0)
            n.setObjetivoCaramelo(Integer.parseInt(objetivos.get(x-1).get(5)),Color.NARANJA);
        if(Integer.parseInt(objetivos.get(x-1).get(6))!=0)
            n.setObjetivoCaramelo(Integer.parseInt(objetivos.get(x-1).get(6)),Color.ROJO);
        if(Integer.parseInt(objetivos.get(x-1).get(7))!=0)
            n.setObjetivoCaramelo(Integer.parseInt(objetivos.get(x-1).get(7)),Color.AZUL);
        n.setObjetivoGlaseado(Integer.parseInt(objetivos.get(x-1).get(8)));
        n.setObjetivoGelatina(Integer.parseInt(objetivos.get(x-1).get(9)));
        n.setObjetivoEnvuelto(Integer.parseInt(objetivos.get(x-1).get(10)));
    }
}