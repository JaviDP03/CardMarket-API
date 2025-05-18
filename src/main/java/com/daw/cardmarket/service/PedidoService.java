package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Pedido;
import com.daw.cardmarket.repository.DireccionRepository;
import com.daw.cardmarket.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Transactional
    public boolean createPedido(Pedido pedido) {
        Pedido pedidoC = pedidoRepository.save(pedido);
        direccionRepository.save(pedido.getDireccion());

        return pedidoC.getId() != null;
    }

    @Transactional
    public boolean updatePedido(int id, Pedido pedidoU) {
        Optional<Pedido> pedidoO = pedidoRepository.findById(id);

        if (pedidoO.isPresent()) {
            Pedido pedido = pedidoO.get();

            pedido.setTotal(pedidoU.getTotal());
            pedido.setFechaCreacion(pedidoU.getFechaCreacion());
            pedido.setDireccion(pedidoU.getDireccion());
            pedido.setItems(pedidoU.getItems());

            pedidoRepository.save(pedido);
            direccionRepository.save(pedido.getDireccion());

            return true;
        }

        return false;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(int id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public boolean deletePedido(int id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
