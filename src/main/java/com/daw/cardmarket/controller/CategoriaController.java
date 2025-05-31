package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Categoria;
import com.daw.cardmarket.service.CategoriaService;
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
@RequestMapping("/categorias")
@Tag(name = "Categorías", description = "API para la gestión de categorías de productos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Crear categoría", description = "Crea una nueva categoría de productos")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para la categoría")
    })
    @PostMapping
    public ResponseEntity<Boolean> createCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.createCategoria(categoria));
    }

    @Operation(summary = "Actualizar categoría", description = "Actualiza los datos de una categoría existente")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para la categoría"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PostMapping("/{id}/editar")
    public ResponseEntity<Boolean> updateCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.updateCategoria(id, categoria));
    }

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista con todas las categorías")
    @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Categoria.class))))
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    @Operation(summary = "Obtener categoría por ID", description = "Devuelve una categoría por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada",
                    content = @Content(schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable int id) {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id).orElse(null));
    }

    @Operation(summary = "Obtener categoría por nombre", description = "Devuelve una categoría por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada",
                    content = @Content(schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Categoria> getCategoriaByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(categoriaService.getCategoriaByNombre(nombre).orElse(null));
    }

    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "409", description = "No se puede eliminar la categoría porque tiene productos asociados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategoria(@PathVariable int id) {
        return ResponseEntity.ok(categoriaService.deleteCategoria(id));
    }
}
