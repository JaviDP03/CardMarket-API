package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Categoria;
import com.daw.cardmarket.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Boolean> createCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.createCategoria(categoria));
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.updateCategoria(categoria));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable int id) {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategoria(@PathVariable int id) {
        return ResponseEntity.ok(categoriaService.deleteCategoria(id));
    }
}
