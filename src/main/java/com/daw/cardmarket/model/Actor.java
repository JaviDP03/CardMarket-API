package com.daw.cardmarket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
public abstract class Actor extends DomainEntity {

    @Column(unique=true)
    private String nombreUsuario;

    @NotBlank
    private String contrasenna;

    @NotBlank
    private String nombre;

    private String apellido;

    @Column(unique=true)
    @Email
    private String email;

    private Roles rol;
}
