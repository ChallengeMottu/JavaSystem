package br.com.pulse.services;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.MotoPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MotoService {

    List<MotoGetDto> findAllMotos();
    Page<MotoGetDto> findAllMotosPaged(Pageable pageable);
    Optional<Moto> findMotoById(Long id);
    MotoPostDto saveMoto(MotoPostDto motoPostDto, Long patioId);
    void deleteMotoById(Long id);
    MotoPostDto updateMoto(Long id, MotoPostDto motoPostDto);
    MotoGetDto findMotoByCodigoBeacon(UUID codigoBeacon);



}
