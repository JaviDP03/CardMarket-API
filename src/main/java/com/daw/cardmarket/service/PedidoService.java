package com.daw.cardmarket.service;

import com.daw.cardmarket.model.ItemPedido;
import com.daw.cardmarket.model.Pedido;
import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.model.Usuario;
import com.daw.cardmarket.repository.DireccionRepository;
import com.daw.cardmarket.repository.ItemPedidoRepository;
import com.daw.cardmarket.repository.PedidoRepository;
import com.daw.cardmarket.utils.JwtUtils;
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

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    public boolean createPedido(Pedido pedido) {
        Usuario usuario = jwtUtils.userLogin();
        List<ItemPedido> listaItems = pedido.getItems();
        Producto tempProducto;

        if (usuario == null) {
            return false;
        }

        for (ItemPedido itemPedido : listaItems) {
            tempProducto = itemPedido.getProducto();
            tempProducto.setStock(tempProducto.getStock() - itemPedido.getCantidad());
            if (tempProducto.getStock() < 0) {
                return false;
            }

            productoService.updateProducto(tempProducto.getId(), tempProducto);
        }

        Pedido pedidoC = pedidoRepository.save(pedido);
        direccionRepository.save(pedidoC.getDireccion());
        usuario.getPedidos().add(pedidoC);
        usuarioService.updateUsuario(usuario);

        return pedidoC.getId() != null;
    }

    @Transactional
    public boolean updatePedido(int id, Pedido pedidoU) {
        Optional<Pedido> pedidoO = pedidoRepository.findById(id);

        if (pedidoO.isPresent()) {
            Pedido pedido = pedidoO.get();

            pedido.setTotal(pedidoU.getTotal());
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

    public List<Pedido> getPedidosByUsuario() {
        Usuario usuario = jwtUtils.userLogin();

        if (usuario == null) {
            return null;
        }

        return usuario.getPedidos();
    }

    @Transactional
    public boolean deletePedido(int id) {
        if (pedidoRepository.existsById(id)) {
            List<ItemPedido> items = pedidoRepository.findById(id).get().getItems();

            for (ItemPedido item : items) {
                itemPedidoRepository.deleteById(item.getId());
            }
            pedidoRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
