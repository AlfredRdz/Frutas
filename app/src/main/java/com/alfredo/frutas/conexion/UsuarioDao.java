package com.alfredo.frutas.conexion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsaurios();

    @Query("SELECT * FROM usuario WHERE usuario LIKE :usuario AND contraseña LIKE :contraseña")
    Usuario login(String usuario, String contraseña);

    @Query("SELECT usuario FROM usuario WHERE usuario = :usuario")
    Usuario comprobar(String usuario);

    @Insert
    void insertUsuario(Usuario... usuario);

    @Delete
    void deleteUsuario(Usuario usuario);
}
