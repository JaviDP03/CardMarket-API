package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Admin;
import com.daw.cardmarket.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/admin")
@Tag(name = "Administradores", description = "API para la gestión de administradores")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Operation(summary = "Crear administrador", description = "Crea un nuevo administrador en el sistema")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administrador creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para el administrador")
    })
    @PostMapping
    public ResponseEntity<Boolean> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
    }

    @Operation(summary = "Actualizar administrador", description = "Actualiza los datos de un administrador existente")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para el administrador"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @PostMapping("/editar")
    public ResponseEntity<Boolean> updateAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.updateAdmin(admin));
    }

    @Operation(summary = "Obtener todos los administradores", description = "Devuelve una lista con todos los administradores")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida exitosamente")
    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @Operation(summary = "Obtener administrador por ID", description = "Devuelve un administrador por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable int id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @Operation(summary = "Eliminar administrador", description = "Elimina un administrador por su identificador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAdmin(@PathVariable int id) {
        return ResponseEntity.ok(adminService.deleteAdmin(id));
    }

    @Operation(summary = "Realizar backup de la base de datos", description = "Genera y descarga un archivo de respaldo de la base de datos")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Backup generado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo generar el archivo de backup"),
            @ApiResponse(responseCode = "500", description = "Error interno al generar el backup")
    })
    @GetMapping("/backupbd")
    public ResponseEntity<Resource> backupDatabase() {
        try {
            File backupFile = adminService.executePostgresqlBackup();

            if (backupFile == null || !backupFile.exists()) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new FileSystemResource(backupFile);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + backupFile.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/sql")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(backupFile.length()))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtener reporte HTML de Dependency-Check", description = "Ejecuta dependency-check y devuelve el HTML generado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte HTML generado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error al generar el reporte")
    })
    @GetMapping("/dependency-check-report")
    public ResponseEntity<Resource> getDependencyCheckReport() {
        try {
            File htmlReport = adminService.generateDependencyCheckHtmlReport();

            if (htmlReport == null || !htmlReport.exists()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            Resource resource = new FileSystemResource(htmlReport);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + htmlReport.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "text/html")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(htmlReport.length()))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
