package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Admin;
import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.utils.Consultas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;

@Repository
public class AdminRepository {

    private JdbcTemplate jdbcTemplate;

    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean comprobarAdmin(Login login) {
        Login adminLogin = jdbcTemplate.queryForObject(String.format(Consultas.COMPROBAR_ADMIN, login.getUsername()), new Login.LoginRowMapper());

        return adminLogin != null && adminLogin.getUsername().equals(login.getUsername()) && adminLogin.getPassword().equals(login.getPassword());
    }

    public Admin obtenerAdmin(int id) {
        return jdbcTemplate.queryForObject(String.format(Consultas.OBTENER_ADMIN, id), new Admin.AdminRowMapper());
    }

    public boolean crearAdmin(Admin admin) {
        try {
            jdbcTemplate.execute(String.format(Consultas.CREAR_ADMIN,
                    admin.getNombreUsuario(),
                    admin.getContrasenna(),
                    admin.getNombre(),
                    admin.getApellido(),
                    admin.getEmail()
            ));

            return true;
        } catch (Exception e) {
            System.out.println("Error al crear el admin: " + e.getMessage());

            return false;
        }
    }

    public boolean modificarAdmin(int id, Admin admin) {
        try {
            jdbcTemplate.execute(String.format(Consultas.EDITAR_ADMIN,
                    admin.getContrasenna(),
                    admin.getNombre(),
                    admin.getApellido(),
                    admin.getEmail(),
                    id
            ));

            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el admin: " + e.getMessage());

            return false;
        }
    }

    public boolean eliminarAdmin(int id) {
        try {
            jdbcTemplate.execute(String.format(Consultas.ELIMINAR_ADMIN, id));

            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el admin: " + e.getMessage());

            return false;
        }
    }
}
