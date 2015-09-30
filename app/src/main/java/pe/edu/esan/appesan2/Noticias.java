package pe.edu.esan.appesan2;

import java.io.Serializable;
import java.util.Date;
/*
    Clase que obtiene y manda datos
    https://github.com/GeekyTheory/Tutorial-Android-Lector-de-noticias
 */

@SuppressWarnings("serial")
public class Noticias implements Serializable {
    //Declaracion de varialbes que se obtienen y envian segun cada articulo
    private String titulo;
    private String contenido;
    private String resumen;
    private String enlace;
    private String imagen;
    private Date fecha;

    public void setTitulo(String title) {
        //Manda el titulo del articulo
        this.titulo = title;
    }

    public void setContenido(String content) {
        //Manda el contenido del articulo
        this.contenido = content;
    }

    public void setResumen(String text) {
        //Manda el resumen del articulo
        this.resumen = text;
    }

    public void setEnlace(String url) {
        //Manda el enlace o url del articulo
        this.enlace = url;
    }

    public void setImage(String image) {
        //Manda la imagen del articulo
        this.imagen = image;
    }

    public void setFecha(Date date) {
        //Manda la fecha de publicacion del articulo
        this.fecha = date;
    }

    public String getTitulo() {
        //Obtiene el titulo del articulo
        return this.titulo;
    }

    public String getContenido() {
        //Obtiene el contenido o descripcion del articulo
        return this.contenido;
    }

    public String getResumen() {
        //Obtiene el resumen del articulo
        return this.resumen;
    }

    public String getEnlace() {
        //Obtiene el enlace o URL del articulo
        return this.enlace;
    }

    public String getImagen() {
        //Obtiene la imagen del articulo
        return this.imagen;
    }

    public Date getFecha() {
        ////Obtiene la fecha de publicacion del articulo
        return this.fecha;
    }
}
