package br.com.cezardev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cezardev.dto.MedicoDTO;
import br.com.cezardev.service.MedicoService;

@RestController
@RequestMapping("/medico")
@CrossOrigin
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
    public ResponseEntity<List<MedicoDTO>> listarTodos() {
        List<MedicoDTO> medicos = medicoService.listarTodos();
        return ResponseEntity.ok(medicos);
    }

	@PostMapping
	public void inserir(@RequestBody MedicoDTO medico) {
		medicoService.inserir(medico);
	}
}