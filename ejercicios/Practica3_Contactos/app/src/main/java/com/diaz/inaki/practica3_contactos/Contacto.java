package com.diaz.inaki.practica3_contactos;

/**
 * Created by 8fdi02 on 23/3/17.
 */

public class Contacto {
    private String name;
    private String numero;
    private String aviso;
    private String photoURI;



    public Contacto() {
    }
    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }
}
