package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.service.ProductoService;
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
@RequestMapping("/productos")
@Tag(name = "Productos", description = "API para la gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ValoracionService valoracionService;

    @Operation(summary = "Crear producto", description = "Crea un nuevo producto en el sistema")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para el producto")
    })
    @PostMapping
    public ResponseEntity<Boolean> createProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.createProducto(producto));
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un producto existente")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para el producto"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PostMapping("{id}/editar")
    public ResponseEntity<Boolean> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.updateProducto(id, producto));
    }

    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista con todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Producto.class))))
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable int id) {
        return ResponseEntity.ok(productoService.getProductoById(id).get());
    }

    @Operation(summary = "Obtener productos por categoría", description = "Devuelve una lista de productos filtrados por categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Producto.class)))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> getProductoByCategoria(@PathVariable int idCategoria) {
        return ResponseEntity.ok(productoService.getProductosByCategoria(idCategoria));
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "409", description = "No se puede eliminar el producto porque está en uso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProducto(@PathVariable int id) {
        return ResponseEntity.ok(productoService.deleteProducto(id));
    }
}
