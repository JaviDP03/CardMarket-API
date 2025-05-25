package com.daw.cardmarket.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

    private Date fechaNacimiento;

    @OneToMany
    private List<Pedido> pedidos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @OneToMany
    private List<Valoracion> valoraciones;
}
