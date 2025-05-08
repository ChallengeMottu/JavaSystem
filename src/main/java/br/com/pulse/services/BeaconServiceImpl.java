package br.com.pulse.services;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.repositories.BeaconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BeaconServiceImpl implements BeaconService {
    @Autowired
    private BeaconRepository beaconRepository;

    @Override
    public Optional<Beacon> findByCodigoUnico(UUID codigoUnico) {
        return beaconRepository.findByCodigoUnico(codigoUnico);
    }
}
