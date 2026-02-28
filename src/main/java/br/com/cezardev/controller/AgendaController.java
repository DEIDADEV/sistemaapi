package br.com.cezardev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cezardev.dto.AgendaDTO;
import br.com.cezardev.service.AgendaService;

@RestController
@RequestMapping("agenda")
@CrossOrigin
public class AgendaController {
	
	
	@Autowired
	private AgendaService agendaService;
	
	@GetMapping
	public List<AgendaDTO> listarTodos() {
		return agendaService.listarTodos();
	}
	
	/*@PostMapping
	public void inserir(@RequestBody AgendaDTO agenda) {
		agendaService.inserir(agenda);
	}
	
	@PutMapping
	public AgendaDTO alterar(@RequestBody AgendaDTO agenda) {
		return agendaService.alterar(agenda);
	}*/

}
