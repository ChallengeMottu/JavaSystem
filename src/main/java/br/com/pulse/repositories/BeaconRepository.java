package br.com.pulse.repositories;

import br.com.pulse.domainmodel.Beacon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeaconRepository extends JpaRepository<Beacon, Long> {

    Optional<Beacon> findByCodigoUnico(UUID codigoUnico);
}
