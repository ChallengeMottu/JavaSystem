package br.com.pulse.controllers;

import br.com.pulse.domainmodel.Patio;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.PatioDto;
import br.com.pulse.services.PatioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patios")
public class PatioController {

    private final PatioServiceImpl patioServiceImpl;

    public PatioController(PatioServiceImpl patioServiceImpl) {
        this.patioServiceImpl = patioServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Patio>> ListarPatios(){
        List<Patio> patios = patioServiceImpl.listAllPatios();
        return ResponseEntity.ok().body(patios);
    }

    @PostMapping
    public ResponseEntity<Patio> savePatio(@RequestBody PatioDto patioDto){
        Patio patio = patioServiceImpl.savePatio(patioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patio);
    }

    @GetMapping("/{id}/motos")
    public ResponseEntity<List<MotoGetDto>> listarMotosPorPatio(@PathVariable Long id){
        List<MotoGetDto> motos = patioServiceImpl.listAllMotos(id);
        return ResponseEntity.ok().body(motos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Patio> getPatioById(@PathVariable Long id){
        return ResponseEntity.ok().body(patioServiceImpl.getPatioById(id));
    }



}
