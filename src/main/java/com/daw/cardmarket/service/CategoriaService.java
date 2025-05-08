package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Categoria;
import com.daw.cardmarket.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.listarCategorias();
    }

    public Categoria obtenerCategoria(int id) {
        return categoriaRepository.obtenerCategoria(id);
    }

    public boolean crearCategoria(Categoria categoria) {
        return categoriaRepository.crearCategoria(categoria);
    }

    public boolean actualizarCategoria(int id, Categoria categoria) {
        return categoriaRepository.actualizarCategoria(id, categoria);
    }

    public boolean eliminarCategoria(int id) {
        return categoriaRepository.eliminarCategoria(id);
    }
}
