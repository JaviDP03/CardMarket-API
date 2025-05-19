package com.daw.cardmarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ItemPedido extends DomainEntity {

    @ManyToOne
    private Producto producto;

    @Min(0)
    private int cantidad;

    private double total;

    @PrePersist
    @PreUpdate
    private void calcularTotal() {
        this.total = this.producto.getPrecio() * this.cantidad;
    }
}
