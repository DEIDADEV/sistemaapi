package br.com.cezardev.service;

import br.com.cezardev.dto.MedicoDTO;
import br.com.cezardev.entity.MedicoEntity;
import br.com.cezardev.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    // Listar todos os médicos
    public List<MedicoDTO> listarTodos() {
        List<MedicoEntity> medicos = medicoRepository.findAll();
        return medicos.stream().map(MedicoDTO::new).toList();
    }

    // Inserir novo médico
    public MedicoDTO inserir(MedicoDTO medico) {
        MedicoEntity medicoEntity = new MedicoEntity(medico);
        medicoRepository.save(medicoEntity);
        return new MedicoDTO(medicoEntity);
    }

    // Atualizar médico existente
    public MedicoDTO atualizar(Long id, MedicoDTO dto) {
        MedicoEntity medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());
        medico.setEspecialidade(dto.getEspecialidade());

        medicoRepository.save(medico);

        return new MedicoDTO(medico);
    }

    // Excluir médico
    public void excluir(Long id) {
        MedicoEntity medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        medicoRepository.delete(medico);
    }
}