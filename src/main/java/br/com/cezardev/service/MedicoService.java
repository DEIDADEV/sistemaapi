package br.com.cezardev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cezardev.dto.MedicoDTO;
import br.com.cezardev.entity.MedicoEntity;
import br.com.cezardev.repository.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public List<MedicoDTO> listarTodos() {
		List<MedicoEntity> medicos = medicoRepository.findAll();
		return medicos.stream().map(MedicoDTO::new).toList();
	}
	
	public void inserir(MedicoDTO medico) {
		MedicoEntity medicoEntity = new MedicoEntity(medico);
		medicoRepository.save(medicoEntity);
	}

}
