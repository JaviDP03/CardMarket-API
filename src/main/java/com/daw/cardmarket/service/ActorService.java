package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Actor;
import com.daw.cardmarket.repository.ActorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ActorService implements UserDetailsService {

    @Autowired
    private ActorRepository actorRepository;

    @Transactional
    public boolean saveActor(Actor actor) {
        Actor actorC = actorRepository.save(actor);

        return actorC.getId() != null;
    }

    public Actor findByUsername(String username) {
        return actorRepository.findByNombreUsuario(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Actor actor = this.findByUsername(username);

        if (actor != null) {
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(actor.getRol().toString()));

            return new User(actor.getNombreUsuario(), actor.getContrasenna(), authorities);
        } else {
            throw new UsernameNotFoundException("Nombre de usuario no encontrado");
        }
    }
}
