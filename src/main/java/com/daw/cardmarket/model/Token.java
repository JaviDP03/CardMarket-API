package com.daw.cardmarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Token {
    private String token;
    private Date fechaExpiracion;
}
