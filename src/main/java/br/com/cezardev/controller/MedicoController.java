package br.com.cezardev.controller;

import br.com.cezardev.dto.MedicoDTO;
import br.com.cezardev.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    // Listar todos os médicos
    @GetMapping
    public List<MedicoDTO> listarTodos() {
        return service.listarTodos();
    }

    // Inserir novo médico
    @PostMapping
    public ResponseEntity<MedicoDTO> inserir(@RequestBody MedicoDTO dto) {
        MedicoDTO criado = service.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // Atualizar médico existente
    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizar(@PathVariable Long id, @RequestBody MedicoDTO dto) {
        try {
            MedicoDTO atualizado = service.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Excluir médico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}