package br.com.pulse.repositories;

import br.com.pulse.domainmodel.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

}
