package br.com.pulse.repositories;


import br.com.pulse.domainmodel.entities.Motorcycle;
import br.com.pulse.domainmodel.enums.OperationStatus;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    Motorcycle findByLicensePlate(String licensePlate);
    List<Motorcycle> findByParkingId(Long parkingId);
    List<Motorcycle> findByOperationalStatusAndParkingId(OperationStatus status, Long parkingId);
    long countByParkingId(Long parkingId);
}
