package br.com.cezardev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cezardev.dto.PacienteDTO;
import br.com.cezardev.entity.PacienteEntity;
import br.com.cezardev.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<PacienteDTO> listarTodos() {
		List<PacienteEntity> pacientes = pacienteRepository.findAll();
		return pacientes.stream().map(PacienteDTO::new).toList();
	}
	
	public void inserir(PacienteDTO paciente) {
		PacienteEntity perfilEntity = new PacienteEntity(paciente);
		pacienteRepository.save(perfilEntity);
	}
	
	public PacienteDTO alterar(PacienteDTO paciente) {
		PacienteEntity perfilEntity = new PacienteEntity(paciente);
		return new PacienteDTO(pacienteRepository.save(perfilEntity));
	}
	
	public void excluir (Long id) {
		PacienteEntity paciente = pacienteRepository.findById(id).get();
		pacienteRepository.delete(paciente);
	}
	
	public PacienteDTO buscarPorId(Long id) {
		return new PacienteDTO(pacienteRepository.findById(id).get());
	}

}
