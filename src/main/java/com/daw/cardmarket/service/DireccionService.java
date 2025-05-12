package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Direccion;
import com.daw.cardmarket.repository.DireccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Transactional
    public boolean createDireccion(Direccion direccion) {
        Direccion direccionC = direccionRepository.save(direccion);

        return direccionC.getId() != null;
    }

    @Transactional
    public boolean updateDireccion(Direccion direccionU) {
        Optional<Direccion> direccionO = direccionRepository.findById(direccionU.getId());

        if (direccionO.isPresent()) {
            Direccion direccion = direccionO.get();

            direccion.setDireccion(direccionU.getDireccion());
            direccion.setCodigoPostal(direccionU.getCodigoPostal());
            direccion.setCiudad(direccionU.getCiudad());
            direccion.setProvincia(direccionU.getProvincia());
            direccion.setPais(direccionU.getPais());
            direccion.setEsPrincipal(direccionU.isEsPrincipal());

            direccionRepository.save(direccion);

            return true;
        }

        return false;
    }

    public List<Direccion> getAllDirecciones() {
        return direccionRepository.findAll();
    }

    public Optional<Direccion> getDireccionById(int id) {
        return direccionRepository.findById(id);
    }

    @Transactional
    public boolean deleteDireccion(int id) {
        if (direccionRepository.existsById(id)) {
            direccionRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
