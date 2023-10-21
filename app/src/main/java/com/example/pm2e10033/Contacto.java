package com.example.pm2e10033;

public class Contacto {
    private String pais;
    private String nombre;
    private String telefono;
    private String nota;
    private String imagen;

    private long id;

    public Contacto() {
    }

    public Contacto(String nombre, String telefono, String nota, long id) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.nota = nota;
        this.id = id;
    }
    public Contacto(String nombre, String telefono, String nota) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.nota = nota;
    }

    public Contacto(String pais, String nombre, String telefono, String nota, String imagen, int id) {
        this.pais = pais;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nota = nota;
        this.imagen = imagen;
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    @Override
    public String toString() {
        return "id" + id + "Nombre: " + nombre + ", Teléfono: " + telefono + ", Nota: " + nota;
    }
}
