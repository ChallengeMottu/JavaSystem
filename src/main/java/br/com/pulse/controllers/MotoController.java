package br.com.pulse.controllers;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.dtos.MotoDto;
import br.com.pulse.exceptions.MotoAlreadyExistsException;
import br.com.pulse.services.MotoServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/motos")
@Tag(name = "Motos", description = "Operações CRUD da entidade Moto")
public class MotoController {

    @Autowired
    private final MotoServiceImpl motoServiceImpl;

    public MotoController(MotoServiceImpl motoService) {
        this.motoServiceImpl = motoService;
    }



    @GetMapping("/{beacon}")
    public ResponseEntity<MotoDto> buscaPorBeacon(@PathVariable UUID beacon) {
        return motoServiceImpl.buscarMotoPorBeacon(beacon)
                .map(moto -> ResponseEntity.ok(new MotoDto(moto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<MotoDto> listar() {
        List<Moto> motos = motoServiceImpl.findAll();
        return motos.stream().map(MotoDto::new).toList();
    }

    @PostMapping
    public ResponseEntity<MotoDto> cadastrarMoto(@RequestBody MotoDto motoDto) {
        try{
            MotoDto moto = motoServiceImpl.cadastrarMoto(motoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(moto);

        }catch (MotoAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDto> buscaPorId(@PathVariable Long id) {
        return motoServiceImpl.findById(id)
                .map(moto -> ResponseEntity.ok(new MotoDto(moto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MotoDto> deletarMoto(@PathVariable Long id) {
        motoServiceImpl.deletebyId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDto> atualizarMoto(@PathVariable Long id, @RequestBody MotoDto motoDto) {
        try{
            MotoDto motoAtualizada = motoServiceImpl.updateMoto(id, motoDto);
            return ResponseEntity.ok(motoAtualizada);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
