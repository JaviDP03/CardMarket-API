package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasenna;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Date fechaNacimiento;
    private List<Pedido> pedidos;
    private List<Direccion> direcciones;

    public static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNombreUsuario(rs.getString("nombre_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setFechaNacimiento(rs.getDate("fecha_nac"));

            return usuario;
        }
    }
}
