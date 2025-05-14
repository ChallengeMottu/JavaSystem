package br.com.pulse.controllers;

import br.com.pulse.domainmodel.Patio;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.PatioDto;
import br.com.pulse.services.PatioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patio")
@Tag(name = "Patios", description = "Operações CRUD da entidade Patio")
public class PatioController {

    private final PatioServiceImpl patioServiceImpl;


    public PatioController(PatioServiceImpl patioServiceImpl) {
        this.patioServiceImpl = patioServiceImpl;
    }

    @GetMapping
    @Operation(summary = "Listagem de todos os Patios cadastrados")
    public ResponseEntity<List<Patio>> listAllPatios() {
        List<Patio> patios = patioServiceImpl.listAllPatios();
        return ResponseEntity.ok().body(patios);
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<Patio>> findAllPatiosPaged(Pageable pageable) {
        Page<Patio> page = patioServiceImpl.listAllPatiosPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    @Operation(summary = "Cadastro de Patios")
    public ResponseEntity<Patio> savePatio(@Valid @RequestBody PatioDto patioDto){
        Patio patio = patioServiceImpl.savePatio(patioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patio);
    }

    @GetMapping("/{id}/motos")
    @Operation(summary = "Listagem de Motos por Patio")
    public ResponseEntity<List<MotoGetDto>> listMotosByPatio(@PathVariable Long id){
        List<MotoGetDto> motos = patioServiceImpl.listAllMotos(id);
        return ResponseEntity.ok().body(motos);
    }

    @GetMapping("{id}")
    @Operation(summary = "Encontra Patio pelo ID cadastrado")
    public ResponseEntity<Patio> getPatioById(@PathVariable Long id){
        return ResponseEntity.ok().body(patioServiceImpl.getPatioById(id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deleta Patio pelo ID cadastrado")
    public ResponseEntity<Patio> deletePatio(@PathVariable Long id){
        patioServiceImpl.deletePatioById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    @Operation(summary = "Atualiza informações do Patio cadastrado")
    public ResponseEntity<Patio> updatePatioById(@PathVariable Long id, @RequestBody PatioDto patioDto){
            Patio patioUpdated = patioServiceImpl.updatePatio(id, patioDto);
            return ResponseEntity.ok().body(patioUpdated);
    }


}
