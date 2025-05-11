package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Actor;
import com.daw.cardmarket.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsername(String username);
}
