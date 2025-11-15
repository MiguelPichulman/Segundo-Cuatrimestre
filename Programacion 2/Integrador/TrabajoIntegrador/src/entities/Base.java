package entities;


/*
Clase abstracta de la que heredan otras entidades. Centraliza los campos comunes
*/
public abstract class Base {
    private long id;
    private boolean eliminado; //baja logica
    
    /*
    Constructores
    */
    protected Base() {
        this.eliminado= false;
    }

    public Base(long id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }
    
    /*
    Getters y setters
    */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }  
}