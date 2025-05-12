package com.daw.cardmarket.service;

import com.daw.cardmarket.model.ItemPedido;
import com.daw.cardmarket.repository.ItemPedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public boolean createItemPedido(ItemPedido itemPedido) {
        ItemPedido itemPedidoC = itemPedidoRepository.save(itemPedido);

        return itemPedidoC.getId() != null;
    }

    @Transactional
    public boolean updateItemPedido(ItemPedido itemPedido) {
        Optional<ItemPedido> itemPedidoOptional = itemPedidoRepository.findById(itemPedido.getId());

        if (itemPedidoOptional.isPresent()) {
            ItemPedido itemPedidoC = itemPedidoOptional.get();

            itemPedido.setNombre(itemPedidoC.getNombre());
            itemPedido.setCantidad(itemPedidoC.getCantidad());
            itemPedido.setPrecioUnitario(itemPedidoC.getPrecioUnitario());

            itemPedidoRepository.save(itemPedido);

            return true;
        }

        return false;
    }

    public List<ItemPedido> getAllItemPedidos() {
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedido> getItemPedidoById(int id) {
        return itemPedidoRepository.findById(id);
    }

    @Transactional
    public boolean deleteItemPedido(int id) {
        if (itemPedidoRepository.existsById(id)) {
            itemPedidoRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
