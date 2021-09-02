package com.alfredo.frutas.datamodel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fruta {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_fruta")
    private Integer id_fruta;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "color")
    private String color;
    @ColumnInfo(name = "cantidad")
    private Integer cantidad;
    @ColumnInfo(name = "imagen")
    private String imagen;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "beneficios")
    private String beneficios;
    @ColumnInfo(name = "vitaminas")
    private String vitaminas;


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
