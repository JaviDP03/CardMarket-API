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
public class ItemPedido extends DomainEntity {

    @NotBlank
    private String nombre;

    @NotBlank
    private int cantidad;

    @NotBlank
    private double precioUnitario;
}
