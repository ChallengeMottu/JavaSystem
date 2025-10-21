package br.com.pulse.controllers;

import br.com.pulse.domainmodel.entities.Employee;
import br.com.pulse.dtos.request.EmployeeRequestDto;
import br.com.pulse.dtos.response.EmployeeResponseDto;
import br.com.pulse.services.interfaces.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('GESTOR')")
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
        Employee employee = modelMapper.map(dto, Employee.class);
        Employee saved = employeeService.save(employee);
        EmployeeResponseDto response = modelMapper.map(saved, EmployeeResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PreAuthorize("hasRole('GESTOR')")
    @GetMapping("/parking/{parkingId}")
    public ResponseEntity<List<EmployeeResponseDto>> getByParking(@PathVariable Long parkingId) {
        List<EmployeeResponseDto> response = employeeService.findByParkingId(parkingId)
                .stream()
                .map(emp -> modelMapper.map(emp, EmployeeResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('GESTOR')")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<EmployeeResponseDto> getByCpf(@PathVariable String cpf) {
        Employee employee = employeeService.findByCpf(cpf);
        EmployeeResponseDto response = modelMapper.map(employee, EmployeeResponseDto.class);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('GESTOR')")
    @PutMapping
    public ResponseEntity<Void> updateEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
        Employee employee = modelMapper.map(dto, Employee.class);
        employeeService.updateUser(employee);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('GESTOR')")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String cpf) {
        employeeService.deleteUserByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
