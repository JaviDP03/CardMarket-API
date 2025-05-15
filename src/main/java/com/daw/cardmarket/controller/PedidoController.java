package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Pedido;
import com.daw.cardmarket.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<Boolean> createPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.createPedido(pedido));
    }

    @PostMapping("/editar")
    public ResponseEntity<Boolean> updatePedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.updatePedido(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.getPedidoById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePedido(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.deletePedido(id));
    }
}
