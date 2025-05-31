package com.daw.cardmarket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@Tag(name = "Estado", description = "API para verificar el estado de la aplicación")
public class TestController {

    @Value("${build.time}")
    private String buildTime;

    @Operation(summary = "Verificar estado", description = "Comprueba si la aplicación está en funcionamiento y muestra la fecha de compilación")
    @ApiResponse(responseCode = "200", description = "Aplicación en funcionamiento")
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("<p>RUNNING</p><p>build time: " + buildTime + "</p>");
    }
}
