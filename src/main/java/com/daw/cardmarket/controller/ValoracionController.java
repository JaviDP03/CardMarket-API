package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.model.Valoracion;
import com.daw.cardmarket.service.ValoracionService;
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
@Tag(name = "Valoraciones", description = "API para la gestión de valoraciones de productos")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @Operation(summary = "Crear valoración", description = "Crea una nueva valoración para un producto")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Valoración creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para la valoración"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "403", description = "Usuario no autenticado")
    })
    @PostMapping("/productos/{idProducto}/valoraciones")
    public ResponseEntity<Boolean> createValoracion(@RequestBody Valoracion valoracion, @PathVariable int idProducto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(valoracionService.createValoracion(valoracion, idProducto));
    }

    @Operation(summary = "Obtener valoraciones por producto", description = "Devuelve todas las valoraciones de un producto específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de valoraciones obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Valoracion.class)))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/productos/{idProducto}/valoraciones")
    public ResponseEntity<List<Valoracion>> getValoracionesByProducto(@PathVariable int idProducto) {
        return ResponseEntity.ok(valoracionService.getValoracionesByProducto(idProducto));
    }

    @Operation(summary = "Obtener valoraciones del usuario actual", description = "Devuelve todas las valoraciones realizadas por el usuario autenticado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de valoraciones obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Valoracion.class)))),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @GetMapping("/valoraciones/usuario")
    public ResponseEntity<List<Valoracion>> getValoracionesByUsuario() {
        return ResponseEntity.ok(valoracionService.getValoracionesByUsuario());
    }

    @Operation(summary = "Obtener valoración por ID", description = "Devuelve una valoración específica por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valoración encontrada",
                    content = @Content(schema = @Schema(implementation = Valoracion.class))),
            @ApiResponse(responseCode = "404", description = "Valoración no encontrada")
    })
    @GetMapping("/valoraciones/{id}")
    public ResponseEntity<Valoracion> getValoracionById(@PathVariable int id) {
        return ResponseEntity.ok(valoracionService.getValoracionById(id).orElse(null));
    }

    @Operation(summary = "Obtener usuario por valoración", description = "Devuelve el usuario que realizó una valoración específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Valoración o usuario no encontrado")
    })
    @GetMapping("/valoraciones/{id}/usuario")
    public ResponseEntity<Usuario> getUsuarioByValoracion(@PathVariable int id) {
        return ResponseEntity.ok(valoracionService.getUsuarioByValoracion(id).orElse(null));
    }

    @Operation(summary = "Eliminar valoración", description = "Elimina una valoración específica de un producto")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valoración eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Valoración o producto no encontrado"),
            @ApiResponse(responseCode = "403", description = "No tiene permisos para eliminar esta valoración")
    })
    @DeleteMapping("/productos/{idProducto}/valoraciones/{id}")
    public ResponseEntity<Boolean> deleteValoracion(@PathVariable int id, @PathVariable int idProducto) {
        return ResponseEntity.ok(valoracionService.deleteValoracion(id, idProducto));
    }
}
