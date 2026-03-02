package br.com.cezardev.controller;

import br.com.cezardev.dto.PacienteDTO;
import br.com.cezardev.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    // ============================
    // LISTAR TODOS
    // ============================
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // ============================
    // INSERIR NOVO
    // ============================
    @PostMapping
    public ResponseEntity<PacienteDTO> inserir(@RequestBody PacienteDTO dto) {
        PacienteDTO criado = service.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // ============================
    // ATUALIZAR
    // ============================
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizar(@PathVariable Long id,
                                                 @RequestBody PacienteDTO dto) {
        try {
            PacienteDTO atualizado = service.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // ============================
    // EXCLUIR
    // ============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}