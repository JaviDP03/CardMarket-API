package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean comprobarUsuario(Login login) {
        return usuarioRepository.comprobarUsuario(login);
    }

    public Usuario obtenerUsuario(int id) {
        return usuarioRepository.obtenerUsuario(id);
    }

    public boolean crearUsuario(Usuario usuario) {
        return usuarioRepository.crearUsuario(usuario);
    }

    public boolean modificarUsuario(int id, Usuario usuario) {
        return usuarioRepository.modificarUsuario(id, usuario);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioRepository.eliminarUsuario(id);
    }
}
