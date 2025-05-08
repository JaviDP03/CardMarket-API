package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Categoria;
import com.daw.cardmarket.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();

        if (categorias != null && !categorias.isEmpty()) {
            return ResponseEntity.ok(categorias);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable int id) {
        Categoria categoria = categoriaService.obtenerCategoria(id);

        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> crearCategoria(@RequestBody Categoria categoria) {
        if (categoriaService.crearCategoria(categoria)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping("/{id}/editar")
    public ResponseEntity<Boolean> actualizarCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        if (categoriaService.actualizarCategoria(id, categoria)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarCategoria(@PathVariable int id) {
        if (categoriaService.eliminarCategoria(id)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
