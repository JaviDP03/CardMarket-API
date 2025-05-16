package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.ItemPedido;
import com.daw.cardmarket.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping
    public ResponseEntity<Boolean> createItemPedido(@RequestBody com.daw.cardmarket.model.ItemPedido itemPedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPedidoService.createItemPedido(itemPedido));
    }

    @PostMapping("/editar")
    public ResponseEntity<Boolean> updateItemPedido(@RequestBody ItemPedido itemPedido) {
        return ResponseEntity.ok(itemPedidoService.updateItemPedido(itemPedido));
    }

    @GetMapping
    public ResponseEntity<List<ItemPedido>> getAllItemPedidos() {
        return ResponseEntity.ok(itemPedidoService.getAllItemPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable int id) {
        return ResponseEntity.ok(itemPedidoService.getItemPedidoById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteItemPedido(@PathVariable int id) {
        return ResponseEntity.ok(itemPedidoService.deleteItemPedido(id));
    }
}
