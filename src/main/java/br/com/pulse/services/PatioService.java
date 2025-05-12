package br.com.pulse.services;

import br.com.pulse.domainmodel.Patio;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.PatioPostDto;

import java.util.List;
import java.util.Optional;

public interface PatioService {
    List<Patio> listAllPatios();
    List<MotoGetDto> listAllMotos();
    void deletePatioById(Long id);
    Patio getPatioById(Long id);
    Patio updatePatio(Long id, PatioPostDto patio);
    Patio savePatio(Patio patio);
}
