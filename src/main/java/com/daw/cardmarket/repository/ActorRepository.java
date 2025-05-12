package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    public Actor findByNombreUsuario(String nombreUsuario);
}
