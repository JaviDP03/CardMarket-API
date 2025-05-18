package com.daw.cardmarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private Direccion direccion;

    @OneToMany
    private List<ItemPedido> items;
}
