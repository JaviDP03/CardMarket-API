package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Direccion;
import com.daw.cardmarket.model.Pedido;
import com.daw.cardmarket.repository.DireccionRepository;
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

    @Transactional
    public boolean createDireccion(Direccion direccion) {
        Direccion direccionC = direccionRepository.save(direccion);

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
            direccion.setEsPrincipal(direccionU.isEsPrincipal());

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

        if (direccionRepository.existsById(id)) {
            List<Pedido> listaPedidos = pedidoService.getAllPedidos();
            Direccion direccion = direccionRepository.findById(id).get();

            for (Pedido pedido : listaPedidos) {
                if (pedido.getDireccion() == direccion) {
                    pedido.setDireccion(null);
                    pedidoService.updatePedido(pedido.getId(), pedido);
                }
            }

            direccionRepository.deleteById(id);
            
            return true;
        }

        return false;
    }
}
