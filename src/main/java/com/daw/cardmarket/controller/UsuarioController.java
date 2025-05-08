package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.service.UsuarioService;
import com.daw.cardmarket.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Login de usuario",
            description = "Verifica las credenciales del usuario y si son válidas, devuelve un JWT"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logueado con éxito"),
            @ApiResponse(responseCode = "401", description = "Error en el login", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if (usuarioService.comprobarUsuario(login)) {
            return ResponseEntity.ok(jwtUtils.generateToken(login.getUsername()));
        }

        return ResponseEntity.status(401).body("Error en el login");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id) {
        Usuario usuario = usuarioService.obtenerUsuario(id);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/registro")
    public ResponseEntity<Boolean> crearUsuario(@RequestBody Usuario usuario) {
        if (usuarioService.crearUsuario(usuario)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @PostMapping("/{id}/editar")
    public ResponseEntity<Boolean> modificarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        if (usuarioService.modificarUsuario(id, usuario)) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarUsuario(@PathVariable int id) {
        if (usuarioService.eliminarUsuario(id)) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }
}