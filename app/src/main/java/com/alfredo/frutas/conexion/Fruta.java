package com.alfredo.frutas.conexion;

public class Fruta {
    private Integer id_fruta;
    private String nombre;
    private String color;
    private Integer cantidad;
    private String imagen;
    private String descripcion;
    private String beneficios;
    private String vitaminas;

    public Fruta(Integer id_fruta, String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas) {
        this.id_fruta = id_fruta;
        this.nombre = nombre;
        this.color = color;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.beneficios = beneficios;
        this.vitaminas = vitaminas;
    }

    public Fruta(Integer id_fruta, String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas) {
        this.id_fruta = id_fruta;
        this.nombre = nombre;
        this.color = color;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.beneficios = beneficios;
        this.vitaminas = vitaminas;
    }

    public Fruta(String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas) {
        this.nombre = nombre;
        this.color = color;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.beneficios = beneficios;
        this.vitaminas = vitaminas;
    }

    public Fruta(String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas) {
        this.nombre = nombre;
        this.color = color;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.beneficios = beneficios;
        this.vitaminas = vitaminas;
    }

    public Integer getId_fruta() {
        return id_fruta;
    }

    public void setId_fruta(Integer id_fruta) {
        this.id_fruta = id_fruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public String getVitaminas() {
        return vitaminas;
    }

    public void setVitaminas(String vitaminas) {
        this.vitaminas = vitaminas;
    }
}
