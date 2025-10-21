package br.com.pulse.controllers;




import br.com.pulse.services.interfaces.BeaconAssociationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associations")
public class AssociationController {

    private final BeaconAssociationService beaconAssociationService;

    public AssociationController(BeaconAssociationService beaconAssociationService) {
        this.beaconAssociationService = beaconAssociationService;
    }

    @PreAuthorize("hasRole('OPERADOR')")
    @PostMapping("/associate")
    public ResponseEntity<String> associateBeacon(
            @RequestParam String plate,
            @RequestParam Long beaconId
    ) {
        beaconAssociationService.associarBeacon(plate, beaconId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Beacon associado com sucesso à moto " + plate);
    }

    @PreAuthorize("hasRole('OPERADOR')")
    @PostMapping("/dissociate")
    public ResponseEntity<String> dissociateBeacon(
            @RequestParam String plate
    ) {
        beaconAssociationService.desassociarBeacon(plate);
        return ResponseEntity.ok("Beacon desassociado com sucesso da moto " + plate);
    }
}

