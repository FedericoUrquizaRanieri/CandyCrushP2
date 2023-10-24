package Entidad;

public interface Matcheable {
    public boolean match(Entidad entidad);
    public boolean match_with(Caramelo caramelo);
    public boolean match_with(RalladoH ralladoH);
    public boolean match_with(RalladoV ralladoV);
    public boolean match_with(Envuelto envuelto);
    public boolean match_with(Glaseado glaseado);
}
