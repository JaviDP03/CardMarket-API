package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Admin;
import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean comprobarAdmin(Login login) {
        return adminRepository.comprobarAdmin(login);
    }

    public Admin obtenerAdmin(int id) {
        return adminRepository.obtenerAdmin(id);
    }

    public boolean crearAdmin(Admin admin) {
        return adminRepository.crearAdmin(admin);
    }

    public boolean modificarAdmin(int id, Admin admin) {
        return adminRepository.modificarAdmin(id, admin);
    }

    public boolean eliminarAdmin(int id) {
        return adminRepository.eliminarAdmin(id);
    }
}
