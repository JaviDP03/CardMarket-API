package com.daw.cardmarket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pedido extends DomainEntity {

    @Min(0)
    private double total;

    @CreationTimestamp
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedido> items;
}
