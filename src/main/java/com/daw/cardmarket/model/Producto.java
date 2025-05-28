package com.daw.cardmarket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Producto extends DomainEntity {

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @Min(0)
    private double precio;

    @Min(0)
    private int stock;

    @NotBlank
    @Column(length = 1000000)
    private String imagenB64;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")
    private List<Valoracion> valoraciones;
}
