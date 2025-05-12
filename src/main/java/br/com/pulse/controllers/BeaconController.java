package br.com.pulse.controllers;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.dtos.BeaconPostDto;
import br.com.pulse.services.BeaconServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/beacons")
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
        return ResponseEntity.ok(beaconServiceImpl.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lista todos os Beacons cadastrados em sistemas")
    public ResponseEntity<List<Beacon>> getAllBeacons() {
        List<Beacon> beacons = beaconServiceImpl.listAllBeacons();
        return ResponseEntity.ok(beacons);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Beacon pelo ID")
    public ResponseEntity<Void> deleteBeaconById(@PathVariable Long id) {
        beaconServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Encontra o Beacon pelo código único transmitido pelo aparelho")
    @GetMapping("/codigo/{codigoBeacon}")
    public ResponseEntity<Beacon> getBeaconByCodigoUnico(@PathVariable UUID codigoBeacon) {
        return beaconServiceImpl.findByCodigoUnico(codigoBeacon)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza o Beacon pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Beacon> updateBeaconById(@PathVariable Long id, @RequestBody BeaconPostDto beaconDto) {
        try {
            Beacon beaconUpdated = beaconServiceImpl.updateBeacon(id, beaconDto);
            return ResponseEntity.ok(beaconUpdated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deleta o Beacon pelo código único transmitido pelo aparelho")
    @DeleteMapping("/codigo/{codigo}")
    public ResponseEntity<Void> deleteBeaconByCodigo(@PathVariable UUID codigo) {
        beaconServiceImpl.deleteByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Adiciona um Beacon e o vincula a uma Moto já existente")
    @PostMapping("/{motoId}")
    public ResponseEntity<Beacon> saveBeacon(@PathVariable Long motoId, @RequestBody BeaconPostDto beaconDto) {
        try {
            Beacon beaconSaved = beaconServiceImpl.save(beaconDto, motoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(beaconSaved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
