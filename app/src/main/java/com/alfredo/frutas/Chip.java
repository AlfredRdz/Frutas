package com.alfredo.frutas;

public class Chip {
    private String nombre;

    public Chip(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Chip{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
