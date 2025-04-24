package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

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
    public ResponseEntity<String> login(@RequestBody Login login) {
        if (usuarioService.comprobarUsuario(login)) {
            return ResponseEntity.ok("Logueado con éxito");
        }

        return ResponseEntity.status(401).body("Error en el login");
    }
}

