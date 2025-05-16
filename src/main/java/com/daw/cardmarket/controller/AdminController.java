package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Admin;
import com.daw.cardmarket.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
