
package entities;

import java.time.LocalDateTime;

public class Usuario extends Base{
    private CredencialAcceso credencial_id;
    private String username;
    private String email;
    private boolean activo;
    private LocalDateTime fechaRegistro;
    private long credencialId;
    

    public Usuario() {
        super();
    }

    public Usuario(int id, String username, String email, boolean activo, LocalDateTime fechaRegistro, long credencialId) {
        super(id,false);
        this.username = username;
        this.email = email;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
        this.credencialId = credencialId;
    }

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

    public CredencialAcceso getCredencial_id() {
        return credencial_id;
    }

    public void setCredencial_id(CredencialAcceso credencial_id) {
        this.credencial_id = credencial_id;
    }

    public long getCredencialId() {
        return credencialId;
    }

    public void setCredencialId(long credencialId) {
        this.credencialId = credencialId;
    }
    

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + ", email=" + email + ", activo=" + activo + ", fechaRegistro=" + fechaRegistro + ", credencial_id=" + credencial_id + '}';
    }

    //ver !!!
//    @Override
//    public boolean equals(Object o){
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Usuario usuario = (Usuario) o;
//        return getId() == usuario.getId();
//    }
//     @Override
//    public int hashCode() {
//        return Objects.hash(getId());
//    }
    
}
