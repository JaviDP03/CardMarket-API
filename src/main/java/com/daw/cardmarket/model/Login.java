package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
@Getter
@Setter
public class Login {
    private String username;
    private String password;

    public static class LoginRowMapper implements RowMapper<Login> {
        @Override
        public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
            Login login = new Login();
            login.setUsername(rs.getString("nombre_usuario"));
            login.setPassword(rs.getString("contrasenna"));

            return login;
        }
    }
}
