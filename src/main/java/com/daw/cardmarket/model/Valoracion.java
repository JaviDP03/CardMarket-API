package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Valoracion {
    private int id;
    private int puntuacion;
    private String comentario;
    private Date fechaPublicacion;
}
