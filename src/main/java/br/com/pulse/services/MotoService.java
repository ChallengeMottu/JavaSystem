package br.com.pulse.services;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.MotoPostDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MotoService {

    List<MotoGetDto> findAllMotos();
    Optional<Moto> findMotoById(Long id);
    MotoPostDto saveMoto(MotoPostDto motoPostDto, Long patioId);
    void deleteMotoById(Long id);
    MotoPostDto updateMoto(Long id, MotoPostDto motoPostDto);
    MotoGetDto findMotoByCodigoBeacon(UUID codigoBeacon);
    List<MotoGetDto> findMotoByModelo(ModeloMoto modelo);
}
