package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Admin {
    private int id;
    private String nombreUsuario;
    private String contrasenna;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaCreacion;

    public static class AdminRowMapper implements RowMapper<Admin> {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setId(rs.getInt("id"));
            admin.setNombreUsuario(rs.getString("nombre_usuario"));
            admin.setNombre(rs.getString("nombre"));
            admin.setApellido(rs.getString("apellido"));
            admin.setEmail(rs.getString("email"));
            admin.setFechaCreacion(rs.getDate("fecha_creacion"));

            return admin;
        }
    }
}
