package br.com.pulse.services;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.Patio;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.MotoPostDto;
import br.com.pulse.repositories.MotoRepository;
import br.com.pulse.repositories.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public List<MotoGetDto> findAll() {

        return motoRepository.findAll()
                .stream()
                .map(MotoGetDto::new)
                .toList();
    }

    @Override
    public Optional<Moto> findById(Long id) {
        return Optional.ofNullable(motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto não encontrada com id: " + id)));
    }

    @Override
    @Transactional
    public MotoPostDto cadastrarMoto(MotoPostDto motoPostDto, Long patioId) {
        Patio patio = patioRepository.findById(patioId)
                .orElseThrow(() -> new RuntimeException("Patio não encontrado"));

        Moto moto = new Moto();
        moto.setPlaca(motoPostDto.getPlaca());
        moto.setModelo(motoPostDto.getModelo());
        moto.setNumeroChassi(motoPostDto.getNumeroChassi());
        moto.setCor(motoPostDto.getCor());
        moto.setStatus(motoPostDto.getStatus());
        moto.setCondicaoMecanica(motoPostDto.getCondicaoMecanica());
        moto.setPatio(patio);
        patio.getMotos().add(moto);


        Moto motoSalva = motoRepository.save(moto);
        return new MotoPostDto(motoSalva);
    }

    @Override
    @Transactional
    public void deletebyId(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto não encontrada"));
        motoRepository.delete(moto);
    }

    @Override
    @Transactional
    public MotoPostDto updateMoto(Long id, MotoPostDto motoPostDto) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto não encontrada com id: " + id));

        moto.setPlaca(motoPostDto.getPlaca());
        moto.setModelo(motoPostDto.getModelo());
        moto.setCor(motoPostDto.getCor());
        moto.setNumeroChassi(motoPostDto.getNumeroChassi());
        moto.setStatus(motoPostDto.getStatus());
        moto.setCondicaoMecanica(motoPostDto.getCondicaoMecanica());

        Moto motoAtualizada = motoRepository.save(moto);
        return new MotoPostDto(motoAtualizada);
    }


}
