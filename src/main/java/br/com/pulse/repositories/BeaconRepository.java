package br.com.pulse.repositories;


import br.com.pulse.domainmodel.entities.Beacon;
import br.com.pulse.domainmodel.enums.StatusBeacon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BeaconRepository extends JpaRepository<Beacon, Long> {
    Beacon findByBeaconCode(String beaconCode);
    List<Beacon> findByBeaconStatus(StatusBeacon status);


}
