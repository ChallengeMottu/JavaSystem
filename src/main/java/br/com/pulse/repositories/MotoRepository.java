package br.com.pulse.repositories;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    Optional<Moto> findByBeaconCodigo(UUID codigoBeacon);
    List<Moto> findByModelo(ModeloMoto modelo);
}
