package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.utils.Consultas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;

@Repository
public class UsuarioRepository {

    private JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean comprobarUsuario(Login login) {
        Login usuarioLogin = jdbcTemplate.queryForObject(String.format(Consultas.COMPROBAR_USUARIO, login.getUsername()), new Login.LoginRowMapper());

        return usuarioLogin != null && usuarioLogin.getUsername().equals(login.getUsername()) && usuarioLogin.getPassword().equals(login.getPassword());
    }

    public Usuario obtenerUsuario(int id) {
        return jdbcTemplate.queryForObject(String.format(Consultas.OBTENER_USUARIO, id), new Usuario.UsuarioRowMapper());
    }

    public boolean crearUsuario(Usuario usuario) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNac = sdf.format(usuario.getFechaNacimiento());

            jdbcTemplate.execute(String.format(Consultas.CREAR_USUARIO,
                    usuario.getNombreUsuario(),
                    usuario.getContrasenna(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    fechaNac
            ));

            return true;
        } catch (Exception e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());

            return false;
        }
    }

    public boolean modificarUsuario(int id, Usuario usuario) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNac = sdf.format(usuario.getFechaNacimiento());

            jdbcTemplate.execute(String.format(Consultas.EDITAR_USUARIO,
                    usuario.getContrasenna(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    fechaNac,
                    id
            ));

            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el usuario: " + e.getMessage());

            return false;
        }
    }

    public boolean eliminarUsuario(int id) {
        try {
            jdbcTemplate.execute(String.format(Consultas.ELIMINAR_USUARIO, id));

            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());

            return false;
        }
    }
}
