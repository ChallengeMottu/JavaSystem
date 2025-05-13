package br.com.pulse.controllers;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.dtos.MotoGetDto;
import br.com.pulse.dtos.MotoPostDto;
import br.com.pulse.services.MotoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motos")
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
        return motoServiceImpl.findById(id)
                .map(moto -> ResponseEntity.ok(new MotoGetDto(moto)))
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Lista todas as Motos cadastradas")
    @GetMapping
    public ResponseEntity<List<MotoGetDto>> listar() {
        return ResponseEntity.ok(motoServiceImpl.findAll());
    }


    @Operation(summary = "Realiza cadastro da Moto")
    @PostMapping("/{patioId}")
    public ResponseEntity<MotoPostDto> cadastrarMoto(@RequestBody MotoPostDto motoPostDto, @PathVariable Long patioId) {
        try {
            MotoPostDto moto = motoServiceImpl.cadastrarMoto(motoPostDto, patioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(moto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Operation(summary = "Deleta Moto pelo ID cadastrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMoto(@PathVariable Long id) {
        try {
            motoServiceImpl.deletebyId(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualiza Moto pelo ID cadastrado")
    @PutMapping("/{id}")
    public ResponseEntity<MotoPostDto> atualizarMoto(@PathVariable Long id, @RequestBody MotoPostDto motoPostDto) {
        try {
            MotoPostDto motoAtualizada = motoServiceImpl.updateMoto(id, motoPostDto);
            return ResponseEntity.ok(motoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
