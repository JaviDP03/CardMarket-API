package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Boolean> createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(usuario));
    }

    @PostMapping("/editar")
    public ResponseEntity<Boolean> updateUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUsuario(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }
}
