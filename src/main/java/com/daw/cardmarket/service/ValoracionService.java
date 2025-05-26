package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.model.Valoracion;
import com.daw.cardmarket.repository.ValoracionRepository;
import com.daw.cardmarket.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    public boolean createValoracion(Valoracion valoracion, int idProducto) {
        Usuario usuario = jwtUtils.userLogin();
        Valoracion valoracionC = valoracionRepository.save(valoracion);
        Producto producto = productoService.getProductoById(idProducto).orElse(null);

        if (producto != null) {
            producto.getValoraciones().add(valoracionC);
            productoService.updateProducto(idProducto, producto);
        }

        if (usuario != null) {
            usuario.getValoraciones().add(valoracionC);
            usuarioService.updateUsuario(usuario);
        }

        return valoracionC.getId() != null;
    }

    public List<Valoracion> getValoracionesByProducto(int idProducto) {
        return valoracionRepository.findAll();
    }

    public List<Valoracion> getValoracionesByUsuario() {
        List<Valoracion> valoraciones = valoracionRepository.findAll();
        List<Valoracion> valoracionesUsuario = new ArrayList<>();
        Usuario usuario = jwtUtils.userLogin();

        for (Valoracion valoracion : valoraciones) {
            if (usuario.getValoraciones().contains(valoracion)) {
                valoracionesUsuario.add(valoracion);
            }
        }

        return valoracionesUsuario;
    }

    public Optional<Usuario> getUsuarioByValoracion(int id) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        Valoracion valoracion = getValoracionById(id).orElse(null);
        for (Usuario usuario : usuarios) {
            if (usuario.getValoraciones().contains(valoracion)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public Optional<Valoracion> getValoracionById(int id) {
        return valoracionRepository.findById(id);
    }

    @Transactional
    public boolean deleteValoracion(int id, int idProducto) {
        if (valoracionRepository.existsById(id)) {
            Producto producto = productoService.getProductoById(idProducto).orElse(null);

            if (producto != null) {
                producto.getValoraciones().remove(getValoracionById(id).orElse(null));
                productoService.updateProducto(idProducto, producto);
            }

            valoracionRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
