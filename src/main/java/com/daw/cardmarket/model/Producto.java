package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String urlImagen;
    private Categoria categoria;
    private List<Valoracion> valoraciones;

    public static class ProductoRowMapper implements RowMapper<Producto> {
        @Override
        public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Producto producto = new Producto();
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setStock(rs.getInt("stock"));
            producto.setUrlImagen(rs.getString("url_imagen"));

            return producto;
        }
    }
}
