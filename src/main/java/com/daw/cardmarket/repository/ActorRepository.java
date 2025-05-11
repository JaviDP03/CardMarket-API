package com.daw.cardmarket.repository;

import com.daw.cardmarket.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    public Optional<Actor> findByUsername(String username);
}
