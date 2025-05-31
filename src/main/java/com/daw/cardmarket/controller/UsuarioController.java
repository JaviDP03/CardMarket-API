package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "API para la gestión de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para el usuario"),
            @ApiResponse(responseCode = "409", description = "El nombre de usuario o email ya existe")
    })
    @PostMapping
    public ResponseEntity<Boolean> createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(usuario));
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para el usuario"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "403", description = "No tiene permisos para actualizar este usuario")
    })
    @PostMapping("/editar")
    public ResponseEntity<Boolean> updateUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista con todos los usuarios registrados")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id).orElse(null));
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "403", description = "No tiene permisos para eliminar este usuario"),
            @ApiResponse(responseCode = "409", description = "No se puede eliminar el usuario porque tiene pedidos asociados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUsuario(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }
}
