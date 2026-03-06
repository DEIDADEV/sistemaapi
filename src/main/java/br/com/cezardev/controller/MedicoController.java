package br.com.cezardev.controller;

import br.com.cezardev.dto.MedicoDTO;
import br.com.cezardev.service.MedicoService;

import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // ==========================
    // LISTAR
    // ==========================
    @GetMapping
    public Page<MedicoDTO> listar(

            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String crm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ) {

        return medicoService.listar(nome, crm, page, size);

    }

    // ==========================
    // INSERIR
    // ==========================
    @PostMapping
    public MedicoDTO inserir(@RequestBody MedicoDTO dto) {

        return medicoService.inserir(dto);

    }

    // ==========================
    // ATUALIZAR
    // ==========================
    @PutMapping("/{id}")
    public MedicoDTO atualizar(

            @PathVariable Long id,
            @RequestBody MedicoDTO dto

    ) {

        return medicoService.atualizar(id, dto);

    }

    // ==========================
    // EXCLUIR
    // ==========================
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        medicoService.excluir(id);

    }
}