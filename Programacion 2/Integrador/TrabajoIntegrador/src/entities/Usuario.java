
package entities;

import java.time.LocalDateTime;

/*
Representa la entidad "A", el Usuario. Contiene sus datos personales y la referencia 1-a-1 a su credencial
*/
public class Usuario extends Base{
    private String username;
    private String email;
    private boolean activo;
    private LocalDateTime fechaRegistro;
    private CredencialAcceso credencialId;  
    /*
    Constructores
    */
    public Usuario() {
        super();
    }

    public Usuario(long id, String username, String email, boolean activo, LocalDateTime fechaRegistro, CredencialAcceso credencialId) {
        super(id,false);
        this.username = username;
        this.email = email;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
        this.credencialId = credencialId;
    }
    
    /*
    Getters y setters
    */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public CredencialAcceso getCredencialId() {
        return credencialId;
    }

    public void setCredencialId(CredencialAcceso credencialId) {
        this.credencialId = credencialId;
    }

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + ", email=" + email + ", activo=" + activo + ", fechaRegistro=" + fechaRegistro + ", credencial_id=" + credencialId + '}';
    }
}
