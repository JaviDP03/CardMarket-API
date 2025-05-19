package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.model.Valoracion;
import com.daw.cardmarket.repository.ValoracionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private ProductoService productoService;

    @Transactional
    public boolean createValoracion(Valoracion valoracion, int idProducto) {
        Valoracion valoracionC = valoracionRepository.save(valoracion);
        Producto producto = productoService.getProductoById(idProducto).orElse(null);

        if (producto != null) {
            producto.getValoraciones().add(valoracionC);
            productoService.updateProducto(idProducto, producto);
        }

        return valoracionC.getId() != null;
    }

    public List<Valoracion> getValoracionesByProducto(int idProducto) {
        return valoracionRepository.findAll();
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
