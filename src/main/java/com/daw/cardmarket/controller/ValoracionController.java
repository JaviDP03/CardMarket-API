package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Valoracion;
import com.daw.cardmarket.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @PostMapping
    public ResponseEntity<Boolean> createValoracion(Valoracion valoracion) {
        return ResponseEntity.ok(valoracionService.createValoracion(valoracion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> getValoracionById(@PathVariable int id) {
        return ResponseEntity.ok(valoracionService.getValoracionById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteValoracion(@PathVariable int id) {
        return ResponseEntity.ok(valoracionService.deleteValoracion(id));
    }
}
