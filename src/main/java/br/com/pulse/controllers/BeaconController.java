package br.com.pulse.controllers;

import br.com.pulse.domainmodel.entities.Beacon;
import br.com.pulse.domainmodel.enums.StatusBeacon;
import br.com.pulse.dtos.request.BeaconRequestDto;
import br.com.pulse.dtos.response.BeaconResponseDto;
import br.com.pulse.services.interfaces.BeaconService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/beacons")
public class BeaconController {

    private final BeaconService beaconService;
    private final ModelMapper modelMapper;

    public BeaconController(BeaconService beaconService, ModelMapper modelMapper) {
        this.beaconService = beaconService;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("hasRole('OPERADOR')")
    @GetMapping
    public List<BeaconResponseDto> findAll() {
        return beaconService.findAll()
                .stream()
                .map(beacon -> modelMapper.map(beacon, BeaconResponseDto.class))
                .collect(Collectors.toList());
    }


    @PreAuthorize("hasRole('OPERADOR')")
    @GetMapping("/{id}")
    public BeaconResponseDto findById(@PathVariable Long id) {
        Beacon beacon = beaconService.findById(id);
        return modelMapper.map(beacon, BeaconResponseDto.class);
    }


    @PreAuthorize("hasRole('OPERADOR')")
    @PostMapping
    public BeaconResponseDto create(@Valid @RequestBody BeaconRequestDto dto) {
        Beacon beacon = modelMapper.map(dto, Beacon.class);
        Beacon saved = beaconService.save(beacon);
        return modelMapper.map(saved, BeaconResponseDto.class);
    }


    @PreAuthorize("hasRole('OPERADOR')")
    @PutMapping("/{id}")
    public BeaconResponseDto update(@PathVariable Long id, @Valid @RequestBody BeaconRequestDto dto) {
        Beacon beaconAtualizado = modelMapper.map(dto, Beacon.class);
        Beacon updated = beaconService.update(id, beaconAtualizado);
        return modelMapper.map(updated, BeaconResponseDto.class);
    }

    @PreAuthorize("hasRole('OPERADOR')")
    @PatchMapping("/{id}/status")
    public BeaconResponseDto updateStatus(@PathVariable Long id, @RequestParam StatusBeacon status) {
        Beacon updated = beaconService.updateStatus(id, status);
        return modelMapper.map(updated, BeaconResponseDto.class);
    }

    @PreAuthorize("hasRole('OPERADOR')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        beaconService.delete(id);
    }

    @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR')")
    @GetMapping("/status/{status}")
    public List<BeaconResponseDto> findByStatus(@PathVariable StatusBeacon status) {
        return beaconService.findByStatus(status)
                .stream()
                .map(beacon -> modelMapper.map(beacon, BeaconResponseDto.class))
                .collect(Collectors.toList());
    }
}