package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Producto;
import com.daw.cardmarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.listarProductos();
    }

    public Producto obtenerProducto(int id) {
        return productoRepository.obtenerProducto(id);
    }

    public boolean crearProducto(Producto producto) {
        return productoRepository.crearProducto(producto);
    }

    public boolean actualizarProducto(int id, Producto producto) {
        return productoRepository.actualizarProducto(id, producto);
    }

    public boolean eliminarProducto(int id) {
        return productoRepository.eliminarProducto(id);
    }
}
