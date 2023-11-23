package Entidad;

import Tablero.Tablero;

public interface Destruible {
    public void destruirse(Tablero t);
    public boolean seDestruyeCon(Entidad entidad);
    public boolean seDestruyen(Glaseado glaseado);
    public boolean seDestruyen(Caramelo caramelo);
    public boolean seDestruyen(RalladoH ralladoH);
    public boolean seDestruyen(RalladoV ralladoV);
    public boolean seDestruyen(Envuelto envuelto);
    public boolean seDestruyen(Cruz cruz);
}
