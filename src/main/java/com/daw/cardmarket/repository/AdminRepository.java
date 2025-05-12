package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    public Optional<Admin> findByNombreUsuario(String nombreUsuario);
}
