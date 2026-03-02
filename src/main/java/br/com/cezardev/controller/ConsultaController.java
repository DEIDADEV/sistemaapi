package br.com.cezardev.controller;

import br.com.cezardev.dto.ConsultaDTO;
import br.com.cezardev.dto.ConsultaResponseDTO;
import br.com.cezardev.service.ConsultaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/consultas")
@CrossOrigin
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    // Endpoint existente: criar nova consulta
    @PostMapping
    public ConsultaResponseDTO inserir(@RequestBody ConsultaDTO dto) {
        return service.inserir(dto);
    }

    // Endpoint existente: listar todas as consultas
    @GetMapping
    public List<ConsultaResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    // NOVO ENDPOINT: atualizar status de uma consulta
    @PatchMapping("/{id}/status")
    public ConsultaResponseDTO atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        return service.atualizarStatus(id, status);
    }
}