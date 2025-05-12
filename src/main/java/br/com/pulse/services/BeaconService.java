package br.com.pulse.services;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.dtos.BeaconPostDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeaconService {
    Optional<Beacon> findByCodigoUnico(UUID codigoUnico);

    Beacon findById(Long id);

    Beacon save(BeaconPostDto beaconDto, Long motoId);

    void deleteById(Long id);

    void deleteByCodigo(UUID codigo);

    List<Beacon> listAllBeacons();




    Beacon updateBeacon(Long id, BeaconPostDto beaconUpdate);
}



