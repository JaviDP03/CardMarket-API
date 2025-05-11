package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public void createProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Transactional
    public void updateProducto(Producto productoU) {
        Producto producto = productoRepository.findById(productoU.getId()).get();

        producto.setNombre(productoU.getNombre());
        producto.setDescripcion(productoU.getDescripcion());
        producto.setPrecio(productoU.getPrecio());
        producto.setStock(productoU.getStock());
        producto.setUrlImagen(productoU.getUrlImagen());
        producto.setCategoria(productoU.getCategoria());

        productoRepository.save(producto);
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(int id) {
        return productoRepository.findById(id);
    }

    public void deleteProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        }
    }
}
