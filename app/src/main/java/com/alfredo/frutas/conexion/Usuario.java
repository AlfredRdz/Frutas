package com.alfredo.frutas.conexion;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    private Integer id_usuario;
    @ColumnInfo(name = "usuario")
    private String usuario;
    @ColumnInfo(name = "contraseña")
    private String contraseña;

//    public Usuario(Integer id_usuario, String usuario, String contraseña) {
//        this.id_usuario = id_usuario;
//        this.usuario = usuario;
//        this.contraseña = contraseña;
//    }
//
//    public Usuario(String usuario, String contraseña) {
//        this.usuario = usuario;
//        this.contraseña = contraseña;
//    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
