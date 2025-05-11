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
public class Categoria extends DomainEntity {

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;
}
