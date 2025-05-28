package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public boolean createProducto(Producto producto) {
        Producto productoC = productoRepository.save(producto);

        return productoC.getId() != null;
    }

    @Transactional
    public boolean updateProducto(int id, Producto productoU) {
        Optional<Producto> productoO = productoRepository.findById(id);

        if (productoO.isPresent()) {
            Producto producto = productoO.get();

            producto.setNombre(productoU.getNombre());
            producto.setDescripcion(productoU.getDescripcion());
            producto.setPrecio(productoU.getPrecio());
            producto.setStock(productoU.getStock());
            producto.setImagenB64(productoU.getImagenB64());
            producto.setCategoria(productoU.getCategoria());

            productoRepository.save(producto);

            return true;
        }

        return false;
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(int id) {
        return productoRepository.findById(id);
    }

    public List<Producto> getProductosByCategoria(int idCategoria) {
        List<Producto> productos = getAllProductos();
        List<Producto> productosCategoria = new ArrayList<>();

        for (Producto producto : productos) {
            if (producto.getCategoria().getId() == idCategoria) {
                productosCategoria.add(producto);
            }
        }

        return productosCategoria;
    }

    public boolean deleteProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
