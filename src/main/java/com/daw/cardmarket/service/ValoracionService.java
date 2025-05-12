package com.daw.cardmarket.service;

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

    @Transactional
    public boolean createValoracion(Valoracion valoracion) {
        Valoracion valoracionC = valoracionRepository.save(valoracion);

        return valoracionC.getId() != null;
    }

    public List<Valoracion> getValoraciones() {
        return valoracionRepository.findAll();
    }

    public Optional<Valoracion> getValoracionById(int id) {
        return valoracionRepository.findById(id);
    }

    @Transactional
    public boolean deleteValoracion(int id) {
        if (valoracionRepository.existsById(id)) {
            valoracionRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
