package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.utils.Consultas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoRepository {

    private JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Producto> listarProductos() {
        try {
            return jdbcTemplate.query(Consultas.LISTAR_PRODUCTOS, new Producto.ProductoRowMapper());
        } catch (Exception e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());

            return null;
        }
    }

    public Producto obtenerProducto(int id) {
        try {
            return jdbcTemplate.queryForObject(Consultas.OBTENER_PRODUCTO, new Producto.ProductoRowMapper(), id);
        } catch (Exception e) {
            System.out.println("Error al obtener el producto: " + e.getMessage());

            return null;
        }
    }

    public boolean crearProducto(Producto producto) {
        try {
            jdbcTemplate.update(Consultas.CREAR_PRODUCTO,
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getStock(),
                    producto.getUrlImagen(),
                    producto.getCategoria().getId());

            return true;
        } catch (Exception e) {
            System.out.println("Error al crear el producto: " + e.getMessage());

            return false;
        }
    }

    public boolean actualizarProducto(int id, Producto producto) {
        try {
            jdbcTemplate.update(Consultas.EDITAR_PRODUCTO,
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getStock(),
                    producto.getUrlImagen(),
                    producto.getCategoria().getId(),
                    id);

            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());

            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        try {
            jdbcTemplate.update(Consultas.ELIMINAR_PRODUCTO, id);

            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());

            return false;
        }
    }

}
