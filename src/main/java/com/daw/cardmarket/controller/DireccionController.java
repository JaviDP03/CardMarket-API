package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Direccion;
import com.daw.cardmarket.service.DireccionService;
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
@RequestMapping("/direcciones")
@Tag(name = "Direcciones", description = "API para la gestión de direcciones de usuarios")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @Operation(summary = "Crear dirección", description = "Crea una nueva dirección para un usuario")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dirección creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para la dirección")
    })
    @PostMapping
    public ResponseEntity<Boolean> createDireccion(@RequestBody Direccion direccion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(direccionService.createDireccion(direccion));
    }

    @Operation(summary = "Actualizar dirección", description = "Actualiza los datos de una dirección existente")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para la dirección"),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @PostMapping("{id}/editar")
    public ResponseEntity<Boolean> updateDireccion(@PathVariable int id, @RequestBody Direccion direccion) {
        return ResponseEntity.ok(direccionService.updateDireccion(id, direccion));
    }

    @Operation(summary = "Obtener todas las direcciones", description = "Devuelve una lista con todas las direcciones")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Lista de direcciones obtenida exitosamente",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Direccion.class))))
    @GetMapping
    public ResponseEntity<List<Direccion>> getAllDirecciones() {
        return ResponseEntity.ok(direccionService.getAllDirecciones());
    }

    @Operation(summary = "Obtener dirección por ID", description = "Devuelve una dirección por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección encontrada",
                    content = @Content(schema = @Schema(implementation = Direccion.class))),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable int id) {
        return ResponseEntity.ok(direccionService.getDireccionById(id).orElse(null));
    }

    @Operation(summary = "Eliminar dirección", description = "Elimina una dirección por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada"),
            @ApiResponse(responseCode = "409", description = "No se puede eliminar la dirección porque está en uso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDireccion(@PathVariable int id) {
        return ResponseEntity.ok(direccionService.deleteDireccion(id));
    }
}
