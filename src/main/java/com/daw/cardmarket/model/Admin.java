package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Admin {
    private int id;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Date fechaCreacion;
}
