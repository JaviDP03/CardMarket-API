package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Direccion {
    private int id;
    private String direccion;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    private String pais;
    private boolean esPrincipal;
}
