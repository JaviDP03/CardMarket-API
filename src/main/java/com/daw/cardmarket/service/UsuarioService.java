package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean comprobarUsuario(Login login) {
        return usuarioRepository.comprobarUsuario(login);
    }
}
