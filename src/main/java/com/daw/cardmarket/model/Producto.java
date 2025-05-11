package com.daw.cardmarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

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

    @NotBlank
    private double precio;

    @NotBlank
    private int stock;

    @NotBlank
    @URL
    private String urlImagen;

    @ManyToOne
    private Categoria categoria;

    @OneToMany
    private List<Valoracion> valoraciones;
}
