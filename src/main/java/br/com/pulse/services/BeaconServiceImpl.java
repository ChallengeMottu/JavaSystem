package br.com.pulse.services;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.domainmodel.Moto;
import br.com.pulse.dtos.BeaconPostDto;
import br.com.pulse.repositories.BeaconRepository;
import br.com.pulse.repositories.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeaconServiceImpl implements BeaconService {

    private final BeaconRepository beaconRepository;
    private final MotoRepository motoRepository;

    @Override
    public Optional<Beacon> findByCodigoUnico(UUID codigo) {
        return beaconRepository.findByCodigo(codigo);
    }

    @Override
    public Beacon findById(Long id) {
        return beaconRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beacon com ID " + id + " não encontrado."));
    }

    @Override
    @Transactional
    public Beacon save(BeaconPostDto beaconDto, Long motoId) {
        Moto moto = motoRepository.findById(motoId)
                .orElseThrow(() -> new RuntimeException("Moto com ID " + motoId + " não encontrada."));

        if (moto.getBeacon() != null) {
            throw new RuntimeException("Esta moto já possui um beacon vinculado.");
        }
        UUID novoUUID;
        do {
            novoUUID = UUID.randomUUID();
        } while (beaconRepository.findByCodigo(novoUUID).isPresent());

        Beacon beacon = new Beacon();
        beacon.setCodigo(novoUUID);
        beacon.setStatus(beaconDto.getStatus());
        beacon.setMoto(moto);



        moto.setBeacon(beacon);

        return beaconRepository.save(beacon);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Beacon beacon = beaconRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beacon com ID " + id + " não encontrado para exclusão."));

        Moto moto = beacon.getMoto();
        if (moto != null) {
            moto.setBeacon(null);
            beacon.setMoto(null);
            motoRepository.save(moto);
        }

        beaconRepository.delete(beacon);
    }


    @Override
    public void deleteByCodigo(UUID codigo) {
        Optional<Beacon> beacon = beaconRepository.findByCodigo(codigo);
        if (beacon.isEmpty()) {
            throw new RuntimeException("Beacon com código " + codigo + " não encontrado para exclusão.");
        }
        beaconRepository.delete(beacon.get());
    }

    @Override
    public List<Beacon> listAllBeacons() {
        return beaconRepository.findAll();
    }

    @Transactional
    @Override
    public Beacon updateBeacon(Long id, BeaconPostDto beaconUpdate) {
        Beacon beacon = beaconRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beacon com ID " + id + " não encontrado."));

        beacon.setStatus(beaconUpdate.getStatus());

        return beaconRepository.save(beacon);
    }
}
