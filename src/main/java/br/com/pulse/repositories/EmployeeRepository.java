package br.com.pulse.repositories;

import br.com.pulse.domainmodel.entities.Employee;
import br.com.pulse.domainmodel.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
    Optional<Employee> findByCpf(String cpf);
    List<Employee> findAllByParkingId(Long parkingId);


}