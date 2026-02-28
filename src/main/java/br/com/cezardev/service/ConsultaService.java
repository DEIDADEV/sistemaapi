package br.com.cezardev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cezardev.dto.ConsultaDTO;
import br.com.cezardev.entity.ConsultaEntity;
import br.com.cezardev.entity.MedicoEntity;
import br.com.cezardev.entity.PacienteEntity;
import br.com.cezardev.repository.ConsultaRepository;
import br.com.cezardev.repository.MedicoRepository;
import br.com.cezardev.repository.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    // Listar todas as consultas
    public List<ConsultaDTO> listarTodos() {
        return consultaRepository.findAll()
                .stream()
                .map(ConsultaDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public ConsultaDTO buscarPorId(Long id) {
        ConsultaEntity entity = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        return new ConsultaDTO(entity);
    }

    // Inserir nova consulta
    public ConsultaDTO inserir(ConsultaDTO dto) {
        MedicoEntity medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado: " + dto.getMedicoId()));

        PacienteEntity paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado: " + dto.getPacienteId()));

        ConsultaEntity entity = new ConsultaEntity(dto, medico, paciente);

        return new ConsultaDTO(consultaRepository.save(entity));
    }

    // Alterar consulta existente
    public ConsultaDTO alterar(ConsultaDTO dto) {
        if (dto.getId() == null || !consultaRepository.existsById(dto.getId())) {
            throw new RuntimeException("Consulta não encontrada para alteração");
        }

        MedicoEntity medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado: " + dto.getMedicoId()));

        PacienteEntity paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado: " + dto.getPacienteId()));

        ConsultaEntity entity = new ConsultaEntity(dto, medico, paciente);
        entity.setId(dto.getId()); // manter o ID para atualização

        return new ConsultaDTO(consultaRepository.save(entity));
    }

    // Excluir consulta
    public void excluir(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada para exclusão");
        }
        consultaRepository.deleteById(id);
    }
}