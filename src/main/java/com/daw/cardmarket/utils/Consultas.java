package com.daw.cardmarket.utils;

public class Consultas {
    // Login
    public static final String COMPROBAR_USUARIO = "SELECT nombre_usuario, contrasenna FROM usuarios WHERE nombre_usuario = '%1$s'";

    // Usuarios
    public static final String OBTENER_USUARIO = "SELECT id, nombre_usuario, nombre, apellido, email, telefono, fecha_nac FROM usuarios WHERE id = %1$d";
    public static final String CREAR_USUARIO = "INSERT INTO usuarios (nombre_usuario, contrasenna, nombre, apellido, email, telefono, fecha_nac)" +
            "VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s')";
    public static final String EDITAR_USUARIO = "UPDATE usuarios SET contrasenna = '%1$s', nombre = '%2$s', apellido = '%3$s', email = '%4$s', telefono = '%5$s'," +
            "fecha_nac = '%6$s' WHERE id = %7$d";
    public static final String ELIMINAR_USUARIO = "DELETE FROM usuarios WHERE id = %1$d";
}
