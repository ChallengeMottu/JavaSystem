package br.com.pulse.services;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.dtos.MotoDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MotoService {

    List<Moto> findAll();
    Optional<Moto> findById(Long id);
    MotoDto cadastrarMoto(MotoDto motoDto);
    void deletebyId(Long id);
    Optional<Moto> buscarMotoPorBeacon(UUID codigoBeacon);
    MotoDto updateMoto(Long id, MotoDto motoDto);

}
