package Entidad;

import Tablero.Tablero;

public interface Destruible {
    public void destruirse(Tablero t);
    public boolean se_destruye_con(Entidad entidad);
    public boolean se_destruyen(Glaseado glaseado);
    public boolean se_destruyen(Caramelo caramelo);
    public boolean se_destruyen(RalladoH ralladoH);
    public boolean se_destruyen(RalladoV ralladoV);
    public boolean se_destruyen(Envuelto envuelto);
}
