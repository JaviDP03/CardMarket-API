package com.daw.cardmarket.utils;

public class Consultas {
    // Login
    public static final String COMPROBAR_USUARIO = "SELECT nombre_usuario, contrasenna FROM usuarios WHERE nombre_usuario = '%1$s'";
    public static final String COMPROBAR_ADMIN = "SELECT nombre_usuario, contrasenna FROM administradores WHERE nombre_usuario = '%1$s'";

    // Usuario
    public static final String OBTENER_USUARIO = "SELECT id, nombre_usuario, nombre, apellido, email, telefono, fecha_nac FROM usuarios WHERE id = %1$d";
    public static final String CREAR_USUARIO = "INSERT INTO usuarios (nombre_usuario, contrasenna, nombre, apellido, email, telefono, fecha_nac)" +
            "VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s')";
    public static final String EDITAR_USUARIO = "UPDATE usuarios SET contrasenna = '%1$s', nombre = '%2$s', apellido = '%3$s', email = '%4$s', telefono = '%5$s'," +
            "fecha_nac = '%6$s' WHERE id = %7$d";
    public static final String ELIMINAR_USUARIO = "DELETE FROM usuarios WHERE id = %1$d";

    // Admin
    public static final String OBTENER_ADMIN = "SELECT id, nombre_usuario, nombre, apellido, email, fecha_creacion FROM administradores WHERE id = %1$d";
    public static final String CREAR_ADMIN = "INSERT INTO administradores (nombre_usuario, contrasenna, nombre, apellido, email)" +
            "VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s')";
    public static final String EDITAR_ADMIN = "UPDATE administradores SET contrasenna = '%1$s', nombre = '%2$s', apellido = '%3$s', email = '%4$s' WHERE id = %5$d";
    public static final String ELIMINAR_ADMIN = "DELETE FROM administradores WHERE id = %1$d";

    // Producto
    public static final String LISTAR_PRODUCTOS = "SELECT id, nombre, descripcion, precio, stock, url_imagen, id_categoria FROM productos";
    public static final String OBTENER_PRODUCTO = "SELECT id, nombre, descripcion, precio, stock, url_imagen, id_categoria FROM productos WHERE id = %1$d";
    public static final String CREAR_PRODUCTO = "INSERT INTO productos (nombre, descripcion, precio, stock, url_imagen, id_categoria)" +
            "VALUES ('%1$s', '%2$s', %3$f, %4$d, '%5$s', %6$d)";
    public static final String EDITAR_PRODUCTO = "UPDATE productos SET nombre = '%1$s', descripcion = '%2$s', precio = %3$f, stock = %4$d, url_imagen = '%5$s'," +
            "id_categoria = %6$d WHERE id = %6$d";
    public static final String ELIMINAR_PRODUCTO = "DELETE FROM productos WHERE id = %1$d";

    // Categoria
    public static final String LISTAR_CATEGORIAS = "SELECT id, nombre, descripcion FROM categorias";
    public static final String OBTENER_CATEGORIA = "SELECT id, nombre, descripcion FROM categorias WHERE id = %1$d";
    public static final String CREAR_CATEGORIA = "INSERT INTO categorias (nombre, descripcion) VALUES ('%1$s', '%2$s')";
    public static final String EDITAR_CATEGORIA = "UPDATE categorias SET nombre = '%1$s', descripcion = '%2$s' WHERE id = %3$d";
    public static final String ELIMINAR_CATEGORIA = "DELETE FROM categorias WHERE id = %1$d";
}
