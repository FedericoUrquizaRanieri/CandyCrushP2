package Entidad;

public interface Matcheable {
    public boolean match(Entidad entidad);
    public boolean matchWith(Caramelo caramelo);
    public boolean matchWith(RalladoH ralladoH);
    public boolean matchWith(RalladoV ralladoV);
    public boolean matchWith(Envuelto envuelto);
    public boolean matchWith(Glaseado glaseado);
    public boolean matchWith(Cruz cruz);
}
