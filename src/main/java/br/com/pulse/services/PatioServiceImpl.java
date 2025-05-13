package br.com.pulse.services;

import br.com.pulse.domainmodel.Patio;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.PatioDto;
import br.com.pulse.repositories.PatioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;



@Service
public class PatioServiceImpl implements PatioService {

    private final PatioRepository patioRepository;

    public PatioServiceImpl(PatioRepository patioRepository) {
        this.patioRepository = patioRepository;
    }

    @Override
    public List<Patio> listAllPatios() {
        return patioRepository.findAll();
    }

    @Override
    public List<MotoGetDto> listAllMotos(Long patioId) {
        Patio patio = patioRepository.findById(patioId)
                .orElseThrow(()-> new RuntimeException("Patio não encontrado"));
        return patio.getMotos().stream()
                .map(MotoGetDto::new)
                .toList();
    }

    @Override
    public void deletePatioById(Long id) {
        if (!patioRepository.existsById(id)) {
            throw new RuntimeException("Pátio não encontrado com id: " + id);
        }
        patioRepository.deleteById(id);
    }

    @Override
    public Patio getPatioById(Long id) {
        return patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado com id: " + id));
    }

    @Override
    @Transactional
    public Patio updatePatio(Long id, PatioDto patioUpdate) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado com id: " + id));


        patio.setEndereco(patioUpdate.getEndereco());
        patio.setComprimento(patioUpdate.getComprimento());
        patio.setLargura(patioUpdate.getLargura());

        patio.atualizarCapacidade();
        return patioRepository.save(patio);
    }

    @Override
    public Patio savePatio(PatioDto patioDto) {
        Patio patio = new Patio();
        patio.setEndereco(patioDto.getEndereco());
        patio.setComprimento(patioDto.getComprimento());
        patio.setLargura(patioDto.getLargura());
        patio.atualizarCapacidade();
        return patioRepository.save(patio);
    }
}


