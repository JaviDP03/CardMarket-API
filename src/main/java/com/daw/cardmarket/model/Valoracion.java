package com.daw.cardmarket.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Valoracion extends DomainEntity {

    @Min(0)
    private int puntuacion;

    @NotBlank
    private String comentario;

    @CreationTimestamp
    private Date fechaPublicacion;
}
