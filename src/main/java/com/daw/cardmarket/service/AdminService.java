package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Admin;
import com.daw.cardmarket.model.Roles;
import com.daw.cardmarket.repository.AdminRepository;
import com.daw.cardmarket.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    public boolean createAdmin(Admin admin) {
        admin.setRol(Roles.ADMIN);
        admin.setContrasenna(passwordEncoder.encode(admin.getContrasenna()));

        Admin adminC = adminRepository.save(admin);

        return adminC.getId() != null;
    }

    @Transactional
    public boolean updateAdmin(Admin adminU) {
        Admin admin = jwtUtils.userLogin();

        if (admin != null) {
            admin.setNombre(adminU.getNombre());
            admin.setApellido(adminU.getApellido());
            admin.setEmail(adminU.getEmail());
            admin.setContrasenna(passwordEncoder.encode(adminU.getContrasenna()));

            adminRepository.save(admin);

            return true;
        }

        return false;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByNombreUsuario(username);
    }

    @Transactional
    public boolean deleteAdmin(int id) {
        Admin admin = jwtUtils.userLogin();

        if (admin != null) {
            adminRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
