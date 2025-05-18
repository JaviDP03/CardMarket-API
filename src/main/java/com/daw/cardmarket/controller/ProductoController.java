package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.model.Valoracion;
import com.daw.cardmarket.service.ProductoService;
import com.daw.cardmarket.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ValoracionService valoracionService;

    @PostMapping
    public ResponseEntity<Boolean> createProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.createProducto(producto));
    }

    @PostMapping("{id}/editar")
    public ResponseEntity<Boolean> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.updateProducto(id, producto));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable int id) {
        return ResponseEntity.ok(productoService.getProductoById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProducto(@PathVariable int id) {
        return ResponseEntity.ok(productoService.deleteProducto(id));
    }

    @GetMapping("/{idProducto}/valoraciones")
    public ResponseEntity<List<Valoracion>> getValoracionesByProducto(@PathVariable int idProducto) {
        return ResponseEntity.ok(valoracionService.getValoracionesByProducto(idProducto));
    }
}
