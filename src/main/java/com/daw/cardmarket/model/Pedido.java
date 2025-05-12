package com.daw.cardmarket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pedido extends DomainEntity {

    @NotBlank
    private double total;

    @NotBlank
    private Date fechaCreacion;

    @ManyToOne
    private Direccion direccion;

    @OneToMany
    private List<ItemPedido> items;
}
