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
        if (findByUsername(usuario.getNombreUsuario()).isPresent() || findByEmail(usuario.getEmail()).isPresent()) {
            return false;
        }

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
            usuario.setImagenB64(usuarioU.getImagenB64());
            usuario.setTelefono(usuarioU.getTelefono());
            usuario.setFechaNacimiento(usuarioU.getFechaNacimiento());
            usuario.setDirecciones(usuarioU.getDirecciones());

            if(usuarioU.getContrasenna() != null && !usuarioU.getContrasenna().equals(usuario.getContrasenna())) {
                usuario.setContrasenna(passwordEncoder.encode(usuarioU.getContrasenna()));
            }

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

        return Optional.ofNullable(usuario);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByNombreUsuario(username);
    }

    public Optional<Usuario> findByEmail(String email) {
        List<Usuario> usuarios = getAllUsuarios();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return Optional.of(usuario);
            }
        }

        return Optional.empty();
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
