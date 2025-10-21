package br.com.pulse.services.implementations;

import br.com.pulse.domainmodel.entities.Beacon;
import br.com.pulse.domainmodel.entities.Motorcycle;
import br.com.pulse.domainmodel.enums.StatusBeacon;
import br.com.pulse.exceptions.InvalidArgumentException;
import br.com.pulse.exceptions.ObjectAlreadyExistsException;
import br.com.pulse.services.interfaces.BeaconAssociationService;
import br.com.pulse.services.interfaces.BeaconService;
import br.com.pulse.services.interfaces.MotorcycleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeaconAssociationServiceImpl implements BeaconAssociationService {

    @Autowired
    private MotorcycleService motorcycleService;

    @Autowired
    private BeaconService beaconService;

    @Override
    @Transactional
    public void associarBeacon(String plate, Long beaconId) {
        Beacon beacon = beaconService.findById(beaconId);

        if (beacon.getMotorcycle() != null) {
            throw new ObjectAlreadyExistsException("Beacon já está associado a outra moto");
        }


        Motorcycle moto = motorcycleService.findByLicensePlate(plate);
        beacon.setMotorcycle(moto);


        beacon.setBeaconStatus(StatusBeacon.INATIVO);

        beaconService.update(beacon.getId(), beacon);
    }


    @Override
    @Transactional
    public void desassociarBeacon(String plate) {
        Motorcycle moto = motorcycleService.findByLicensePlate(plate);
        if (moto == null) {
            throw new InvalidArgumentException("Moto com placa " + plate + " não encontrada.");
        }

        Beacon beacon = moto.getBeacon();
        if (beacon != null) {
            moto.setBeacon(null);
            beacon.setMotorcycle(null);

            beacon.setBeaconStatus(StatusBeacon.ATIVO);
            motorcycleService.update(moto.getId(), moto);
            beaconService.update(beacon.getId(), beacon);
        }
    }
}
