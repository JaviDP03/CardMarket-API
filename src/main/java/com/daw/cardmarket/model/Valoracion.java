package com.daw.cardmarket.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Valoracion extends DomainEntity {

    @NotBlank
    private int puntuacion;

    @NotBlank
    private String comentario;

    @NotBlank
    private Date fechaPublicacion;
}
