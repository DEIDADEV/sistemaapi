package br.com.cezardev.controller;

import br.com.cezardev.dto.PacienteDTO;
import br.com.cezardev.service.PacienteService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    // ============================
    // BUSCAR PACIENTES
    // ============================
    @GetMapping
    public ResponseEntity<Page<PacienteDTO>> buscar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            Pageable pageable) {

        Page<PacienteDTO> pacientes = service.buscar(nome, cpf, pageable);

        return ResponseEntity.ok(pacientes);
    }

    // ============================
    // INSERIR
    // ============================
    @PostMapping
    public ResponseEntity<PacienteDTO> inserir(@RequestBody PacienteDTO dto) {

        PacienteDTO criado = service.inserir(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criado);
    }

    // ============================
    // ATUALIZAR
    // ============================
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizar(
            @PathVariable Long id,
            @RequestBody PacienteDTO dto) {

        PacienteDTO atualizado = service.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);
    }

    // ============================
    // EXCLUIR
    // ============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}