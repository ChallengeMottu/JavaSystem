package br.com.pulse.services.interfaces;


import br.com.pulse.domainmodel.entities.Beacon;
import br.com.pulse.domainmodel.enums.StatusBeacon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BeaconService {
    Beacon findById(Long id);
    List<Beacon> findAll();
    Beacon save(Beacon beacon);
    void delete(Long id);
    Beacon update(Long id, Beacon beacon);
    List<Beacon> findByStatus(StatusBeacon status);
    Beacon updateStatus(Long id, StatusBeacon novoStatus);



}



