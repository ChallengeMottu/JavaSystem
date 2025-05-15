package br.com.pulse.services;

import br.com.pulse.domainmodel.Patio;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.PatioDto;
import br.com.pulse.exceptions.ObjectNotFoundException;
import br.com.pulse.repositories.PatioRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Cacheable(value = "patioCache", key = "'patios_all'")
    public List<Patio> findAllPatios() {
        return patioRepository.findAll();
    }

    @Override
    public Page<Patio> findAllPatiosPaged(Pageable pageable) {
        return patioRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "patioCache", key = "#patioId")
    public List<MotoGetDto> listAllMotos(Long patioId) {
        Patio patio = patioRepository.findById(patioId)
                .orElseThrow(()-> new ObjectNotFoundException("Patio", patioId));
        return patio.getMotos().stream()
                .map(MotoGetDto::new)
                .toList();
    }

    @Override
    public void deletePatioById(Long id) {
        if (!patioRepository.existsById(id)) {
            throw new ObjectNotFoundException("Pátio" ,id);
        }
        patioRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "patioCache", key = "#id")
    public Patio getPatioById(Long id) {
        return patioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pátio",  id));
    }

    @Override
    @Transactional
    public Patio updatePatio(Long id, PatioDto patioUpdate) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pátio",  id));


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


