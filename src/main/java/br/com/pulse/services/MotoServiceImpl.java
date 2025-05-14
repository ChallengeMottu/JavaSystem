package br.com.pulse.services;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.Patio;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.MotoPostDto;
import br.com.pulse.exceptions.EmptyResultException;
import br.com.pulse.exceptions.ObjectNotFoundException;
import br.com.pulse.repositories.MotoRepository;
import br.com.pulse.repositories.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MotoServiceImpl implements MotoService {

    private final MotoRepository motoRepository;
    private final PatioRepository patioRepository;

    @Autowired
    public MotoServiceImpl(MotoRepository motoRepository, PatioRepository patioRepository) {
        this.motoRepository = motoRepository;
        this.patioRepository = patioRepository;
    }

    @Override
    @Cacheable(value = "motoCache", key = "'motos_all'")
    public List<MotoGetDto> findAllMotos() {

        List<Moto> motos = motoRepository.findAll();
        if (motos.isEmpty()) {
            throw new EmptyResultException("Nenhuma moto encontrada");
        }
        return motos
                .stream()
                .map(MotoGetDto::new)
                .toList();
    }

    @Override
    @Cacheable(value = "motoCache", key = "#id")
    public Optional<Moto> findMotoById(Long id) {
        return Optional.ofNullable(motoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Moto", id)));
    }

    @Override
    @Transactional
    public MotoPostDto saveMoto(MotoPostDto motoPostDto, Long patioId) {
        Patio patio = patioRepository.findById(patioId)
                .orElseThrow(() -> new ObjectNotFoundException("Patio", patioId));

        Moto moto = new Moto();
        moto.setPlaca(motoPostDto.getPlaca());
        moto.setModelo(motoPostDto.getModelo());
        moto.setNumeroChassi(motoPostDto.getNumeroChassi());
        moto.setStatus(motoPostDto.getStatus());
        moto.setCondicaoMecanica(motoPostDto.getCondicaoMecanica());
        moto.setPatio(patio);
        patio.getMotos().add(moto);


        Moto motoSalva = motoRepository.save(moto);
        return new MotoPostDto(motoSalva);
    }

    @Override
    @Transactional
    public void deleteMotoById(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Moto", id));
        motoRepository.delete(moto);
    }

    @Override
    @Transactional
    public MotoPostDto updateMoto(Long id, MotoPostDto motoPostDto) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Moto" , id));

        moto.setPlaca(motoPostDto.getPlaca());
        moto.setModelo(motoPostDto.getModelo());
        moto.setNumeroChassi(motoPostDto.getNumeroChassi());
        moto.setStatus(motoPostDto.getStatus());
        moto.setCondicaoMecanica(motoPostDto.getCondicaoMecanica());

        Moto motoAtualizada = motoRepository.save(moto);
        return new MotoPostDto(motoAtualizada);
    }

    @Override
    @Cacheable(value = "motoCache", key = "#codigoBeacon")
    public MotoGetDto findMotoByCodigoBeacon(UUID codigoBeacon) {
        return motoRepository.findByBeaconCodigo(codigoBeacon)
                .map(MotoGetDto::new)
                .orElseThrow(() -> new ObjectNotFoundException("Moto", codigoBeacon));
    }

    @Override
    @Cacheable(value = "motoCache", key = "#modelo.name")
    public List<MotoGetDto> findMotoByModelo(ModeloMoto modelo) {
        List<Moto> motos = motoRepository.findByModelo(modelo);
        if (motos.isEmpty()){
            throw new EmptyResultException("Nenhuma moto encontrada");
        }
        return motos
                .stream()
                .map(MotoGetDto::new)
                .toList();
    }


}
