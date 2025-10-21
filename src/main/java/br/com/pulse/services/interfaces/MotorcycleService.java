package br.com.pulse.services.interfaces;

import br.com.pulse.domainmodel.entities.Motorcycle;
import br.com.pulse.domainmodel.enums.OperationStatus;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public interface MotorcycleService {
    Motorcycle save(Motorcycle motorcycle);
    Motorcycle findById(Long motorcycleId);
    Motorcycle findByLicensePlate(String licensePlate);
    List<Motorcycle> findAllByParking(Long parkingId);
    void delete(Long motorcycleId);
    List<Motorcycle> listAllByStatusAndParking(OperationStatus status, Long parkingId);
    void update(Long motorcycleId, Motorcycle motorcycle);
    boolean belongsToEmployeeParking(Long motorcycleId, Long parkingId);

    void updateStatus(Long id, OperationStatus operationalStatus);
}
