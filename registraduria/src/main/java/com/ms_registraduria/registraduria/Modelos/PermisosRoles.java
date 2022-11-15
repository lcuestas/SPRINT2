package com.ms_registraduria.registraduria.Modelos;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
public class PermisosRoles {
    @Id
    private String _id;
    @DBRef
    private Roll roll;
    @DBRef
    private Permisos permisos;

    public PermisosRoles() {
    }

    public String get_id() {
        return _id;
    }

    public Roll getRoll() {
        return roll;
    }
    public Permisos getPermisos() {
        return permisos;
    }
    public void setRoll(Roll roll) {
        this.roll = roll;
    }
    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }
}
