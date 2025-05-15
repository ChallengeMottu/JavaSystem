package br.com.pulse.services;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.dtos.BeaconPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeaconService {
    Optional<Beacon> findByCodigoUnico(UUID codigoUnico);
    Beacon findBeaconById(Long id);
    Beacon saveBeacon(BeaconPostDto beaconDto, Long motoId);
    void deleteBeaconById(Long id);
    List<Beacon> findAllBeacons();
    Page<Beacon> findAllBeaconsPaged(Pageable pageable);
    Beacon updateBeacon(Long id, BeaconPostDto beaconUpdate);
}



