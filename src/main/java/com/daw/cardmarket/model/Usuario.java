package com.daw.cardmarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario extends Actor {

    @Pattern(regexp = "^([6789]\\d{8})?$")
    private String telefono;

    @NotBlank
    private Date fechaNacimiento;

    @OneToMany
    private List<Pedido> pedidos;

    @OneToMany
    private List<Direccion> direcciones;
}
