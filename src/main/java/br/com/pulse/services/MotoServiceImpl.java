package br.com.pulse.services;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.domainmodel.Moto;
import br.com.pulse.dtos.MotoDto;
import br.com.pulse.exceptions.MotoAlreadyExistsException;
import br.com.pulse.repositories.BeaconRepository;
import br.com.pulse.repositories.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MotoServiceImpl implements MotoService {

    @Autowired
    private final MotoRepository motoRepository;
    private final BeaconRepository beaconRepository;

    public MotoServiceImpl(MotoRepository motoRepository, BeaconRepository beaconRepository) {
        this.motoRepository = motoRepository;
        this.beaconRepository = beaconRepository;
    }

    @Override
    public List<Moto> findAll() {
        return motoRepository.findAll();
    }

    @Override
    public Optional<Moto> findById(Long id) {
        return motoRepository.findById(id);
    }

    public MotoDto cadastrarMoto(MotoDto motoDto) {
        Optional<Moto> motoExistente = buscarMotoPorBeacon(motoDto.getCodigoBeacon());
        if (motoExistente.isPresent()) {
            throw new MotoAlreadyExistsException("Moto já cadastrada com esse código no sistema");
        }

        Beacon beacon = beaconRepository.findByCodigoUnico(motoDto.getCodigoBeacon())
                .orElseGet(() -> {
                    Beacon novoBeacon = new Beacon();
                    novoBeacon.setCodigoUnico(motoDto.getCodigoBeacon());
                    return beaconRepository.save(novoBeacon);
                });

        Moto moto = new Moto();
        moto.setPlaca(motoDto.getPlaca());
        moto.setModelo(motoDto.getModelo());
        moto.setCor(motoDto.getCor());
        moto.setNumeroChassi(motoDto.getNumeroChassi());
        moto.setCodigoBeacon(beacon);
        moto.setStatus(motoDto.getStatus());
        moto.setCondicaoMecanica(motoDto.getCondicaoMecanica());

        beacon.setMoto(moto);
        Moto motoSalva = motoRepository.save(moto);

        return new MotoDto(motoSalva);
    }


    @Override
    public void deletebyId(Long id) {
        motoRepository.deleteById(id);
    }




    @Override
    public Optional<Moto> buscarMotoPorBeacon(UUID codigoBeacon) {
        return beaconRepository.findByCodigoUnico(codigoBeacon)
                .map(Beacon::getMoto);
    }

    @Override
    public MotoDto updateMoto(Long id, MotoDto motoDto) {
        Optional<Moto> optionalMoto = motoRepository.findById(id);
        if (optionalMoto.isEmpty()) {
            throw new RuntimeException("Moto não encontrada com id: " + id);
        }

        Moto moto = optionalMoto.get();
        moto.setPlaca(motoDto.getPlaca());
        moto.setModelo(motoDto.getModelo());
        moto.setCor(motoDto.getCor());
        moto.setNumeroChassi(motoDto.getNumeroChassi());
        moto.setStatus(motoDto.getStatus());
        moto.setCondicaoMecanica(motoDto.getCondicaoMecanica());

        if (motoDto.getCodigoBeacon() != null) {
            Beacon beacon = beaconRepository.findByCodigoUnico(motoDto.getCodigoBeacon())
                    .orElseGet(() -> {
                        Beacon novo = new Beacon();
                        novo.setCodigoUnico(motoDto.getCodigoBeacon());
                        return beaconRepository.save(novo);
                    });
            moto.setCodigoBeacon(beacon);
            beacon.setMoto(moto);
        }

        Moto motoAtualizada = motoRepository.save(moto);
        return new MotoDto(motoAtualizada);
    }




}
