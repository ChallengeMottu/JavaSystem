package br.com.pulse.services;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.domainmodel.Moto;
import br.com.pulse.dtos.BeaconPostDto;
import br.com.pulse.exceptions.ObjectNotFoundException;
import br.com.pulse.repositories.BeaconRepository;
import br.com.pulse.repositories.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Cacheable(value = "beaconCache", key = "#codigo")
    public Optional<Beacon> findByCodigoUnico(UUID codigo) {
        return beaconRepository.findByCodigo(codigo);
    }

    @Override
    @Cacheable(value = "beaconCache", key = "#id")
    public Beacon findBeaconById(Long id) {
        return beaconRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Beacon", id));
    }

    @Override
    @Transactional
    public Beacon saveBeacon(BeaconPostDto beaconDto, Long motoId) {
        Moto moto = motoRepository.findById(motoId)
                .orElseThrow(() -> new ObjectNotFoundException("Moto", motoId));

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
    public void deleteBeaconById(Long id) {
        Beacon beacon = beaconRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Beacon", id));

        Moto moto = beacon.getMoto();
        if (moto != null) {
            moto.setBeacon(null);
            beacon.setMoto(null);
            motoRepository.save(moto);
        }

        beaconRepository.delete(beacon);
    }




    @Override
    @Cacheable(value = "beaconCache", key = "'beacons_all'")
    public List<Beacon> listAllBeacons() {
        return beaconRepository.findAll();
    }

    @Override
    public Page<Beacon> findAllBeaconsPaged(Pageable pageable) {
        return beaconRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Beacon updateBeacon(Long id, BeaconPostDto beaconUpdate) {
        Beacon beacon = beaconRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Beacon", id));

        beacon.setStatus(beaconUpdate.getStatus());

        return beaconRepository.save(beacon);
    }
}
