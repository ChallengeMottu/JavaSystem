package br.com.pulse.services;

import br.com.pulse.domainmodel.Patio;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.PatioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatioService {
    List<Patio> findAllPatios();
    Page<Patio> findAllPatiosPaged(Pageable pageable);
    List<MotoGetDto> listAllMotos(Long patioId);
    void deletePatioById(Long id);
    Patio getPatioById(Long id);
    Patio updatePatio(Long id, PatioDto patio);
    Patio savePatio(PatioDto patioDto);
}
