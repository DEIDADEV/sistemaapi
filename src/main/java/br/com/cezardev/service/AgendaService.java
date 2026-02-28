package br.com.cezardev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cezardev.dto.AgendaDTO;
import br.com.cezardev.entity.AgendaEntity;
import br.com.cezardev.repository.AgendaRepository;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;
	
	public List<AgendaDTO> listarTodos(){
		List<AgendaEntity> agendas = agendaRepository.findAll();
		return agendas.stream().map(AgendaDTO::new).toList();
	}
	
	/*public void inserir(AgendaDTO agenda) {
		AgendaEntity agendaEntity = new AgendaEntity(agenda);
		agendaRepository.save(agendaEntity);
	}
	
	public AgendaDTO alterar(AgendaDTO agenda) {
		AgendaEntity agendaEntity = new AgendaEntity(agenda);
		return new AgendaDTO(agendaRepository.save(agendaEntity));
	}*/
	
}
