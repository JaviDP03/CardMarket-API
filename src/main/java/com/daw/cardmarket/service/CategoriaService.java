package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Categoria;
import com.daw.cardmarket.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public boolean createCategoria(Categoria categoria) {
        Categoria categoriaC = categoriaRepository.save(categoria);

        return categoriaC.getId() != null;
    }

    @Transactional
    public boolean updateCategoria(int id, Categoria categoriaU) {
        Optional<Categoria> categoriaO = categoriaRepository.findById(id);

        if (categoriaO.isPresent()) {
            Categoria categoria = categoriaO.get();

            categoria.setNombre(categoriaU.getNombre());
            categoria.setDescripcion(categoriaU.getDescripcion());

            categoriaRepository.save(categoria);

            return true;
        }

        return false;
    }

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoriaById(int id) {
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> getCategoriaByNombre(String nombre) {
        List<Categoria> categorias = getAllCategorias();

        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return Optional.of(categoria);
            }
        }

        return Optional.empty();
    }

    @Transactional
    public boolean deleteCategoria(int id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
