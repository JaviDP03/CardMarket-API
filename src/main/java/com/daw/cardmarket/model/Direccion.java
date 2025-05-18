package com.daw.cardmarket.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Direccion extends DomainEntity {

    @NotBlank
    private String direccion;

    @NotBlank
    private String codigoPostal;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String provincia;

    @NotBlank
    private String pais;

    private boolean esPrincipal;
}
