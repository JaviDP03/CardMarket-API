package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.model.Roles;
import com.daw.cardmarket.repository.UsuarioRepository;
import com.daw.cardmarket.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    public boolean createUsuario(Usuario usuario) {
        usuario.setRol(Roles.USUARIO);
        usuario.setContrasenna(passwordEncoder.encode(usuario.getContrasenna()));

        Usuario usuarioC = usuarioRepository.save(usuario);

        return usuarioC.getId() != null;
    }

    @Transactional
    public boolean updateUsuario(Usuario usuarioU) {
        Usuario usuario = jwtUtils.userLogin();

        if (usuario != null) {
            usuario.setNombre(usuarioU.getNombre());
            usuario.setApellido(usuarioU.getApellido());
            usuario.setEmail(usuarioU.getEmail());
            usuario.setContrasenna(passwordEncoder.encode(usuarioU.getContrasenna()));
            usuario.setTelefono(usuarioU.getTelefono());
            usuario.setFechaNacimiento(usuarioU.getFechaNacimiento());
            usuario.setDirecciones(usuarioU.getDirecciones());

            usuarioRepository.save(usuario);

            return true;
        }

        return false;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            usuario.setContrasenna(null);
        }

        return Optional.ofNullable(usuario);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByNombreUsuario(username);
    }

    @Transactional
    public boolean deleteUsuario(int id) {
        Usuario usuario = jwtUtils.userLogin();

        if (usuario != null) {
            usuarioRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
