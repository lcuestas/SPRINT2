package com.ms_registraduria.registraduria.Modelos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document()
public class Permisos {
    @Id
    private String _id;
    private String URL;
    private String metodo;

    public Permisos(String URL, String metodo) {
        this.URL = URL;
        this.metodo = metodo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}