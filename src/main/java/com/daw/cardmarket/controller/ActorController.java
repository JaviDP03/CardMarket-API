package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Actor;
import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.model.Token;
import com.daw.cardmarket.service.ActorService;
import com.daw.cardmarket.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Autenticación", description = "API para la gestión de autenticación de usuarios")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario autenticarse y obtener un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa", content = @Content(schema = @Schema(implementation = Token.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Login login) {
        String username = login.getUsername();
        String password = login.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Token token = jwtUtils.generateToken(authentication);

        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Obtener usuario actual", description = "Devuelve la información del usuario actualmente autenticado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(schema = @Schema(implementation = Actor.class))),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @GetMapping("/userLogin")
    public ResponseEntity<Actor> userLogin() {
        Actor actor = jwtUtils.userLogin();

        if (actor == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(actor);
    }

    @Operation(summary = "Buscar actor por nombre de usuario", description = "Obtiene un actor por su nombre de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actor encontrado"),
            @ApiResponse(responseCode = "404", description = "Actor no encontrado")
    })
    @GetMapping("/actor/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(actorService.findByUsername(username));
    }
}
