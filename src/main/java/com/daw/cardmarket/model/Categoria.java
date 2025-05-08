package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

@NoArgsConstructor
@Getter
@Setter
public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;

    public static class CategoriaRowMapper implements RowMapper<Categoria> {
        @Override
        public Categoria mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("id"));
            categoria.setNombre(rs.getString("nombre"));
            categoria.setDescripcion(rs.getString("descripcion"));

            return categoria;
        }
    }
}
