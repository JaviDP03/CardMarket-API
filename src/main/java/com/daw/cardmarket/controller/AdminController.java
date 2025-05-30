package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Admin;
import com.daw.cardmarket.service.AdminService;
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
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Boolean> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
    }

    @PostMapping("/editar")
    public ResponseEntity<Boolean> updateAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.updateAdmin(admin));
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable int id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAdmin(@PathVariable int id) {
        return ResponseEntity.ok(adminService.deleteAdmin(id));
    }

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

    
}
