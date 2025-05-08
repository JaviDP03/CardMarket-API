package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.service.ProductoService;
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

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();

        if (productos != null && !productos.isEmpty()) {
            return ResponseEntity.ok(productos);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int id) {
        Producto producto = productoService.obtenerProducto(id);

        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> crearProducto(Producto producto) {
        if (productoService.crearProducto(producto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Boolean> actualizarProducto(int id, Producto producto) {
        if (productoService.actualizarProducto(id, producto)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarProducto(int id) {
        if (productoService.eliminarProducto(id)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
