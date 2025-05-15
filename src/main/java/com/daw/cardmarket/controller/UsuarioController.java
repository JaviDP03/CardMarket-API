package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Boolean> createUsuario(Usuario usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }

    @PostMapping("/editar")
    public ResponseEntity<Boolean> updateUsuario(Usuario usuario) {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(int id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUsuario(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }
}
