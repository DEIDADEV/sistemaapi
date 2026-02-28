package br.com.cezardev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.cezardev.dto.ConsultaDTO;
import br.com.cezardev.service.ConsultaService;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*") // libera acesso para front-end local
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    // Listar todas
    @GetMapping
    public List<ConsultaDTO> listarTodos() {
        return consultaService.listarTodos();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ConsultaDTO buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    // Inserir nova consulta
    @PostMapping
    public ConsultaDTO inserir(@RequestBody ConsultaDTO dto) {
        return consultaService.inserir(dto);
    }

    // Alterar consulta
    @PutMapping("/{id}")
    public ConsultaDTO alterar(@PathVariable Long id, @RequestBody ConsultaDTO dto) {
        dto.setId(id);
        return consultaService.alterar(dto);
    }

    // Excluir consulta
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        consultaService.excluir(id);
    }
}