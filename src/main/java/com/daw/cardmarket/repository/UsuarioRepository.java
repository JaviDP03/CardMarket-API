package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.utils.Consultas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
