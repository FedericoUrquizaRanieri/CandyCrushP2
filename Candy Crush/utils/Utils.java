package utils;

import Entidad.Color;

public class Utils {
    public static int labelWidth = 60;
    public static int labelHeight = 60;
    public static int labelSpacing = 5;
    public static int dimension = 10;
    public static String skin="Minecraft";

    public static int panelWidth() {
        return dimension * labelWidth + (dimension + 1) * labelSpacing;
    }
    public static int panelHeight() {
        return dimension * labelHeight + (dimension + 1) * labelSpacing;
    }
    public static int labelPositionX(int x) {
        return x * (labelSpacing + labelWidth) + labelSpacing;
    }
    public static int labelPositionY(int y) {
        return y * (labelSpacing + labelHeight) + labelSpacing;
    }
    public static void setSkin(String s){
        skin=s;
    }

    public static int getPuntaje(Color color) {
        int puntaje = 0;
        switch(color){
            case ROJO: {
                puntaje = 5;
                break;
            }
            case VERDE: {
                puntaje = 10;
                break;
            }
            case NARANJA: {
                puntaje = 15;
                break;
            }
            case AMARILLO: {
                puntaje = 20;
                break;
            }
            case AZUL: {
                puntaje = 20;
                break;
            }
            case ROSA: {
                puntaje = 25;
                break;
            }
        }
        return puntaje;
    }
}
