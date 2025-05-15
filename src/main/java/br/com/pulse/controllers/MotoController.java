package br.com.pulse.controllers;


import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.MotoPostDto;
import br.com.pulse.services.MotoServiceImpl;
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
import java.util.UUID;


@RestController
@RequestMapping("/moto")
@Tag(name = "Motos", description = "Operações CRUD da entidade Moto")
public class MotoController {

    private final MotoServiceImpl motoServiceImpl;

    @Autowired
    public MotoController(MotoServiceImpl motoServiceImpl) {
        this.motoServiceImpl = motoServiceImpl;
    }

    @Operation(summary = "Encontra a Moto pelo ID cadastrado")
    @GetMapping("/{id}")
    public ResponseEntity<MotoGetDto> findMotoById(@PathVariable Long id) {
        return motoServiceImpl.findMotoById(id)
                .map(moto -> ResponseEntity.ok(new MotoGetDto(moto)))
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Lista todas as Motos cadastradas")
    @GetMapping
    public ResponseEntity<List<MotoGetDto>> findAllMotos() {
        return ResponseEntity.ok(motoServiceImpl.findAllMotos());
    }


    @GetMapping("/pageable")
    @Operation(summary = "Lista todas as Motos com paginação")
    public ResponseEntity<Page<MotoGetDto>> findAllMotosPaged(Pageable pageable){
        Page<MotoGetDto> page = motoServiceImpl.findAllMotosPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @Operation(summary = "Realiza cadastro da Moto")
    @PostMapping("/{patioId}")
    public ResponseEntity<MotoPostDto> saveMoto(@Valid @RequestBody MotoPostDto motoPostDto, @PathVariable Long patioId) {
            MotoPostDto moto = motoServiceImpl.saveMoto(motoPostDto, patioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(moto);
    }


    @Operation(summary = "Deleta Moto pelo ID cadastrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotoById(@PathVariable Long id) {
            motoServiceImpl.deleteMotoById(id);
            return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza Moto pelo ID cadastrado")
    @PutMapping("/{id}")
    public ResponseEntity<MotoPostDto> updateMoto(@PathVariable Long id, @Valid @RequestBody MotoPostDto motoPostDto) {
            MotoPostDto motoAtualizada = motoServiceImpl.updateMoto(id, motoPostDto);
            return ResponseEntity.ok(motoAtualizada);

    }

    @GetMapping("/beacon/{codigo}")
    public ResponseEntity<MotoGetDto> findMotoByCodigoBeacon(@PathVariable UUID codigo) {
            MotoGetDto motoCodigo = motoServiceImpl.findMotoByCodigoBeacon(codigo);
            return ResponseEntity.ok(motoCodigo);
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<MotoGetDto>> findMotoByModelo(@PathVariable ModeloMoto modelo) {
        List<MotoGetDto> motos = motoServiceImpl.findMotoByModelo(modelo);
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/modelo/{modelo}/pageable")
    public ResponseEntity<Page<MotoGetDto>> findMotoByModeloPaged(@PathVariable ModeloMoto modelo, Pageable pageable) {
        Page<MotoGetDto> motos = motoServiceImpl.findMotoByModeloPaged(modelo, pageable);
        return ResponseEntity.ok(motos);
    }




}