package pe.edu.esan.appesan2;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by educacionadistancia on 20/08/2015.
 */
public class Noticias implements Serializable{
    private String titulo;
    private String contenido;
    private String resumen;
    private String enlace;
    private String imagen;
    private Date fecha;

    public void setTitulo(String title) {
        this.titulo = title;
    }

    public void setContenido(String content) {
        this.contenido = content;
    }

    public void setResumen(String text) {
        this.resumen = text;
    }

    public void setEnlace(String url) {
        this.enlace = url;
    }

    public void setImage(String image) {
        this.imagen = image;
    }

    public void setFecha(Date date) {
        this.fecha = date;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getContenido() {
        return this.contenido;
    }

    public String getResumen() {
        return this.resumen;
    }

    public String getEnlace() {
        return this.enlace;
    }

    public String getImagen() {
        return this.imagen;
    }

    public Date getFecha() {
        return this.fecha;
    }
}


