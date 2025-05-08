package br.com.pulse.services;

import br.com.pulse.domainmodel.Beacon;

import java.util.Optional;
import java.util.UUID;

public interface BeaconService {
    Optional<Beacon> findByCodigoUnico(UUID codigoUnico);
}
