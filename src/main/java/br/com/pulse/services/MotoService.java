package br.com.pulse.services;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.dtos.MotoPostDto;

import java.util.List;
import java.util.Optional;


public interface MotoService {

    List<Moto> findAll();
    Optional<Moto> findById(Long id);
    MotoPostDto cadastrarMoto(MotoPostDto motoPostDto);
    void deletebyId(Long id);
    MotoPostDto updateMoto(Long id, MotoPostDto motoPostDto);

}
