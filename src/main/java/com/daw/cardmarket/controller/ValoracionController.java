package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.model.Valoracion;
import com.daw.cardmarket.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @PostMapping("/productos/{idProducto}/valoraciones")
    public ResponseEntity<Boolean> createValoracion(@RequestBody Valoracion valoracion, @PathVariable int idProducto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(valoracionService.createValoracion(valoracion, idProducto));
    }

    @GetMapping("/productos/{idProducto}/valoraciones")
    public ResponseEntity<List<Valoracion>> getValoracionesByProducto(@PathVariable int idProducto) {
        return ResponseEntity.ok(valoracionService.getValoracionesByProducto(idProducto));
    }

    @GetMapping("/valoraciones/usuario")
    public ResponseEntity<List<Valoracion>> getValoracionesByUsuario() {
        return ResponseEntity.ok(valoracionService.getValoracionesByUsuario());
    }

    @GetMapping("/valoraciones/{id}")
    public ResponseEntity<Valoracion> getValoracionById(@PathVariable int id) {
        return ResponseEntity.ok(valoracionService.getValoracionById(id).orElse(null));
    }

    @GetMapping("/valoraciones/{id}/usuario")
    public ResponseEntity<Usuario> getUsuarioByValoracion(@PathVariable int id) {
        return ResponseEntity.ok(valoracionService.getUsuarioByValoracion(id).orElse(null));
    }

    @DeleteMapping("/productos/{idProducto}/valoraciones/{id}")
    public ResponseEntity<Boolean> deleteValoracion(@PathVariable int id, @PathVariable int idProducto) {
        return ResponseEntity.ok(valoracionService.deleteValoracion(id, idProducto));
    }
}
