package br.com.pulse.repositories;

import br.com.pulse.domainmodel.Patio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatioRepository extends JpaRepository<Patio, Long> {
}
