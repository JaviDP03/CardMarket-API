package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.model.Admin;
import com.daw.cardmarket.service.AdminService;
import com.daw.cardmarket.service.AdminService;
import com.daw.cardmarket.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AdminService adminService;

    @Operation(
            summary = "Login de admin",
            description = "Verifica las credenciales del admin y si son válidas, devuelve un JWT"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logueado con éxito"),
            @ApiResponse(responseCode = "401", description = "Error en el login", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if (adminService.comprobarAdmin(login)) {
            return ResponseEntity.ok(jwtUtils.generateToken(login.getUsername()));
        }

        return ResponseEntity.status(401).body("Error en el login");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> obtenerAdmin(@PathVariable int id) {
        Admin admin = adminService.obtenerAdmin(id);

        if (admin != null) {
            return ResponseEntity.ok(admin);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/registro")
    public ResponseEntity<Boolean> crearAdmin(@RequestBody Admin admin) {
        if (adminService.crearAdmin(admin)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @PostMapping("/{id}/editar")
    public ResponseEntity<Boolean> modificarAdmin(@PathVariable int id, @RequestBody Admin admin) {
        if (adminService.modificarAdmin(id, admin)) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarAdmin(@PathVariable int id) {
        if (adminService.eliminarAdmin(id)) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }
}