package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Direccion;
import com.daw.cardmarket.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @PostMapping
    public ResponseEntity<Boolean> createDireccion(@RequestBody Direccion direccion) {
        return ResponseEntity.ok(direccionService.createDireccion(direccion));
    }

    @PostMapping("/editar")
    public ResponseEntity<Boolean> updateDireccion(@RequestBody Direccion direccion) {
        return ResponseEntity.ok(direccionService.updateDireccion(direccion));
    }

    @GetMapping
    public ResponseEntity<List<Direccion>> getAllDirecciones() {
        return ResponseEntity.ok(direccionService.getAllDirecciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable int id) {
        return ResponseEntity.ok(direccionService.getDireccionById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDireccion(@PathVariable int id) {
        return ResponseEntity.ok(direccionService.deleteDireccion(id));
    }
}
