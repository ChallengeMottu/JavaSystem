package br.com.pulse.controllers;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.dtos.BeaconPostDto;
import br.com.pulse.services.BeaconServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/beacon")
@Tag(name = "Beacons", description = "Operações CRUD da entidade Beacon")
public class BeaconController {

    private final BeaconServiceImpl beaconServiceImpl;

    @Autowired
    public BeaconController(BeaconServiceImpl beaconServiceImpl) {
        this.beaconServiceImpl = beaconServiceImpl;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Tem como função a busca pelos Beacons de acordo com seu ID cadastrado")
    public ResponseEntity<Beacon> getBeaconById(@PathVariable Long id) {
        return ResponseEntity.ok(beaconServiceImpl.findBeaconById(id));
    }

    @GetMapping
    @Operation(summary = "Lista todos os Beacons cadastrados em sistemas")
    public ResponseEntity<List<Beacon>> getAllBeacons() {
        List<Beacon> beacons = beaconServiceImpl.listAllBeacons();
        return ResponseEntity.ok(beacons);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Beacon>> getAllBeaconsPaginated(Pageable pageable) {
        Page<Beacon> beacon = beaconServiceImpl.findAllBeaconsPaged(pageable);
        return ResponseEntity.ok().body(beacon);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Beacon pelo ID")
    public ResponseEntity<Void> deleteBeaconById(@PathVariable Long id) {
        beaconServiceImpl.deleteBeaconById(id);
        return ResponseEntity.noContent().build();
    }



    @Operation(summary = "Atualiza o Beacon pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Beacon> updateBeaconById(@PathVariable Long id, @Valid @RequestBody BeaconPostDto beaconDto) {
            Beacon beaconUpdated = beaconServiceImpl.updateBeacon(id, beaconDto);
            return ResponseEntity.ok(beaconUpdated);
    }



    @Operation(summary = "Adiciona um Beacon e o vincula a uma Moto já existente")
    @PostMapping("/{motoId}")
    public ResponseEntity<Beacon> saveBeacon(@PathVariable Long motoId, @Valid @RequestBody BeaconPostDto beaconDto) {
            Beacon beaconSaved = beaconServiceImpl.saveBeacon(beaconDto, motoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(beaconSaved);
    }

}
