package br.com.pulse.services;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.dtos.MotoPostDto;
import br.com.pulse.repositories.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MotoServiceImpl implements MotoService {

    private final MotoRepository motoRepository;

    @Autowired
    public MotoServiceImpl(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Override
    public List<Moto> findAll() {
        return motoRepository.findAll();
    }

    @Override
    public Optional<Moto> findById(Long id) {
        return Optional.ofNullable(motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto não encontrada com id: " + id)));
    }

    @Override
    @Transactional
    public MotoPostDto cadastrarMoto(MotoPostDto motoPostDto) {
        Moto moto = new Moto();
        moto.setPlaca(motoPostDto.getPlaca());
        moto.setModelo(motoPostDto.getModelo());
        moto.setNumeroChassi(motoPostDto.getNumeroChassi());
        moto.setCor(motoPostDto.getCor());
        moto.setStatus(motoPostDto.getStatus());
        moto.setCondicaoMecanica(motoPostDto.getCondicaoMecanica());

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
