package com.daw.cardmarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Pedido {
    private int id;
    private String total;
    private String estado;
    private Date fechaCreacion;
    private List<ItemPedido> items;
}
