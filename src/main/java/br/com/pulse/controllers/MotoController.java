package br.com.pulse.controllers;


import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.MotoPostDto;
import br.com.pulse.services.MotoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<MotoGetDto> buscarPorId(@PathVariable Long id) {
        return motoServiceImpl.findMotoById(id)
                .map(moto -> ResponseEntity.ok(new MotoGetDto(moto)))
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Lista todas as Motos cadastradas")
    @GetMapping
    public ResponseEntity<List<MotoGetDto>> listar() {
        return ResponseEntity.ok(motoServiceImpl.findAllMotos());
    }


    @Operation(summary = "Realiza cadastro da Moto")
    @PostMapping("/{patioId}")
    public ResponseEntity<MotoPostDto> cadastrarMoto(@Valid @RequestBody MotoPostDto motoPostDto, @PathVariable Long patioId) {
            MotoPostDto moto = motoServiceImpl.saveMoto(motoPostDto, patioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(moto);
    }


    @Operation(summary = "Deleta Moto pelo ID cadastrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMoto(@PathVariable Long id) {
            motoServiceImpl.deleteMotoById(id);
            return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza Moto pelo ID cadastrado")
    @PutMapping("/{id}")
    public ResponseEntity<MotoPostDto> atualizarMoto(@PathVariable Long id, @Valid @RequestBody MotoPostDto motoPostDto) {
            MotoPostDto motoAtualizada = motoServiceImpl.updateMoto(id, motoPostDto);
            return ResponseEntity.ok(motoAtualizada);

    }

    @GetMapping("/beacon/{codigo}")
    public ResponseEntity<MotoGetDto> buscarPorCodigoBeacon(@PathVariable UUID codigo) {
            MotoGetDto motoCodigo = motoServiceImpl.findMotoByCodigoBeacon(codigo);
            return ResponseEntity.ok(motoCodigo);
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<MotoGetDto>> listarPorModelo(@PathVariable ModeloMoto modelo) {
        List<MotoGetDto> motos = motoServiceImpl.findMotoByModelo(modelo);
        return ResponseEntity.ok(motos);
    }

}