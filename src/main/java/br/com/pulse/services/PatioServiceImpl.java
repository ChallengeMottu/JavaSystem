package br.com.pulse.services;

import br.com.pulse.domainmodel.Patio;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.PatioPostDto;
import br.com.pulse.repositories.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PatioServiceImpl implements PatioService {

    @Autowired
    private final PatioRepository patioRepository;

    public PatioServiceImpl(PatioRepository patioRepository) {
        this.patioRepository = patioRepository;
    }

    @Override
    public List<Patio> listAllPatios() {

        return patioRepository.findAll();
    }

    @Override
    public List<MotoGetDto> listAllMotos() {

        return List.of();
    }

    @Override
    public void deletePatioById(Long id) {

        patioRepository.deleteById(id);
    }

    @Override
    public Patio getPatioById(Long id) {

        return patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado com id: " + id));
    }



    @Override
    @Transactional
    public Patio updatePatio(Long id, PatioPostDto patioUpdate) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado com id: " + id));


        patio.setEndereco(patioUpdate.getEndereco());
        patio.setComprimento(patioUpdate.getComprimento());
        patio.setLargura(patioUpdate.getLargura());

        patio.atualizarCapacidade();
        return patioRepository.save(patio);
    }

    @Override
    public Patio savePatio(Patio patio) {
        patio.atualizarCapacidade();
        return patioRepository.save(patio);
    }
}
