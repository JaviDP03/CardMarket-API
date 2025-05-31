package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Pedido;
import com.daw.cardmarket.service.PedidoService;
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
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "API para la gestión de pedidos de usuarios")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Crear pedido", description = "Crea un nuevo pedido para un usuario")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para el pedido")
    })
    @PostMapping()
    public ResponseEntity<Boolean> createPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.createPedido(pedido));
    }

    @Operation(summary = "Actualizar pedido", description = "Actualiza los datos de un pedido existente")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para el pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PostMapping("{id}/editar")
    public ResponseEntity<Boolean> updatePedido(@PathVariable int id, @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.updatePedido(id, pedido));
    }

    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista con todos los pedidos")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pedido.class))))
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @Operation(summary = "Obtener pedido por ID", description = "Devuelve un pedido por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                    content = @Content(schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.getPedidoById(id).orElse(null));
    }

    @Operation(summary = "Obtener pedidos del usuario actual", description = "Devuelve una lista con todos los pedidos del usuario autenticado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pedido.class)))),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @GetMapping("/usuario")
    public ResponseEntity<List<Pedido>> getPedidosByUsuario() {
        return ResponseEntity.ok(pedidoService.getPedidosByUsuario());
    }

    @Operation(summary = "Eliminar pedido", description = "Elimina un pedido por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado"),
            @ApiResponse(responseCode = "403", description = "No tiene permisos para eliminar este pedido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePedido(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.deletePedido(id));
    }
}
