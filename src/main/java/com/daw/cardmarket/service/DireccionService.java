package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Direccion;
import com.daw.cardmarket.model.Pedido;
import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.repository.DireccionRepository;
import com.daw.cardmarket.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    public boolean createDireccion(Direccion direccion) {
        Usuario usuario = jwtUtils.userLogin();
        if (usuario == null) {
            return false;
        }

        Direccion direccionC = direccion;

        if (!getAllDirecciones().contains(direccionC)) {
            direccionC = direccionRepository.save(direccion);
        }
        usuario.getDirecciones().add(direccionC);
        usuarioService.updateUsuario(usuario);


        return direccionC.getId() != null;
    }

    @Transactional
    public boolean updateDireccion(int id, Direccion direccionU) {
        Optional<Direccion> direccionO = direccionRepository.findById(id);

        if (direccionO.isPresent()) {
            Direccion direccion = direccionO.get();

            direccion.setDireccion(direccionU.getDireccion());
            direccion.setCodigoPostal(direccionU.getCodigoPostal());
            direccion.setCiudad(direccionU.getCiudad());
            direccion.setProvincia(direccionU.getProvincia());
            direccion.setPais(direccionU.getPais());

            direccionRepository.save(direccion);

            return true;
        }

        return false;
    }

    public List<Direccion> getAllDirecciones() {
        return direccionRepository.findAll();
    }

    public Optional<Direccion> getDireccionById(int id) {
        return direccionRepository.findById(id);
    }

    @Transactional
    public boolean deleteDireccion(int id) {
        Usuario usuario = jwtUtils.userLogin();
        if (usuario == null) {
            return false;
        }

        if (direccionRepository.existsById(id)) {
            boolean estaEnPedido = false;
            List<Pedido> listaPedidos = pedidoService.getAllPedidos();
            Direccion direccion = direccionRepository.findById(id).orElse(null);

            for (Pedido pedido : listaPedidos) {
                if (pedido.getDireccion() == direccion) {
                    estaEnPedido = true;
                    break;
                }
            }

            usuario.getDirecciones().remove(direccion);
            usuarioService.updateUsuario(usuario);
            if (!estaEnPedido) {
                direccionRepository.deleteById(id);
            }

            return true;
        }

        return false;
    }
}
