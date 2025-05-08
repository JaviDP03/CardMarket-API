package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Categoria;
import com.daw.cardmarket.utils.Consultas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaRepository {

    private JdbcTemplate jdbcTemplate;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Categoria> listarCategorias() {
        try {
            return jdbcTemplate.query(Consultas.LISTAR_CATEGORIAS, new Categoria.CategoriaRowMapper());
        } catch (Exception e) {
            System.out.println("Error al obtener las categorias: " + e.getMessage());

            return null;
        }
    }

    public Categoria obtenerCategoria(int id) {
        try {
            return jdbcTemplate.queryForObject(Consultas.OBTENER_CATEGORIA, new Categoria.CategoriaRowMapper(), id);
        } catch (Exception e) {
            System.out.println("Error al obtener la categoria: " + e.getMessage());

            return null;
        }
    }

    public boolean crearCategoria(Categoria categoria) {
        try {
            jdbcTemplate.update(Consultas.CREAR_CATEGORIA,
                    categoria.getNombre(),
                    categoria.getDescripcion());

            return true;
        } catch (Exception e) {
            System.out.println("Error al crear la categoria: " + e.getMessage());

            return false;
        }
    }

    public boolean actualizarCategoria(int id, Categoria categoria) {
        try {
            jdbcTemplate.update(Consultas.EDITAR_CATEGORIA,
                    categoria.getNombre(),
                    categoria.getDescripcion(),
                    id);

            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar la categoria: " + e.getMessage());

            return false;
        }
    }

    public boolean eliminarCategoria(int id) {
        try {
            jdbcTemplate.update(Consultas.ELIMINAR_CATEGORIA, id);

            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar la categoria: " + e.getMessage());

            return false;
        }
    }
}
