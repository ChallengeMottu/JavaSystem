package br.com.pulse.services.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface BeaconAssociationService {
    void associarBeacon(String plate, Long beaconId);
    void desassociarBeacon(String plate);
}
