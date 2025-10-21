package br.com.pulse.controllers;

import br.com.pulse.domainmodel.entities.Motorcycle;
import br.com.pulse.domainmodel.enums.OperationStatus;
import br.com.pulse.dtos.request.MotorcycleRequestDto;
import br.com.pulse.dtos.response.MotorcycleResponseDto;
import br.com.pulse.services.interfaces.MotorcycleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motorcycles")
public class MotorcycleController {

    private final MotorcycleService motorcycleService;
    private final ModelMapper modelMapper;

    public MotorcycleController(MotorcycleService motorcycleService, ModelMapper modelMapper) {
        this.motorcycleService = motorcycleService;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("hasRole('OPERADOR')")
    @PostMapping
    public ResponseEntity<MotorcycleResponseDto> create(@Valid @RequestBody MotorcycleRequestDto dto) {
        Motorcycle moto = modelMapper.map(dto, Motorcycle.class);
        Motorcycle saved = motorcycleService.save(moto);
        MotorcycleResponseDto response = modelMapper.map(saved, MotorcycleResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PreAuthorize("hasRole('OPERADOR')")
    @GetMapping("/{id}")
    public ResponseEntity<MotorcycleResponseDto> findById(@PathVariable Long id) {
        Motorcycle moto = motorcycleService.findById(id);
        MotorcycleResponseDto response = modelMapper.map(moto, MotorcycleResponseDto.class);
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR')")
    @GetMapping("/plate/{licensePlate}")
    public ResponseEntity<MotorcycleResponseDto> findByLicensePlate(@PathVariable String licensePlate) {
        Motorcycle moto = motorcycleService.findByLicensePlate(licensePlate);
        MotorcycleResponseDto response = modelMapper.map(moto, MotorcycleResponseDto.class);
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR')")
    @GetMapping("/parking/{parkingId}")
    public ResponseEntity<List<MotorcycleResponseDto>> listByParking(@PathVariable Long parkingId) {
        List<MotorcycleResponseDto> response = motorcycleService.findAllByParking(parkingId)
                .stream()
                .map(m -> modelMapper.map(m, MotorcycleResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAnyRole('OPERADOR', 'MECANICO')")
    @GetMapping("/parking/{parkingId}/status/{status}")
    public ResponseEntity<List<MotorcycleResponseDto>> listByStatusAndParking(
            @PathVariable Long parkingId,
            @PathVariable OperationStatus status
    ) {
        List<MotorcycleResponseDto> response = motorcycleService.listAllByStatusAndParking(status, parkingId)
                .stream()
                .map(m -> modelMapper.map(m, MotorcycleResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasRole('OPERADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody MotorcycleRequestDto dto) {
        Motorcycle moto = modelMapper.map(dto, Motorcycle.class);
        motorcycleService.update(id, moto);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasRole('MECANICO')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam OperationStatus status) {
        motorcycleService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyRole('OPERADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        motorcycleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
